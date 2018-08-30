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
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(absence.getFromDate());
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse(absence.getToDate());
            //check if fromDate before toDate => true
            if (fromDate.equals(toDate) || fromDate.before(toDate)) {
                Employee employee = employeeRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
                absence.setEmployeeId(employee.getIdEmployee());
                String strNow = new SimpleDateFormat("yyyy-MM-dd").format(now);
                absence.setCreatedAt(strNow);
                absenceRepository.save(absence);
                return "Insert absence success!";
            } else {
                throw new CustomException("Error data", 400);
            }
        } catch (ParseException e) {
            throw new CustomException("Error server", 500);
        }

    }

    public String editAbsence(int id, Absence absenceEdit) {
        Date fromDate;
        Date toDate;
        Date createAt;
        try {

            Absence absence = absenceRepository.findByIdAbsencesAndDeleteFlag(id, 0);

            String strCreateAt = absence.getCreatedAt() == null ? absence.getFromDate() : absence.getCreatedAt();

            //validate fromDate, toDate edit
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(absenceEdit.getFromDate());
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse(absenceEdit.getToDate());
            createAt = new SimpleDateFormat("yyyy-MM-dd").parse(strCreateAt);
            Date now = new Date();
            //if time edit < 30 day =>can edit absence
            if (DateDiff.dateDiff(createAt, now) <= 30) {
                if (fromDate.equals(toDate) || fromDate.before(toDate)) {
                    //set employeeId of absence absenceEdit
                    //can't change employeeId
                    absenceEdit.setEmployeeId(absence.getEmployeeId());
                    //set idAbsenceEdit get from absence
                    absenceEdit.setIdAbsences(absence.getIdAbsences());
                    String strNow = new SimpleDateFormat("yyyy-MM-dd").format(now);
                    absence.setUpdateAt(strNow);
                    absenceRepository.save(absenceEdit);
                    return "Update absence success!";
                } else {
                    throw new CustomException("Error data", 400);
                }
            } else {
                throw new CustomException("Error data", 400);
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

//    public Object getListAbsenceEmployee(HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {
//
//        int evalPageSize = pageSize.orElse(Define.initialPageSize);
//        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;
//
//        //find employee by token
//        Employee employee = employeeRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
//
//        //if HR login can view all absence of employee which not yer delete(deleteFlag=0)
//            return getListAbsenceEmployeeHR(evalPage, evalPageSize);
//    }

//    public ListDTO getListAbsenceEmployeePO(Employee employee, int page, int pageSize) {
//        Date now = new Date();
//        String strNow = new SimpleDateFormat("yyyy-MM-dd").format(now);
//        //search list project(in processes) if employee join with role = "PO"
//        //processes
//        ArrayList<Processes> processesRolePO = processesRepository.findByEmployeeIdAndRoleIdAndDeleteFlag(employee.getIdEmployee(), roleRepository.findByNameRole("PO").getIdRole(), 0);
//        ArrayList<Object> listResult = new ArrayList<>();
//        Date startDateProject;
//        Date endDateProject;
//        //time absence
//        Date startDateAbsence;
//        Date endDateAbsence;
//
//        try {
//            for (Processes processes : processesRolePO) {
//                //search list employee of project
//                Project project = projectRepository.findByIdProjectAndDeleteFlag(processes.getProjectId(), 0);
//                //get list employee join project
//                List<Employee> employeeList = project.getEmployeeList();
//                //time start project, if startDateProject null => time = now
//                startDateProject = new SimpleDateFormat("yyyy-MM-dd").parse(processes.getStartDate() != null ? processes.getStartDate() : strNow);
//                //time end project, if endDateProject null => time = now
//                endDateProject = new SimpleDateFormat("yyyy-MM-dd").parse(processes.getStartDate() != null ? processes.getEndDate() : strNow);
//
//                for (Employee objEmp : employeeList) {
//                    //get list absence of objEmp
//                    //when project start to end
////                    List<Absence> absenceList = absenceRepository.findByEmployeeIdAndDeleteFlag(objEmp.getIdEmployee(),0);
//                    List<Absence> absenceList = absenceRepository.findByEmployeeIdAndDeleteFlagAndFromDateGreaterThanEqualAndToDateLessThanEqual(objEmp.getIdEmployee(), 0, processes.getStartDate(), processes.getEndDate());
//                    for (Absence objAbs : absenceList) {
//                        startDateAbsence = new SimpleDateFormat("yyyy-MM-dd").parse(objAbs.getFromDate());
//                        endDateAbsence = new SimpleDateFormat("yyyy-MM-dd").parse(objAbs.getToDate());
//                        //kiem tra neu ngay bat dau va ket thuc xin nghi nam trong giai doan du an dang thuc hien
//                        //moi hien thi no cho po cua project
//                        //????
////                        if ((startDateAbsence.equals(startDateProject) || startDateAbsence.after(startDateProject)) && (endDateAbsence.equals(endDateProject) || endDateAbsence.before(endDateProject))){
//                        AbsenceDTO absenceDTO = new AbsenceDTO();
//                        absenceDTO = modelMapper.map(objAbs, absenceDTO.getClass());
//                        //find nameEmployee
//                        //if name != null=> return name else return ""
//                        absenceDTO.setNameEmployee(objEmp.getNameEmployee());
//                        absenceDTO.setNameProject(project.getNameProject());
//                        absenceDTO.setIdProject(project.getIdProject());
//                        listResult.add(absenceDTO);
////                        }
//
//                    }
//                }
//
//            }
//        } catch (ParseException e) {
//            throw new CustomException("Error server", 500);
//        }
//
//        return new ListDTO(0, listResult);
//    }

    public ListDTO getListAbsenceEmployeeHR(Optional<Integer> page, Optional<Integer> pageSize) {
        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;
        ArrayList<Absence> listAbsence = absenceRepository.findByDeleteFlag(0, PageRequest.of(evalPage, evalPageSize));
        ArrayList<Object> listResult = new ArrayList<>();
        Employee employee;
        String nameEmployee;
        for (Absence objAbsence : listAbsence) {
            AbsenceDTO absenceDTO = new AbsenceDTO();
            absenceDTO = modelMapper.map(objAbsence, absenceDTO.getClass());
            //find nameEmployee
            //if name != null=> return name else return ""
            employee = employeeRepository.findByIdEmployeeAndIsEmployeeAndDeleteFlag(objAbsence.getEmployeeId(), 1, 0);
            nameEmployee = employee != null ? employee.getNameEmployee() : "";
            absenceDTO.setNameEmployee(nameEmployee);
            listResult.add(absenceDTO);

        }
        return new ListDTO(absenceRepository.findByDeleteFlag(0).size(), listResult);
    }


    public ListAbsenceDTO getListAbsenceEmployeeByIdEmployee(int idEmployee, Optional<Integer> page, Optional<Integer> pageSize) {

        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        Employee employee = employeeRepository.findByIdEmployeeAndIsEmployeeAndDeleteFlag(idEmployee, 1, 0);

        //find list absence employee not yet remove deleteFlag = 0
        //paging result
        ArrayList<Object> absenceList = absenceRepository.findByEmployeeIdAndDeleteFlag(employee.getIdEmployee(), 0, PageRequest.of(evalPage, evalPageSize));

        int allowAbsence = 0; //number absence allow
        //số ngày phép năm ngoái còn lại
        int remainingAbsenceDays = employee.getRemainingAbsenceDays();

        //số ngày phép đã nghĩ (theo đăng ký)
        // số ngày này có thể vượt mức ngày phép cho phép hằng năm
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
            endDateProject = project.getEndDate() != null ? project.getEndDate() : strNow;
            //get list absence of a member
            for (Employee objEmp : listMember) {
                //get list absence of objEmp
                //when project start to end
//
                absenceList = absenceRepository.findByEmployeeIdAndDeleteFlagAndFromDateGreaterThanEqualAndToDateLessThanEqual(objEmp.getIdEmployee(), 0, startDateProject, endDateProject);
                for (Absence objAbs : absenceList) {
                    absenceDTO = modelMapper.map(objAbs, absenceDTO.getClass());
                    //find nameEmployee
                    //if name != null=> return name else return ""
                    absenceDTO.setNameEmployee(objEmp.getNameEmployee());
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
}
