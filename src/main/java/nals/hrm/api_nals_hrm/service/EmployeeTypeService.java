package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.entities.EmployeeType;
import nals.hrm.api_nals_hrm.respository.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeTypeService {

    @Autowired
    EmployeeTypeRepository employeeTypeRepository;
    public EmployeeType findByIdEmployeeType(int employeeTypeId) {
        return employeeTypeRepository.findByIdEmployeeType(employeeTypeId);
    }
}
