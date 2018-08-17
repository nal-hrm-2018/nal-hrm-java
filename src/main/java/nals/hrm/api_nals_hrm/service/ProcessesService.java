package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.entities.Processes;
import nals.hrm.api_nals_hrm.repository.ProcessesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProcessesService {
    @Autowired
    ProcessesRepository processesRepository;

    public List<Processes> findByEmployeeId(int idEmployee) {
        return processesRepository.findByEmployeeId(idEmployee);
    }
}
