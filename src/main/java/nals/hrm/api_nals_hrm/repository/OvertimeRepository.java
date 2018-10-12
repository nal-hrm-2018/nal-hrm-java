package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Overtime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface OvertimeRepository extends PagingAndSortingRepository<Overtime, Integer> {

  ArrayList<Overtime> findByEmployeeIdAndDeleteFlagOrderByUpdatedAtDesc(int idEmployee, int deleteFlag, Pageable pageable);

  @Query(value = "SELECT * FROM overtime WHERE MONTH(overtime.date) = MONTH(NOW()) AND YEAR(overtime.date) = YEAR(NOW()) AND employee_id = ?1 AND delete_flag = 0\n" +
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
          "ORDER BY overtime.updated_at DESC, overtime.overtime_status_id ASC", nativeQuery = true)
  ArrayList<Overtime> findOTByIdProject(String idProject, int idEmployee, Pageable pageable);

  @Query(value = "SELECT * FROM overtime LEFT OUTER JOIN processes ON overtime.process_id = processes.id\n" +
          "WHERE (overtime.process_id IS NULL OR (processes.role_id = 4 AND processes.check_project_exit = 0 AND processes.delete_flag = 0)) \n" +
          "AND overtime.delete_flag = 0 \n" +
          "ORDER BY overtime.updated_at DESC, overtime.overtime_status_id ASC", nativeQuery = true)
  ArrayList<Overtime> findOTCEO(Pageable pageable);

  @Query(value = "SELECT * FROM overtime LEFT OUTER JOIN processes ON overtime.process_id = processes.id\n" +
          "WHERE (overtime.process_id IS NULL OR (processes.role_id = 4 AND processes.check_project_exit = 0 AND processes.delete_flag = 0)) \n" +
          "AND overtime.delete_flag = 0 \n" +
          "ORDER BY overtime.updated_at DESC, overtime.overtime_status_id ASC ", nativeQuery = true)
  ArrayList<Overtime> findOTCEO();

  @Query(value = "SELECT * FROM overtime INNER JOIN overtime_statuses ON overtime.overtime_status_id = overtime_statuses.id\n" +
          "WHERE (overtime_statuses.name = \"Accepted\" OR overtime_statuses.name = \"Rejected\")\n" +
          "AND overtime.delete_flag = 0\n" +
          "ORDER BY overtime.updated_at DESC", nativeQuery = true)
  ArrayList<Overtime> findOTHR(Pageable pageable);


  @Query(value = "SELECT * FROM overtime INNER JOIN overtime_statuses ON overtime.overtime_status_id = overtime_statuses.id\n" +
          "WHERE (overtime_statuses.name = \"Accepted\" OR overtime_statuses.name = \"Rejected\")\n" +
          "AND overtime.delete_flag = 0\n" +
          "ORDER BY overtime.updated_at DESC", nativeQuery = true)
  ArrayList<Overtime> findOTHR();

  ArrayList<Overtime> findByEmployeeIdAndDeleteFlagOrderByUpdatedAtDesc(int idEmployee, int i);

//    List<Overtime> findByEmployeeIdAndDateAndDeleteFlag(int idEmployee, String date, int deleteFlag);

  Overtime findByIdAndDeleteFlag(int id, int i);

  @Query(value = "SELECT * FROM overtime INNER JOIN overtime_statuses ON overtime.overtime_status_id = overtime_statuses.id\n" +
          "WHERE (overtime_statuses.name = \"Accepted\" OR overtime_statuses.name = \"Rejected\")\n" +
          "AND employee_id = ?1\n" +
          "AND overtime.delete_flag = 0\n" +
          "ORDER BY overtime.updated_at DESC", nativeQuery = true)
  ArrayList<Overtime> findByEmployeeIdHR(int idEmployee, Pageable pageable);

  @Query(value = "SELECT * FROM overtime INNER JOIN overtime_statuses ON overtime.overtime_status_id = overtime_statuses.id\n" +
          "WHERE (overtime_statuses.name = \"Accepted\" OR overtime_statuses.name = \"Rejected\")\n" +
          "AND employee_id = ?1\n" +
          "AND overtime.delete_flag = 0\n" +
          "ORDER BY overtime.updated_at DESC", nativeQuery = true)
  ArrayList<Overtime> findByEmployeeIdHR(int idEmployee);

  Overtime findByIdAndEmployeeIdAndDeleteFlag(int id, int idEmployee, int i);


  @Query(value = "SELECT * FROM overtime INNER JOIN employees ON overtime.employee_id = employees.id\n" +
          "LEFT OUTER JOIN processes ON overtime.process_id = processes.id\n" +
          "WHERE ((employees.name LIKE %?1% AND employees.delete_flag = 0)\n" +
          "OR DATE(overtime.date) = ?1 OR MONTH(overtime.date) = ?1 OR YEAR(overtime.date) =?1\n" +
          "OR overtime.process_id\n" +
          "IN(SELECT processes.id FROM processes INNER JOIN projects \n" +
          "ON processes.project_id = projects.id WHERE projects.name LIKE %?1% \n" +
          "AND projects.delete_flag = 0 AND processes.delete_flag = 0))\n" +
          "AND ((overtime.process_id IS NULL OR (processes.role_id = 4 AND processes.check_project_exit = 0 AND processes.delete_flag = 0)) AND overtime.delete_flag = 0)\n" +
          "ORDER BY overtime.updated_at DESC, overtime.overtime_status_id ASC", nativeQuery = true)
  ArrayList<Overtime> searchOTCEO(String keyword, Pageable pageable);

  @Query(value = "SELECT * FROM overtime INNER JOIN employees ON overtime.employee_id = employees.id\n" +
          "LEFT OUTER JOIN processes ON overtime.process_id = processes.id\n" +
          "WHERE ((employees.name LIKE %?1% AND employees.delete_flag = 0)\n" +
          "OR DATE(overtime.date) = ?1 OR MONTH(overtime.date) = ?1 OR YEAR(overtime.date) =?1\n" +
          "OR overtime.process_id\n" +
          "IN(SELECT processes.id FROM processes INNER JOIN projects \n" +
          "ON processes.project_id = projects.id WHERE projects.name LIKE %?1% \n" +
          "AND projects.delete_flag = 0 AND processes.delete_flag = 0))\n" +
          "AND ((overtime.process_id IS NULL OR (processes.role_id = 4 AND processes.check_project_exit = 0 AND processes.delete_flag = 0)) AND overtime.delete_flag = 0)\n" +
          "ORDER BY overtime.updated_at DESC, overtime.overtime_status_id ASC", nativeQuery = true)
  ArrayList<Overtime> searchOTCEO(String keyword);


  @Query(value = "SELECT * FROM overtime INNER JOIN overtime_statuses ON overtime.overtime_status_id = overtime_statuses.id\n" +
          "INNER JOIN employees ON overtime.employee_id = employees.id\n" +
          "WHERE ((employees.name LIKE %?1% AND employees.delete_flag = 0) \n" +
          "OR DATE(overtime.date) = ?1 OR MONTH(overtime.date) = ?1 OR YEAR(overtime.date) = ?1 \n" +
          "OR overtime.process_id \n" +
          "IN(SELECT processes.id FROM processes INNER JOIN projects ON processes.project_id = projects.id \n" +
          "WHERE projects.name LIKE %?1% AND projects.delete_flag = 0 AND processes.delete_flag = 0))\n" +
          "AND ((overtime_statuses.name = \"Accepted\" OR overtime_statuses.name = \"Rejected\")\n" +
          "AND overtime.delete_flag = 0)\n" +
          "ORDER BY overtime.updated_at DESC", nativeQuery = true)
  ArrayList<Overtime> searchOTHR(String keyword, Pageable pageable);

  @Query(value = "SELECT * FROM overtime INNER JOIN overtime_statuses ON overtime.overtime_status_id = overtime_statuses.id\n" +
          "INNER JOIN employees ON overtime.employee_id = employees.id\n" +
          "WHERE ((employees.name LIKE %?1% AND employees.delete_flag = 0) \n" +
          "OR DATE(overtime.date) = ?1 OR MONTH(overtime.date) = ?1 OR YEAR(overtime.date) = ?1 \n" +
          "OR overtime.process_id \n" +
          "IN(SELECT processes.id FROM processes INNER JOIN projects ON processes.project_id = projects.id \n" +
          "WHERE projects.name LIKE %?1% AND projects.delete_flag = 0 AND processes.delete_flag = 0))\n" +
          "AND ((overtime_statuses.name = \"Accepted\" OR overtime_statuses.name = \"Rejected\")\n" +
          "AND overtime.delete_flag = 0)\n" +
          "ORDER BY overtime.updated_at DESC", nativeQuery = true)
  ArrayList<Overtime> searchOTHR(String keyword);

//    Overtime save(Overtime overtime);

}
