package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.EmployeeProjectDTO;
import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.entities.Processes;
import nals.hrm.api_nals_hrm.entities.Project;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.repository.EmployeeRepository;
import nals.hrm.api_nals_hrm.repository.ProcessesRepository;
import nals.hrm.api_nals_hrm.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProcessesRepository processesRepository;


    public ListDTO getListProjectByIdEmployee(int idEmployee, Optional<Integer> page, Optional<Integer> pageSize) {
        try {
            ArrayList<Object> result = new ArrayList<>();//arrayList save all information the project of employee

            int evalPageSize = pageSize.orElse(Define.initialPageSize);
            int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

            //find processes the projects of employee
            //This means that all the projects that the employee has joined
            //paging result
            List<Processes> processesList = processesRepository.findByEmployeeId(idEmployee, PageRequest.of(evalPage, evalPageSize));

            for (Processes processes : processesList) {
                EmployeeProjectDTO projectDTO = new EmployeeProjectDTO(projectRepository.findByIdProject(processes.getProjectId()), processes);
                result.add(projectDTO);
            }
            return new ListDTO(processesRepository.findByEmployeeId(idEmployee).size(), result);

        } catch (Exception e) {
            throw new CustomException("No find employee", 404);
        }

    }
}
