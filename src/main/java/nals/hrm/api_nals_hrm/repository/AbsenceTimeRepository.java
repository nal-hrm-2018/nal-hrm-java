package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.AbsenceTime;
import nals.hrm.api_nals_hrm.entities.AbsenceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceTimeRepository extends CrudRepository<AbsenceTime, Integer> {

}
