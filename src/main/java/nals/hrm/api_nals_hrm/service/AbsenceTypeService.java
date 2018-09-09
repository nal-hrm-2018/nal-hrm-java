package nals.hrm.api_nals_hrm.service;


import nals.hrm.api_nals_hrm.entities.AbsenceType;
import nals.hrm.api_nals_hrm.repository.AbsenceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AbsenceTypeService {

    @Autowired
    AbsenceTypeRepository absenceTypeRepository;

    public List<AbsenceType> findAll() {
        return absenceTypeRepository.findTop6ByNameAbsenceTypeNotNull();
    }
}
