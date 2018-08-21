package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Processes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcessesRepository extends CrudRepository<Processes,Integer> {
    List<Processes> findByEmployeeId(int idEmployee, Pageable pageable);

    List<Processes> findByEmployeeId(int idEmployee);
}
