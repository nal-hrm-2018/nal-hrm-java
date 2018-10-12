package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.entities.Team;
import nals.hrm.api_nals_hrm.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamService {

  @Autowired
  TeamRepository teamRepository;

  public List<Team> findAll() {
    return teamRepository.findAll();
  }
}
