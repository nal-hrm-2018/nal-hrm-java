package nals.hrm.api_nals_hrm.respository;

import nals.hrm.api_nals_hrm.entities.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
    Employee findByEmail(String email);

    List<Employee> findByIsEmployeeAndDeleteFlag(int isEmployee, int deleteFlag, Pageable pageable);

}
