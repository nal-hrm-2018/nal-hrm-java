package nals.hrm.api_nals_hrm.respository;

import nals.hrm.api_nals_hrm.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findByEmail(String email);

    Employee findByIdEmployee(int idEmployee);

    List<Employee> findByDeleteFlag(int deleteFlag);

    List<Employee> findByIsEmployeeAndDeleteFlag(int isEmployee, int deleteFlag);
}
