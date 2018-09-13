package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Processes;
import nals.hrm.api_nals_hrm.entities.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

    Project findByIdProjectAndDeleteFlag(String projectId, int deleteFlag);

    @Query(value = "SELECT * FROM projects INNER JOIN processes ON processes.project_id = projects.id\n" +
            "WHERE projects.end_date IS NULL\n" +
            "AND processes.employee_id = ?1\n" +
            "AND processes.role_id = ?2\n" +
            "AND processes.check_project_exit = ?3\n" +
            "AND processes.delete_flag = ?4\n" +
            "AND projects.delete_flag = 0", nativeQuery = true)
    ArrayList<Project> findProjectProcessesAndNotEnd(int idEmployee, int po, int checkProjectExit, int deleteFlag, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM projects INNER JOIN  processes ON processes.project_id = projects.id\n" +
            "WHERE projects.end_date IS NULL\n" +
            "AND processes.employee_id = ?1\n" +
            "AND processes.role_id = ?2\n" +
            "AND processes.check_project_exit = ?3\n" +
            "AND processes.delete_flag = ?4\n" +
            "AND projects.delete_flag = 0", nativeQuery = true)
    int findProjectProcessesAndNotEnd(int idEmployee, int po, int checkProjectExit, int deleteFlag);
}
