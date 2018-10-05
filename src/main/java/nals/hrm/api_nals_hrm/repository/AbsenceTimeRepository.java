package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.AbsenceTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceTimeRepository extends CrudRepository<AbsenceTime, Integer> {

  AbsenceTime findByIdAbsenceTime(int absenceTypeId);
}
