package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Holiday;
import org.springframework.data.repository.CrudRepository;

public interface HolidayRepository extends CrudRepository<Holiday, Integer> {
    Holiday findByDateHolidayAndDeleteFlag(String strFrom,int deleteFlag);
}
