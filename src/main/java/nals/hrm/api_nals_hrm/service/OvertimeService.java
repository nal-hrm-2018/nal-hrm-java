package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.define.DateDiff;
import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.dto.ListOvertimeDTO;
import nals.hrm.api_nals_hrm.dto.OvertimeDTO;
import nals.hrm.api_nals_hrm.entities.*;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.repository.*;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OvertimeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    OvertimeRepository overtimeRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProcessesRepository processesRepository;

    public Object getListOvertimeByToken(HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {
        Employee employee = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)),0 , 0);
        return getListOvertimeByIdEmployee(employee.getIdEmployee(),page,pageSize);
    }

    public ListOvertimeDTO getListOvertimeByIdEmployee(int idEmployee, Optional<Integer> page, Optional<Integer> pageSize) {

        float normal = 0;
        float dayOff = 0;
        float holiday = 0;

        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        ArrayList<Overtime> overtimeList = overtimeRepository.findByEmployeeIdAndDeleteFlagOrderByUpdatedAtDesc(idEmployee, 0, PageRequest.of(evalPage, evalPageSize));
        ArrayList<Object> result = new ArrayList<>();

        mapOvertime(overtimeList,result);

        //list overtime of employee in month now
        ArrayList<Overtime> listOvertimeMonthNow = overtimeRepository.findMonthNow(idEmployee);

        for (Overtime obj : listOvertimeMonthNow){
            if(obj.getOvertimeStatuses().getName().equals("Rejected") ||obj.getOvertimeStatuses().getName().equals("Accepted")){
                switch (obj.getDayTypes().getNameDayType()){
                    case "normal":
                        normal += obj.getCorrectTotalTime();
                        break;
                    case "weekend":
                        dayOff += obj.getCorrectTotalTime();
                        break;
                    default:
                        holiday += obj.getCorrectTotalTime();
                }
            }

        }
        return new ListOvertimeDTO(normal, dayOff, holiday, new ListDTO(listOvertimeMonthNow.size(), result));
    }

    public ListDTO getListOvertimeEmployeeHR(HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {
        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        Employee employeeCEO = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)),0,0);
        ArrayList<Overtime> listOT = new ArrayList<>();
        int total;
        if(employeeCEO.getIsManager() == 1){
            //this is CEO
            //can accept or reject form OT
            listOT = overtimeRepository.findOTOfPoOrHr(PageRequest.of(evalPage, evalPageSize));
            total = listOT.size();
        }else{
           listOT = overtimeRepository.findByDeleteFlagOrderByUpdatedAtDesc(0, PageRequest.of(evalPage, evalPageSize));
            total = overtimeRepository.findByDeleteFlagOrderByUpdatedAtDesc(0).size();
        }


        ArrayList<Object> result = new ArrayList<>();

        mapOvertime(listOT, result);

        return new ListDTO(total, result);
    }

    public ListDTO getListOvertimeProjectManageRolePO(String idProject, HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {

        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;


            //get project by id
            Project project = projectRepository.findByIdProjectAndEndDateAndDeleteFlag(idProject, null,0);
           if(project == null){
               throw new CustomException("project not found", 404);
           }

            //find po of project
            //xác định xem có đúng project mà employee đó làm PO hay k?

            Employee employeePO = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)),0,0);

            Processes obj = processesRepository.findByEmployeeIdAndProjectIdAndRoleIdAndCheckProjectExitAndDeleteFlag(
                    employeePO.getIdEmployee(), idProject, roleRepository.findByNameRole("PO").getIdRole(), 0, 0);
            if(obj == null){
                throw new CustomException("Access Denied Exception", 403);

            }

            ArrayList<Overtime> overtimeArrayList = overtimeRepository.findOTByIdProject(project.getIdProject(),employeePO.getIdEmployee(), PageRequest.of(evalPage, evalPageSize));

            ArrayList<Object> listResult = new ArrayList<>();

            mapOvertime(overtimeArrayList, listResult);

            return new ListDTO(overtimeRepository.findOTByIdProject(idProject,employeePO.getIdEmployee()).size(), listResult);

    }

    public void mapOvertime(ArrayList<Overtime> overtimeList, ArrayList<Object> result){
        OvertimeDTO overTimeDTO = new OvertimeDTO();
        for (Overtime obj : overtimeList){
            overTimeDTO = modelMapper.map(obj, overTimeDTO.getClass());
            overTimeDTO.setIdEmployee(obj.getEmployeeId());
            overTimeDTO.setNameEmployee(obj.getEmployee().getNameEmployee());
            if(obj.getProcesses() != null){
                overTimeDTO.setIdProject(obj.getProcesses().getProjectId());
                overTimeDTO.setNameProject(obj.getProcesses().getProject().getNameProject());
            }

            result.add(overTimeDTO);
        }

    }
}
