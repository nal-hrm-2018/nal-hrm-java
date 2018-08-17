package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.dto.EmployeeProjectDTO;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.entities.Project;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.repository.EmployeeRepository;
import nals.hrm.api_nals_hrm.repository.ProcessesRepository;
import nals.hrm.api_nals_hrm.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProcessesRepository processesRepository;


    @Autowired
    private ModelMapper modelMapper;

    public Project findByIdProject(String idProject) {
        return projectRepository.findByIdProject(idProject);
    }

    public List<EmployeeProjectDTO> getListProjectByIdEmployee(int id) {
        int idEmployee = 0;
        try{
           idEmployee = id;
        }catch (NumberFormatException e){
            throw new CustomException("No find employee", 404);
        }
        try {

            ArrayList<EmployeeProjectDTO> result = new ArrayList<>();//all information of project
            //find employee by id
            Employee employee = employeeRepository.findByIdEmployee(idEmployee);

            EmployeeProjectDTO projectDTO;
            //list project employee joined
            List<Project> listProject = employee.getProjects();
            for (Project project : listProject) {
                projectDTO = new EmployeeProjectDTO(project, processesRepository.findByEmployeeIdAndProjectId(idEmployee, project.getIdProject()));
                result.add(projectDTO);
            }
            return result;

        } catch (Exception e) {
            throw new CustomException("No find employee", 404);
        }

    }
}
