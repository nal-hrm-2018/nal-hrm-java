package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

  Project findByIdProjectAndDeleteFlag(String projectId, int deleteFlag);

  @Query(value = "SELECT * FROM projects INNER JOIN processes ON processes.project_id = projects.id\n" +
          "WHERE projects.end_date IS NULL\n" +
          "AND processes.employee_id = ?1\n" +
          "AND processes.role_id = ?2\n" +
          "AND processes.check_project_exit = ?3\n" +
          "AND processes.delete_flag = ?4\n" +
          "AND projects.delete_flag = ?4", nativeQuery = true)
  ArrayList<Project> findProjectProcessesAndNotEnd(int idEmployee, int po, int checkProjectExit, int deleteFlag, Pageable pageable);

  @Query(value = "SELECT COUNT(*) FROM projects INNER JOIN  processes ON processes.project_id = projects.id\n" +
          "WHERE projects.end_date IS NULL\n" +
          "AND processes.employee_id = ?1\n" +
          "AND processes.role_id = ?2\n" +
          "AND processes.check_project_exit = ?3\n" +
          "AND processes.delete_flag = ?4\n" +
          "AND projects.delete_flag = ?4", nativeQuery = true)
  int findProjectProcessesAndNotEnd(int idEmployee, int po, int checkProjectExit, int deleteFlag);

  Project findByIdProjectAndEndDateAndDeleteFlag(String idProject, String ennDate, int deleteFlag);

//    List<Project> findByEndDateAndDeleteFlag(String endDate, int deleteFlag);

  @Query(value = "SELECT projects.id, projects.name, projects.income, projects.real_cost, projects.description, projects.status_id,\n" +
          "projects.estimate_start_date, projects.start_date, projects.estimate_end_date, projects.end_date,\n" +
          "projects.updated_at,projects.updated_by_employee, projects.created_at, projects.created_by_employee,\n" +
          "projects.delete_flag FROM projects INNER JOIN processes\n" +
          "ON projects.id = processes.project_id\n" +
          "WHERE projects.end_date IS NULL\n" +
          "AND projects.delete_flag = 0\n" +
          "AND processes.delete_flag = 0\n" +
          "GROUP BY projects.id, projects.name, projects.income, projects.real_cost, projects.description, projects.status_id,\n" +
          "projects.estimate_start_date, projects.start_date, projects.estimate_end_date, projects.end_date,\n" +
          "projects.updated_at,projects.updated_by_employee, projects.created_at, projects.created_by_employee,\n" +
          "projects.delete_flag", nativeQuery = true)
  List<Project> projectCompany();
}
