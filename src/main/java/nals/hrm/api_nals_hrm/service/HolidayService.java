package nals.hrm.api_nals_hrm.service;


import nals.hrm.api_nals_hrm.entities.Holiday;
import nals.hrm.api_nals_hrm.entities.HolidayDefault;
import nals.hrm.api_nals_hrm.repository.HolidayDefaultRepository;
import nals.hrm.api_nals_hrm.repository.HolidayRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HolidayService {

    @Autowired
    HolidayRepository holidayRepository;

    @Autowired
    HolidayDefaultRepository holidayDefaultRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Holiday> getListHoliday() {
        List<Holiday> holidays = holidayRepository.findHoliday();
        List<HolidayDefault> holidayDefaults = holidayDefaultRepository.findHolidayDefault();
        holidays.addAll(modelMapper.map(holidayDefaults, holidays.getClass()));
        return holidays;
    }
}
