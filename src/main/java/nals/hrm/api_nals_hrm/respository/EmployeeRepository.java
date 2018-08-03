package nals.hrm.api_nals_hrm.respository;

import nals.hrm.api_nals_hrm.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findByEmail(String email);
//    Employee findByIdEmployee(int idEmployee);

//    Employee findByEmailAndPassword(String email, String password);
}
