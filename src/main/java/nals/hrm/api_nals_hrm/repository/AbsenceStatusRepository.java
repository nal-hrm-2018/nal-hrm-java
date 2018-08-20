package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.AbsenceStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceStatusRepository extends CrudRepository<AbsenceStatus, Integer> {
}
