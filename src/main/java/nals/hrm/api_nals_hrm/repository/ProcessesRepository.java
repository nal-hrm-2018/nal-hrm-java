package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Processes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ProcessesRepository extends CrudRepository<Processes,Integer> {


    List<Processes> findByEmployeeIdAndDeleteFlag(int idEmployee, int deleteFlag,Pageable pageable);

    List<Processes> findByEmployeeIdAndDeleteFlag(int idEmployee, int deleteFlag);

    List<Processes> findByEmployeeIdAndCheckProjectExitAndDeleteFlag(int idEmployee, int checkProjectExit, int deleteFlag);

    List<Processes> findByProjectIdAndCheckProjectExitAndRoleIdAndDeleteFlag(String projectId, int checkProjectExit, int idRole, int deleteFlag);

    Processes findByEmployeeIdAndProjectIdAndRoleIdAndCheckProjectExitAndDeleteFlag(int idEmployee, String idProject, int rolePO, int checkProjectExit, int deleteFlag);

    Processes findByProjectIdAndEmployeeIdAndCheckProjectExitAndDeleteFlag(String idProject, int idEmployee, int checkProjectExit, int deleteFlag);

    @Query(value = "SELECT * FROM processes INNER JOIN projects ON processes.project_id = projects.id\n" +
            "WHERE projects.end_date IS NULL AND projects.delete_flag = 0\n" +
            "AND processes.employee_id = ?1 AND processes.check_project_exit = 0\n" +
            "AND processes.delete_flag = 0", nativeQuery = true)
    List<Processes> findListJoiningProject(int idEmployee);
}
