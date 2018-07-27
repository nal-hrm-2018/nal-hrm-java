package nals.tuyen.nals_hrm.respository;

import nals.tuyen.nals_hrm.entities.Employees;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeesRepository extends CrudRepository<Employees, Integer> {
    Employees findByEmail(String email);

    Employees findByEmailAndPassword(String email, String password);
}
