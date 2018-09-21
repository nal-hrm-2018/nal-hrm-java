package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
//    Employee findByEmail(String email);

    List<Employee> findByIsEmployeeAndDeleteFlag(int isEmployee, int deleteFlag, Pageable pageable);

    Employee findByIdEmployeeAndIsEmployeeAndDeleteFlag(int id, int isEmployee, int deleteFlag);

    List<Employee> findByIsEmployeeAndDeleteFlag(int isEmployee, int deleteFlag);

    List<Employee> findByEmailContainingOrNameEmployeeContainingAndIsEmployeeAndDeleteFlag(String email, String nameEmployee, int isEmployee, int deleteFlag, Pageable pageable);

    List<Employee> findByEmailContainingOrNameEmployeeContainingAndIsEmployeeAndDeleteFlag(String email, String name, int i, int i1);

    @Query(value = "SELECT * FROM employees  INNER JOIN processes ON processes.employee_id = employees.id\n" +
            "WHERE processes.project_id = ?1 \n" +
            "AND processes.check_project_exit = 0 AND processes.delete_flag = 0 AND employees.delete_flag = 0", nativeQuery = true)
    List<Employee> findAllNotExit(String id);

    //all employee or vendor can login
    //if workStatus = 0
    Employee findByEmailAndDeleteFlagAndWorkStatus(String username, int deleteFlag, int workStatus);
}
