package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.dto.OverTimeDTO;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.entities.OverTime;
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
import java.util.ArrayList;
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

    public ListDTO getListOvertimeByToken(HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {
        Employee employee = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)),0 , 0);
        return getListOvertimeByIdEmployee(employee.getIdEmployee(),page,pageSize);
    }

    public ListDTO getListOvertimeByIdEmployee(int idEmployee, Optional<Integer> page, Optional<Integer> pageSize) {

        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        ArrayList<OverTime> overTimeList = overtimeRepository.findByEmployeeIdAndDeleteFlagOrderByUpdatedAtDesc(idEmployee, 0, PageRequest.of(evalPage, evalPageSize));
        ArrayList<Object> result = new ArrayList<>();
        OverTimeDTO overTimeDTO = new OverTimeDTO();

        for (OverTime obj : overTimeList){
            overTimeDTO = modelMapper.map(obj, overTimeDTO.getClass());
            result.add(overTimeDTO);
        }
        return new ListDTO(overtimeRepository.findByEmployeeIdAndDeleteFlag(idEmployee,0).size(), result);
    }
}
