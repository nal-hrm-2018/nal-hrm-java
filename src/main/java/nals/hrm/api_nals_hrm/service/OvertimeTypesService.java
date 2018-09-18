package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.entities.OvertimeTypes;
import nals.hrm.api_nals_hrm.repository.OvertimeTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OvertimeTypesService {

    @Autowired
    OvertimeTypesRepository overtimeTypesRepository;

    public List<OvertimeTypes> findAll() {
        return overtimeTypesRepository.findAll();
    }
}
