package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.repository.EmployeeRepository;
import nals.hrm.api_nals_hrm.repository.ProcessesRepository;
import nals.hrm.api_nals_hrm.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProcessesService {

  @Autowired
  ProcessesRepository processesRepository;

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  ProjectRepository projectRepository;


//    public List<> getListJoiningProjects(HttpServletRequest req) {
//    }
}
