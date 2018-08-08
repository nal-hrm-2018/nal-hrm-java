package nals.hrm.api_nals_hrm.respository;

import nals.hrm.api_nals_hrm.entities.EmployeeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTypeRepository extends CrudRepository<EmployeeType, Integer> {
    EmployeeType findByIdEmployeeType(int employeeTypeId);
}
