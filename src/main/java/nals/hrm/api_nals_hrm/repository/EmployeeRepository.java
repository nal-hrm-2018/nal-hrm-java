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

    List<Employee> findByIsEmployeeAndWorkStatusAndDeleteFlag(int isEmployee, int workStatus, int deleteFlag);

    @Query(value = "SELECT COUNT(*) FROM employees INNER JOIN employee_types\n" +
            "ON employees.employee_type_id = employee_types.id\n" +
            "WHERE employees.is_employee = 1\n" +
            "AND employees.work_status = 0\n" +
            "AND employees.delete_flag = 0\n" +
            "AND employee_types.delete_flag = 0\n" +
            "AND MONTH(NOW()) = MONTH(employees.startwork_date)\n" +
            "AND YEAR(NOW()) = YEAR(employees.startwork_date)\n" +
            "AND (employee_types.name != \"Internship\" AND employee_types.name = \"PartTime\")", nativeQuery = true)
    int newEmployee();


    @Query(value = "SELECT COUNT(*) FROM employees INNER JOIN employee_types\n" +
            "ON employees.employee_type_id = employee_types.id\n" +
            "WHERE employees.is_employee = 1\n" +
            "AND employees.work_status = 0\n" +
            "AND employees.delete_flag = 0\n" +
            "AND employee_types.delete_flag = 0\n" +
            "AND MONTH(NOW()) = MONTH(employees.birthday)\n" +
            "AND (employee_types.name != \"Internship\" AND employee_types.name != \"PartTime\")", nativeQuery = true)
    int birthdays();

    @Query(value = "SELECT COUNT(*) FROM employees INNER JOIN employee_types\n" +
            "ON employees.employee_type_id = employee_types.id\n" +
            "WHERE employees.is_employee = 1\n" +
            "AND employees.delete_flag = 0\n" +
            "AND employee_types.delete_flag = 0\n" +
            "AND MONTH(NOW()) = MONTH(employees.endwork_date)\n" +
            "AND YEAR(NOW()) = YEAR(employees.endwork_date)\n" +
            "AND (employee_types.name != \"Internship\" AND employee_types.name != \"PartTime\")", nativeQuery = true)
    int employeeQuit();
}
