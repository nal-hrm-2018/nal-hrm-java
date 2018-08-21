package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.entities.Absence;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.repository.AbsenceRepository;
import nals.hrm.api_nals_hrm.repository.EmployeeRepository;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AbsenceService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AbsenceRepository absenceRepository;

    public ListDTO findByIdEmployee(HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {

        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        //find employee by token
        Employee employee = employeeRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));

        //find list absence employee
        //paging result
        ArrayList<Object> absenceList = absenceRepository.findByEmployeeId(employee.getIdEmployee(), PageRequest.of(evalPage, evalPageSize));
        int total = absenceRepository.findByEmployeeId(employee.getIdEmployee()).size();
        return new ListDTO(total, absenceList);
    }
}
