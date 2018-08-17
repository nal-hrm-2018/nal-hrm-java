package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Processes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcessesRepository extends CrudRepository<Processes,Integer> {
    List<Processes> findByEmployeeId(int idEmployee);

    Processes findByEmployeeIdAndProjectId(int idEmployee, String idProject);
}
