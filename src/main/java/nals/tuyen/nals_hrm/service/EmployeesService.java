package nals.tuyen.nals_hrm.service;

import nals.tuyen.nals_hrm.entities.Employees;
import nals.tuyen.nals_hrm.respository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeesService {
    @Autowired
    EmployeesRepository employeesRepository;

    public Employees findByEmail(String email) {
        return employeesRepository.findByEmail(email);
    }
}
