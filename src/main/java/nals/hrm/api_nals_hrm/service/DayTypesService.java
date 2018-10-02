package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.entities.DayTypes;
import nals.hrm.api_nals_hrm.repository.DayTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DayTypesService {

    @Autowired
    DayTypesRepository dayTypesRepository;

    public List<DayTypes> findAll() {
        return dayTypesRepository.findAll();
    }
}
