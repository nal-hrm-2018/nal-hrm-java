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
    List<Processes> findByEmployeeId(int idEmployee, Pageable pageable);

    List<Processes> findByEmployeeId(int idEmployee);

    ArrayList<Processes> findByEmployeeIdAndRoleIdAndDeleteFlag(int employeeId, int roleId, int deleteFlag);


}
