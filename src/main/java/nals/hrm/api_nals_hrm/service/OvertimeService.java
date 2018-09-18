package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.dto.ListOvertimeDTO;
import nals.hrm.api_nals_hrm.dto.OverTimeDTO;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.entities.OverTime;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.repository.EmployeeRepository;
import nals.hrm.api_nals_hrm.repository.OvertimeRepository;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
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
    private ModelMapper modelMapper;

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

        ArrayList<OverTime> overTimeList = overtimeRepository.findByEmployeeIdAndDeleteFlagOrderByUpdatedAtDesc(idEmployee, 0, PageRequest.of(evalPage, evalPageSize));
        ArrayList<Object> result = new ArrayList<>();
        OverTimeDTO overTimeDTO = new OverTimeDTO();

        Date dateOvertime;
        Date now = new Date();

        for (OverTime obj : overTimeList){
            overTimeDTO = modelMapper.map(obj, overTimeDTO.getClass());
            result.add(overTimeDTO);
        }

        //list all overtime by id not paging

        ArrayList<OverTime> listAllByIdEmployee = overtimeRepository.findByEmployeeIdAndDeleteFlag(idEmployee,0);

        for (OverTime obj : listAllByIdEmployee){
            //chi thong ke nhung don trong thang hiện tại

            try {
                dateOvertime = new SimpleDateFormat("yyyy-MM-dd").parse(obj.getDate());
            } catch (ParseException e) {
                throw new CustomException("Error server", 500);
            }

            if((now.getMonth() == dateOvertime.getMonth()) && (obj.getOvertimeStatuses().getName().equals("Rejected") ||obj.getOvertimeStatuses().getName().equals("Accepted"))){
                switch (obj.getOvertimeTypes().getName()){
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
        return new ListOvertimeDTO(normal, dayOff, holiday, new ListDTO(listAllByIdEmployee.size(), result));
    }

    public ListDTO getListOvertimeEmployeeHR(Optional<Integer> page, Optional<Integer> pageSize) {
        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        ArrayList<OverTime> listAll = overtimeRepository.findByDeleteFlagOrderByUpdatedAtDesc(0, PageRequest.of(evalPage, evalPageSize));
        ArrayList<Object> result = new ArrayList<>();

        OverTimeDTO overTimeDTO = new OverTimeDTO();
        for (OverTime obj : listAll){
            overTimeDTO = modelMapper.map(obj, overTimeDTO.getClass());
            result.add(overTimeDTO);
        }

        return new ListDTO(overtimeRepository.findByDeleteFlagOrderByUpdatedAtDesc(0).size(), result);
    }

    public Object getListOvertimeProjectManageRolePO(String id, Optional<Integer> page, Optional<Integer> pageSize) {
        return null;
    }
}
