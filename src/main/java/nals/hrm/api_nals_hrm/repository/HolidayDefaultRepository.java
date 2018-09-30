package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.HolidayDefault;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HolidayDefaultRepository extends CrudRepository<HolidayDefault, Integer> {
    HolidayDefault findByDateHolidayDefaultAndDeleteFlag(String strFrom, int deleteFlag);
//    List<HolidayDefault> findByDeleteFlag(int deleteFlag);

    @Query(value = "SELECT * FROM holidays_default\n" +
            "WHERE delete_flag = 0\n" +
            "AND year(holidays_default.date) = year(NOW())", nativeQuery = true)
    List<HolidayDefault> findHolidayDefault();
}
