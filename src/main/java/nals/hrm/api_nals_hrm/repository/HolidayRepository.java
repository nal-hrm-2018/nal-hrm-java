package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Holiday;
import nals.hrm.api_nals_hrm.entities.HolidayStatus;
import org.springframework.data.repository.CrudRepository;

public interface HolidayRepository extends CrudRepository<Holiday, Integer> {
}
