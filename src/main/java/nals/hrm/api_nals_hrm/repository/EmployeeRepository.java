package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
    Employee findByEmail(String email);

    @PreAuthorize("hasAuthority('view_list_employee')")
    List<Employee> findByIsEmployeeAndDeleteFlag(int isEmployee, int deleteFlag, Pageable pageable);

    Employee findByIdEmployeeAndIsEmployeeAndDeleteFlag(int id, int isEmployee, int deleteFlag);

    List<Employee> findByIsEmployeeAndDeleteFlag(int isEmployee, int deleteFlag);

    List<Employee> findByEmailContainingOrNameEmployeeContainingAndIsEmployeeAndDeleteFlag(String email, String nameEmployee, int isEmployee, int deleteFlag, Pageable pageable);

    List<Employee> findByEmailContainingOrNameEmployeeContainingAndIsEmployeeAndDeleteFlag(String email, String name, int i, int i1);
}
