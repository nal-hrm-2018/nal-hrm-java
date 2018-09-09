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


    List<Processes> findByemployeeIdAndCheckProjectExitAndDeleteFlag(int idEmployee, int i, int i1);

    Processes findByProjectIdAndCheckProjectExitAndRoleIdAndDeleteFlag(String projectId, int i, int idRole, int i1);
}
