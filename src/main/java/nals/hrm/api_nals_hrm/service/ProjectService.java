package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.EmployeeProjectDTO;
import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.dto.ProjectDTO;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.entities.Processes;
import nals.hrm.api_nals_hrm.entities.Project;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.repository.EmployeeRepository;
import nals.hrm.api_nals_hrm.repository.ProcessesRepository;
import nals.hrm.api_nals_hrm.repository.ProjectRepository;
import nals.hrm.api_nals_hrm.repository.RoleRepository;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;


    //all project (exit or not exit) by id
    public ListDTO getListProjectByIdEmployee(int idEmployee, Optional<Integer> page, Optional<Integer> pageSize) {
        try {
            ArrayList<Object> result = new ArrayList<>();//arrayList save all information the project of employee

            int evalPageSize = pageSize.orElse(Define.initialPageSize);
            int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

            //find processes the projects of employee
            //This means that all the projects that the employee has joined
            //paging result
            List<Processes> processesList = processesRepository.findByEmployeeIdAndDeleteFlag(
                    idEmployee, 0,PageRequest.of(evalPage, evalPageSize));
            EmployeeProjectDTO projectDTO;
            for (Processes processes : processesList) {
                projectDTO = new EmployeeProjectDTO(projectRepository.findByIdProjectAndDeleteFlag(
                        processes.getProjectId(),0), processes);
                result.add(projectDTO);
            }
            return new ListDTO(processesRepository.findByEmployeeIdAndDeleteFlag(idEmployee,0).size(), result);

        } catch (Exception e) {
            throw new CustomException("No find employee", 404);
        }

    }

    public ListDTO findProjectProcessesManageRolePO(HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {

        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        //find employee by token
        Employee employeePO = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(
                jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)), 0, 0);

        //get list project(in processes) manage role PO
        //project not end
        ArrayList<Project> projectRolePO = projectRepository.findProjectProcessesAndNotEnd(
                employeePO.getIdEmployee(),roleRepository.findByNameRole("PO").getIdRole(),
                0,0, PageRequest.of(evalPage, evalPageSize));
        ArrayList<Object> result = new ArrayList<>();
        ProjectDTO projectDTO = new ProjectDTO();
        for (Project objProject: projectRolePO) {
            projectDTO = modelMapper.map(objProject,projectDTO.getClass());
            projectDTO.setTotalMember(objProject.getEmployeeList().size());
            projectDTO.setNamePO(employeePO.getNameEmployee());
            result.add(projectDTO);
        }
        return new ListDTO(projectRepository.findProjectProcessesAndNotEnd(employeePO.getIdEmployee(),
                roleRepository.findByNameRole("PO").getIdRole(),0,0),result);
    }

    public List<ProjectDTO> getListJoiningProjects(HttpServletRequest req) {
        //find employee by token
        Employee employee = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(
                jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)), 0, 0);

        List<Processes> listJoiningProjects = processesRepository.findListJoiningProject(
                employee.getIdEmployee());

        ArrayList<ProjectDTO> result = new ArrayList<>();
        ProjectDTO projectDTO = new ProjectDTO();
        List<Processes> processesList;
        Employee employeePO;

        for (Processes objProcesses : listJoiningProjects){
            projectDTO = modelMapper.map(objProcesses.getProject(), projectDTO.getClass());
            projectDTO.setTotalMember(objProcesses.getProject().getEmployeeList().size());
            //tim PO cua du an
            processesList = processesRepository.findByProjectIdAndCheckProjectExitAndRoleIdAndDeleteFlag(objProcesses.getProjectId(), 0, 4, 0);
//            employeePO = employeeRepository.findByIdEmployeeAndIsEmployeeAndDeleteFlag(processesList.get(0).getEmployeeId(), 1, 0);
            employeePO = processesList.get(0).getEmployee();
            if(employeePO.getNameEmployee() != null){
                projectDTO.setNamePO(employeePO.getNameEmployee());
            }
            result.add(projectDTO);
        }
        return result;
    }

    public List<ProjectDTO> projectCompanyDashboard() {

        //list project dang dien ra cua cong ty
        List<Project> projectList = projectRepository.projectCompany();
        ArrayList<ProjectDTO> result = new ArrayList<>();
        ProjectDTO projectDTO = new ProjectDTO();
        List<Processes> processesList;
        Employee employeePO;
        for (Project objProject : projectList){
            projectDTO = modelMapper.map(objProject, projectDTO.getClass());
            projectDTO.setTotalMember(objProject.getEmployeeList().size());
            //tim PO cua du an
            processesList = processesRepository.findByProjectIdAndCheckProjectExitAndRoleIdAndDeleteFlag(objProject.getIdProject(), 0, 4, 0);
//            employeePO = employeeRepository.findByIdEmployeeAndIsEmployeeAndDeleteFlag(processesList.get(0).getEmployeeId(), 1, 0);
            employeePO = processesList.get(0).getEmployee();
            if(employeePO.getNameEmployee() != null){
                projectDTO.setNamePO(employeePO.getNameEmployee());
            }
            result.add(projectDTO);
        }
        return result;
    }
}
