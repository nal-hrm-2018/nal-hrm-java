package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.entities.OvertimeStatuses;
import nals.hrm.api_nals_hrm.repository.OvertimeStatusesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OvertimeStatusesService {

  @Autowired
  OvertimeStatusesRepository overtimeStatusesRepository;

  public List<OvertimeStatuses> findAll() {
    return overtimeStatusesRepository.findAll();

  }
}
