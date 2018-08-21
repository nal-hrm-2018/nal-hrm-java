package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Absence;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AbsenceRepository extends CrudRepository<Absence, Integer> {
    List<Absence> findByEmployeeId(int idEmployee);

    ArrayList<Object> findByEmployeeId(int idEmployee, Pageable pageable);
}
