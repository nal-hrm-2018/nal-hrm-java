package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.HolidayDefault;
import org.springframework.data.repository.CrudRepository;

public interface HolidayDefaultRepository extends CrudRepository<HolidayDefault, Integer> {
    HolidayDefault findByDateHolidayDefaultAndDeleteFlag(String strFrom, int deleteFlag);
}
