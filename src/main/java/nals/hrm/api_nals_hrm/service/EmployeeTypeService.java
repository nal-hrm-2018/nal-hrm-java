package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.entities.EmployeeType;
import nals.hrm.api_nals_hrm.repository.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeTypeService {

  @Autowired
  EmployeeTypeRepository employeeTypeRepository;

  public List<EmployeeType> findAll() {
    return employeeTypeRepository.findAll();
  }
}
