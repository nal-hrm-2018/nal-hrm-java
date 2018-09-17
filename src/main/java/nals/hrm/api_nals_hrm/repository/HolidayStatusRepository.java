package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.HolidayDefault;
import nals.hrm.api_nals_hrm.entities.HolidayStatus;
import org.springframework.data.repository.CrudRepository;

public interface HolidayStatusRepository extends CrudRepository<HolidayStatus, Integer> {
}
