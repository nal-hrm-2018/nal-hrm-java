package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.define.CheckWeekend;
import nals.hrm.api_nals_hrm.define.DateDiff;
import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.AbsenceDTO;
import nals.hrm.api_nals_hrm.dto.ListAbsenceDTO;
import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.entities.*;
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
    private HolidayDefaultRepository holidayDefaultRepository;

    @Autowired
    private HolidayRepository holidayRepository;


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

            //if time edit < 30 day(equal fromDate or equal createAt) =>can edit absence
            if (DateDiff.dateDiff(new SimpleDateFormat("yyyy-MM-dd").parse(absenceOld.getFromDate()), now) <= 30 ||DateDiff.dateDiff(new SimpleDateFormat("yyyy-MM-dd").parse(absenceOld.getCreatedAt()), now) <=30 ) {
                int year = 0;
                if(fromDate.getYear() != toDate.getYear()){
                   //can't edit
                    throw new CustomException("Can't edit!", 400);

                }else{
                    if (fromDate.equals(toDate) || fromDate.before(toDate)) {
                        absenceRepository.save(absenceEdit);
                    } else {
                        throw new CustomException("Error data", 400);
                    }
                }
                return "Update absence success!";
            } else {
                throw new CustomException("Can't edit!", 400);
            }

        } catch (ParseException e) {
            throw new CustomException("Error server", 500);
        }

    }

    public String deleteAbsence(int idAbsence) {
        //time can delete the same edit (30 day)
        Absence absence = absenceRepository.findByIdAbsencesAndDeleteFlag(idAbsence, 0);
        Date now = new Date();
        try {
            if (DateDiff.dateDiff(new SimpleDateFormat("yyyy-MM-dd").parse(absence.getFromDate()), now) <= 30 ||DateDiff.dateDiff(new SimpleDateFormat("yyyy-MM-dd").parse(absence.getCreatedAt()), now) <=30 ) {
                absence.setDeleteFlag(1);
                absenceRepository.save(absence);
                return "Delete absence success!";
            }else{
                throw new CustomException("Can't delete!",400);
            }
        } catch (ParseException e) {
            throw new CustomException("Error server!",500);
        }

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
        //all absence of employee all year
        //paging result
        ArrayList<Absence> absenceList = absenceRepository.findByEmployeeIdAndDeleteFlagOrderByUpdateAtDesc(employee.getIdEmployee(), 0, PageRequest.of(evalPage, evalPageSize));

       //map Absence to Object
        ArrayList<Object> result = new ArrayList<>();

        result = modelMapper.map(absenceList, result.getClass());

        Date now = new Date();
        int yearNow = Integer.parseInt(new SimpleDateFormat("yyyy").format(now));

        ArrayList<Absence> listLeave; //list leave by type

        int allowAbsence = 0; //number absence allow

        //số ngày phép năm ngoái còn lại
        //được lấy dữ liệu từ database
        int remainingAbsenceDays = employee.getRemainingAbsenceDays();

        //số ngày phép đã nghĩ (theo đăng ký)
        // số ngày này có thể vượt mức ngày phép cho phép hằng năm
        //tinh ở năm hiện tại

        //1.find list absence with type:  annual_leave in year now
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "annual_leave",yearNow);
        //so ngay nghi hop le(da tru thu 7, cn,le, bu le)
        int annualLeave = checkAbsenceDayInvalid(listLeave);

        //số ngày nghỉ không trả lương
        //2.find list absence with type:  unpaid_leave in year now
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "unpaid_leave",yearNow);
        int unpaidLeave = checkAbsenceDayInvalid(listLeave);

        //nghi thai san
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "maternity_leave",yearNow);
        int maternityLeave = 0;
        for (Absence obj: listLeave) {
            try {
                maternityLeave += DateDiff.dateDiff(new SimpleDateFormat("yyyy-MM-dd").parse(obj.getFromDate()),new SimpleDateFormat("yyyy-MM-dd").parse(obj.getToDate()));
            } catch (ParseException e) {
                throw new CustomException("Error server",500);
            }
        }

        //nghỉ cưới
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "marriage_leave",yearNow);
        //so ngay nghi hop le(da tru thu 7, cn,le, bu le)
        int marriageLeave = checkAbsenceDayInvalid(listLeave);

        //nghỉ tang
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "bereavement_leave",yearNow);
        //so ngay nghi hop le(da tru thu 7, cn,le, bu le)
        int bereavementLeave = checkAbsenceDayInvalid(listLeave);

        //nghỉ dau om
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "sick_leave",yearNow);
        //so ngay nghi hop le(da tru thu 7, cn,le, bu le)
        int sickLeave = checkAbsenceDayInvalid(listLeave);

        //tinh số ngày nghỉ duoc phep hang nam
        String strStartWorkDate = employee.getStartWorkDate();
        Date startWorkDate;
        try {
            startWorkDate = new SimpleDateFormat("yyyy-MM-dd").parse(strStartWorkDate);
        } catch (ParseException e) {
            throw new CustomException("Error server", 500);
        }

        Calendar calendarNow = Calendar.getInstance();
        Calendar calendarWork = Calendar.getInstance();
        calendarNow.setTime(now);
        calendarWork.setTime(startWorkDate);

        //so nam da lam viec cua nhan vien
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
        return new ListAbsenceDTO(allowAbsence, remainingAbsenceDays, annualLeave, unpaidLeave, maternityLeave, marriageLeave, bereavementLeave, sickLeave, new ListDTO(total, result));
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

            //creat absence for form 2
            Absence absenceForm2 = new Absence();
            absenceForm2.setUpdateAt(absence.getUpdateAt());
            absenceForm2.setCreatedAt(absence.getCreatedAt());
            absenceForm2.setEmployeeId(absence.getEmployeeId());
            absenceForm2.setReason(absence.getReason());
            absenceForm2.setAbsenceTimeId(absence.getAbsenceTimeId());
            absenceForm2.setDescription(absence.getDescription());
            absenceForm2.setAbsenceTypeId(absence.getAbsenceTypeId());


            //đơn thứ 2 từ 1/1/toYear - toDate
            year = Integer.parseInt(new SimpleDateFormat("yyyy").format(toDate));
            absenceForm2.setFromDate(year+"-01-01");
            absenceForm2.setToDate(new SimpleDateFormat("yyyy-MM-dd").format(toDate));
            absenceRepository.save(absenceForm2);

        }else{
            if (fromDate.equals(toDate) || fromDate.before(toDate)) {
                absenceRepository.save(absence);
            } else {
                throw new CustomException("Error data", 400);
            }
        }

    }

    //kiem tra xem so ngay nghi hop le(tru di ngay le, ngay thu 7, cn, nghi bu)
    public int checkAbsenceDayInvalid(ArrayList<Absence> listLeave){
        int leave = 0;
        long tmp = 0;
        int countWeekend = 0;
        int countHolidayDefault = 0;
        int countHoliday = 0;
        Date from;
        Date to;
        String strFrom;
        for (Absence objAbsence: listLeave) {
            try {

                from = new SimpleDateFormat("yyyy-MM-dd").parse(objAbsence.getFromDate());
                to = new SimpleDateFormat("yyyy-MM-dd").parse(objAbsence.getToDate());
                countHoliday = 0;
                countHolidayDefault = 0;

                //đếm số ngày đăng ký nghỉ của mỗi đơn.
                //chưa trừ thứ 7, cn và ngày lễ
                tmp = DateDiff.dateDiff(from,to) + 1;

                //đếm số thứ 7, chủ nhật trong khoảng thời gian nghỉ
                countWeekend = CheckWeekend.countWeekend(from, to);

                from = new SimpleDateFormat("yyyy-MM-dd").parse(objAbsence.getFromDate());
                to = new SimpleDateFormat("yyyy-MM-dd").parse(objAbsence.getToDate());

                //danh sách ngày lễ default
                while (from.compareTo(to) <= 0){
                    strFrom = new SimpleDateFormat("yyyy-MM-dd").format(from);
                    //kiểm tra xem nếu không phải thứ 7 or chủ nhật thì mới kiểm tra ngày nghỉ đó có thuộc ngày lễ hay không
                    //nếu là ngày lễ thì countHolidayDefault += 1
                    if(CheckWeekend.checkDate(from) < 7 && holidayDefaultRepository.findByDateHolidayDefaultAndDeleteFlag(strFrom,0) != null){
                        countHolidayDefault += 1;
                    }
                    //kiem tra xe ngay nghi do co thuoc ngay nghi bu nao khong
                    //neu có thì countHoliday += 1
                    if(holidayRepository.findByDateHolidayAndDeleteFlag(strFrom,0) != null){
                        countHoliday += 1;
                    }
                    from.setDate(from.getDate() + 1);
                }

                tmp = tmp - countWeekend - countHolidayDefault - countHoliday;
                if(objAbsence.getAbsenceTime().getNameAbsenceTime().equals("all")){
                    leave += tmp;
                }else{
                    leave += (tmp / 2);
                }

            }catch (ParseException e){
                throw new CustomException("Error server", 500);
            }
        }
        return leave;
    }
}
