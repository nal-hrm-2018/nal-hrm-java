package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.entities.HolidayDefault;
import nals.hrm.api_nals_hrm.repository.HolidayDefaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HolidayDefaultService {

    @Autowired
    HolidayDefaultRepository holidayDefaultRepository;

    public List<HolidayDefault> getListHolidayDefault() {
        return holidayDefaultRepository.findHolidayDefault();
    }
}
