package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Processes;
import nals.hrm.api_nals_hrm.entities.Project;
import org.springframework.data.domain.PageRequest;
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
}
