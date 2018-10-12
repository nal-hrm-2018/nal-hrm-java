package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Holiday;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HolidayRepository extends CrudRepository<Holiday, Integer> {
  Holiday findByDateHolidayAndDeleteFlag(String strFrom, int deleteFlag);

  @Query(value = "SELECT * FROM holidays\n" +
          "WHERE delete_flag = 0\n" +
          "AND year(holidays.date) = year(NOW())", nativeQuery = true)
  List<Holiday> findHoliday();
}
