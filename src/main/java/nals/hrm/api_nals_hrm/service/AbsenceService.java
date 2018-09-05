package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.controller.DateDiff;
import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.AbsenceDTO;
import nals.hrm.api_nals_hrm.dto.ListAbsenceDTO;
import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.entities.Absence;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.entities.Processes;
import nals.hrm.api_nals_hrm.entities.Project;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.repository.*;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class AbsenceService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProcessesRepository processesRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ListAbsenceDTO getListAbsenceEmployeeByToken(HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {

        //find employee by token
        Employee employee = employeeRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));

        return getListAbsenceEmployeeByIdEmployee(employee.getIdEmployee(), page, pageSize);
    }

    public String addAbsence(Absence absence, HttpServletRequest req) {
        Date fromDate;
        Date toDate;
        Date now = new Date();
        try {
            //convert date string to date
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(absence.getFromDate());
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse(absence.getToDate());

            //find employee submit form absence
            //find by token login
            Employee employee = employeeRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));

            absence.setEmployeeId(employee.getIdEmployee());

            String strNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now);
            absence.setCreatedAt(strNow);
            absence.setUpdateAt(strNow);

            handleAbsentDays(absence,fromDate, toDate);
            return "Insert absence success!";
        } catch (ParseException e) {
            throw new CustomException("Error server", 500);
        }

    }




    public String editAbsence(int id, Absence absenceEdit) {
        Date fromDate;
        Date toDate;
        try {

            //find absence old by id get client
            Absence absenceOld = absenceRepository.findByIdAbsencesAndDeleteFlag(id, 0);

            //validate fromDate, toDate edit
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(absenceEdit.getFromDate());
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse(absenceEdit.getToDate());
            Date now = new Date();
            String strNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now);

            //set employeeId of absence absenceEdit
            //can't change employeeId
            absenceEdit.setEmployeeId(absenceOld.getEmployeeId());

            //set idAbsenceEdit get from absence
            absenceEdit.setIdAbsences(absenceOld.getIdAbsences());

            absenceEdit.setUpdateAt(strNow);

            //if time edit < 30 day(equal fromDate) =>can edit absence
            if (DateDiff.dateDiff(new SimpleDateFormat("yyyy-MM-dd").parse(absenceOld.getFromDate()), now) <= 30) {
                handleAbsentDays(absenceEdit,fromDate,toDate);
                return "Update absence success!";
            } else {
                throw new CustomException("Can't edit!", 400);
            }

        } catch (ParseException e) {
            throw new CustomException("Error server", 500);
        }

    }

    public String deleteAbsence(int idAbsence) {
        Absence absence = absenceRepository.findByIdAbsencesAndDeleteFlag(idAbsence, 0);
        absence.setDeleteFlag(1);
        absenceRepository.save(absence);
        return "Delete absence success!";
    }

    public ListDTO getListAbsenceEmployeeHR(Optional<Integer> page, Optional<Integer> pageSize) {
        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;
        ArrayList<Absence> listAbsence = absenceRepository.findByDeleteFlagOrderByUpdateAtDesc(0, PageRequest.of(evalPage, evalPageSize));
        ArrayList<Object> listResult = new ArrayList<>();
        mapListAbsence(listAbsence,listResult);
        return new ListDTO(absenceRepository.findByDeleteFlag(0).size(), listResult);
    }

    public ListAbsenceDTO getListAbsenceEmployeeByIdEmployee(int idEmployee, Optional<Integer> page, Optional<Integer> pageSize) {

        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        Employee employee = employeeRepository.findByIdEmployeeAndIsEmployeeAndDeleteFlag(idEmployee, 1, 0);

        //find list absence employee not yet remove deleteFlag = 0
        //paging result
        ArrayList<Object> absenceList = absenceRepository.findByEmployeeIdAndDeleteFlagOrderByUpdateAtDesc(employee.getIdEmployee(), 0, PageRequest.of(evalPage, evalPageSize));

        int allowAbsence = 0; //number absence allow

        //số ngày phép năm ngoái còn lại
        //được lấy dữ liệu từ database
        int remainingAbsenceDays = employee.getRemainingAbsenceDays();

        //số ngày phép đã nghĩ (theo đăng ký)
        // số ngày này có thể vượt mức ngày phép cho phép hằng năm
        //tinh ở năm hiện tại
        int annualLeave = absenceRepository.countLeave(employee.getIdEmployee(), "annual_leave");

        //số ngày nghỉ không trả lương
        int unpaidLeave = absenceRepository.countLeave(employee.getIdEmployee(), "unpaid_leave");

        //nghỉ thai sản
        int maternityLeave = absenceRepository.countLeave(employee.getIdEmployee(), "maternity_leave");

        //nghỉ cưới
        int marriageLeave = absenceRepository.countLeave(employee.getIdEmployee(), "marriage_leave");

        //nghỉ tang
        int bereavementLeave = absenceRepository.countLeave(employee.getIdEmployee(), "bereavement_leave");

        //tinh số ngày nghỉ duoc phep hang nam
        String strStartWorkDate = employee.getStartWorkDate();
        System.out.println(strStartWorkDate);
        Date startWorkDate;
        try {
            startWorkDate = new SimpleDateFormat("yyyy-MM-dd").parse(strStartWorkDate);
        } catch (ParseException e) {
            throw new CustomException("Error server", 500);
        }
        Date dateNow = new Date();
        Calendar calendarNow = Calendar.getInstance();
        Calendar calendarWork = Calendar.getInstance();
        calendarNow.setTime(dateNow);
        calendarWork.setTime(startWorkDate);

        int timeWork = calendarNow.get(Calendar.YEAR) - calendarWork.get(Calendar.YEAR);
        switch (timeWork) {
            case 0:
                allowAbsence = 12 - calendarWork.get(Calendar.MONTH);
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                allowAbsence = 12 + remainingAbsenceDays;
                break;
            case 6:
                allowAbsence = 13 + remainingAbsenceDays;
                break;
            case 7:
                allowAbsence = 14 + remainingAbsenceDays;
                break;
            case 8:
                allowAbsence = 15 + remainingAbsenceDays;
                break;
            default:
                allowAbsence = 16 + remainingAbsenceDays;

        }
        if (annualLeave > allowAbsence) {
            unpaidLeave = unpaidLeave + annualLeave - allowAbsence;
        }


        int total = absenceRepository.findByEmployeeIdAndDeleteFlag(employee.getIdEmployee(), 0).size();
        ListDTO result = new ListDTO(total, absenceList);
        return new ListAbsenceDTO(allowAbsence, remainingAbsenceDays, annualLeave, unpaidLeave, maternityLeave, marriageLeave, bereavementLeave, result);
    }

    public ListDTO findAbsenceProjectProcessesManageRolePO(String id) {
        try {
            //get project by id
            Project project = projectRepository.findByIdProjectAndDeleteFlag(id, 0);

            //get list member of project
            //any member not exit project
            List<Employee> listMember = employeeRepository.findAllNotExit(id);

            AbsenceDTO absenceDTO = new AbsenceDTO();
            ArrayList<Object> listResult = new ArrayList<>();
            List<Absence> absenceList;
            Date now = new Date();
            String strNow = new SimpleDateFormat("yyyy-MM-dd").format(now);
            String startDateProject;
            String endDateProject;
            startDateProject = project.getStartDate() != null ? project.getStartDate() : strNow;
            endDateProject = project.getEstimateEndDate() != null ? project.getEstimateEndDate() : strNow;
            //get list absence of a member
            for (Employee objEmp : listMember) {
                //get list absence of objEmp
                //when project start to end
                absenceList = absenceRepository.findAbsenceInProject(objEmp.getIdEmployee(), startDateProject, endDateProject);
                for (Absence objAbs : absenceList) {
                    absenceDTO = modelMapper.map(objAbs, absenceDTO.getClass());
                    //find nameEmployee
                    //if name != null=> return name else return ""
                    absenceDTO.setNameEmployee(objEmp.getNameEmployee());
                    absenceDTO.setIdEmployee(objEmp.getIdEmployee());
                    absenceDTO.setNameProject(project.getNameProject());
                    absenceDTO.setIdProject(project.getIdProject());
                    listResult.add(absenceDTO);
                }
            }

            return new ListDTO(listResult.size(),listResult);

        } catch (Exception e) {
            throw new CustomException("Error server", 500);
        }
    }

    public ListDTO searchAbsenceEmployeeHR(Optional<Integer> month, Optional<Integer> year, Optional<Integer> page, Optional<Integer> pageSize) {
        try{
            int evalPageSize = pageSize.orElse(Define.initialPageSize);
            int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

            int evalMonth = month.orElse(0);
            int evalYear = year.orElse(0);
            ArrayList<Absence> listAbsence;
            int total = 0;
            if(evalMonth != 0 && evalYear != 0){
                listAbsence = absenceRepository.findByMonthAndYear(evalMonth,evalYear,PageRequest.of(evalPage, evalPageSize));
                total = absenceRepository.findByMonthAndYear(evalMonth,evalYear);
            }else{
                total = absenceRepository.findByYear(evalYear);
                listAbsence = absenceRepository.findByYear(evalYear, PageRequest.of(evalPage, evalPageSize));
            }

            ArrayList<Object> listResult = new ArrayList<>();
            mapListAbsence(listAbsence,listResult);
            return new ListDTO(total, listResult);
        }catch (Exception e){
            throw new CustomException("Error server",500);
        }

    }

    public void mapListAbsence(ArrayList<Absence> listAbsence, ArrayList<Object> listResult){
        String nameEmployee;
        Employee employee;
        for (Absence objAbsence : listAbsence) {
            AbsenceDTO absenceDTO = new AbsenceDTO();
            absenceDTO = modelMapper.map(objAbsence, absenceDTO.getClass());
            //find nameEmployee
            //if name != null=> return name else return ""
            employee = employeeRepository.findByIdEmployeeAndIsEmployeeAndDeleteFlag(objAbsence.getEmployeeId(), 1, 0);
            nameEmployee = employee != null ? employee.getNameEmployee() : "";
            absenceDTO.setIdEmployee(objAbsence.getEmployeeId());
            absenceDTO.setNameEmployee(nameEmployee);
            listResult.add(absenceDTO);

        }

    }

    public void handleAbsentDays(Absence absence, Date fromDate, Date toDate){
        //kiem tra truong hop đơn xin nghĩ được submit trong khoảng thời gian giữa 2 năm
        //ex: from: 29/12/2017 - to: 3/1/2018
        //chia làm 2 đơn để lưu
        int year = 0;
        if(fromDate.getYear() != toDate.getYear()){
            //chia làm 2 đơn để save vào database
            //đơn thứ nhất từ fromDate - 31/12/fromYear
            absence.setFromDate(absence.getFromDate());
            year = Integer.parseInt(new SimpleDateFormat("yyyy").format(fromDate));
            absence.setToDate(year+"-12-31");
            absenceRepository.save(absence);

            //đơn thứ 2 từ 1/1/toYear - toDate
            year = Integer.parseInt(new SimpleDateFormat("yyyy").format(toDate));
            absence.setFromDate(year+"-01-01");
            absence.setToDate(absence.getToDate());
            absenceRepository.save(absence);

        }else{
            if (fromDate.equals(toDate) || fromDate.before(toDate)) {
                absenceRepository.save(absence);
            } else {
                throw new CustomException("Error data", 400);
            }
        }

    }
}
