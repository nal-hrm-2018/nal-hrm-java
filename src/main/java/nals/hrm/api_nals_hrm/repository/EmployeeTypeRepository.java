package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.EmployeeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeTypeRepository extends CrudRepository<EmployeeType, Integer> {

    EmployeeType findByIdEmployeeType(int employeeTypeId);

    List<EmployeeType> findAll();
}
