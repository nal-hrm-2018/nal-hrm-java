package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
    Employee findByEmail(String email);

    Employee findByIdEmployee(int idEmployee);

    @PreAuthorize("hasAuthority('view_list_employee')")
    List<Employee> findByIsEmployeeAndDeleteFlag(int isEmployee, int deleteFlag, Pageable pageable);

    Employee findByIdEmployeeAndIsEmployeeAndDeleteFlag(int id, int isEmployee, int deleteFlag);

    List<Employee> findByIsEmployeeAndDeleteFlag(int isEmployee, int deleteFlag);

    Employee findByIdEmployeeAndDeleteFlag(int idEmployee, int deleteFlag);
}
