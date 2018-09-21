package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Overtime;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OvertimeRepository extends PagingAndSortingRepository<Overtime, Integer> {

    ArrayList<Overtime> findByEmployeeIdAndDeleteFlagOrderByUpdatedAtDesc(int idEmployee, int deleteFlag, Pageable pageable);

    ArrayList<Overtime> findByDeleteFlagOrderByUpdatedAtDesc(int deleteFlag);

    ArrayList<Overtime> findByDeleteFlagOrderByUpdatedAtDesc(int deleteFlag, Pageable pageable);

    @Query(value = "SELECT * FROM nal_hrm.overtime WHERE MONTH(overtime.date) = MONTH(NOW()) AND YEAR(overtime.date) = YEAR(NOW()) AND employee_id = ?1 AND delete_flag = 0\n" +
            "ORDER BY updated_at DESC ", nativeQuery = true)
    ArrayList<Overtime> findMonthNow(int idEmployee);


    @Query(value = "SELECT * FROM overtime INNER JOIN processes ON overtime.process_id = processes.id\n" +
            "WHERE processes.project_id = ?1 \n" +
            "AND overtime.employee_id != ?2 AND overtime.delete_flag = 0 AND processes.delete_flag = 0\n" +
            "AND processes.check_project_exit = 0", nativeQuery = true)
    ArrayList<Overtime> findOTByIdProject(String idProject, int idEmployee);

    @Query(value = "SELECT * FROM overtime INNER JOIN processes ON overtime.process_id = processes.id\n" +
            "WHERE processes.project_id = ?1 \n" +
            "AND overtime.employee_id != ?2 AND overtime.delete_flag = 0 AND processes.delete_flag = 0\n" +
            "AND processes.check_project_exit = 0\n" +
            "ORDER BY overtime.updated_at DESC", nativeQuery = true)
    ArrayList<Overtime> findOTByIdProject(String idProject, int idEmployee, Pageable pageable);

//    @Query(value = "SELECT * FROM overtime INNER JOIN processes ON overtime.process_id = processes.id\n" +
//            "WHERE overtime.delete_flag = 0 AND processes.delete_flag = 0\n" +
//            "AND processes.check_project_exit = 0\n" +
//            "AND processes.role_id = 4\n" +
//            "ORDER BY overtime.updated_at DESC", nativeQuery = true)

    @Query(value = "SELECT * FROM overtime LEFT OUTER JOIN processes ON overtime.process_id = processes.id\n" +
            "WHERE (overtime.process_id IS NULL OR (processes.role_id = 4 AND processes.check_project_exit = 0 AND processes.delete_flag = 0)) \n" +
            "AND overtime.delete_flag = 0 \n" +
            "ORDER BY overtime.updated_at DESC", nativeQuery = true)
    ArrayList<Overtime> findOTOfPoOrHr(Pageable pageable);
}
