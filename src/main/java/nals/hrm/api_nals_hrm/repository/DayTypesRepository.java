package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.DateTypes;
import org.springframework.data.repository.CrudRepository;

public interface HolidayStatusRepository extends CrudRepository<DateTypes, Integer> {
}
