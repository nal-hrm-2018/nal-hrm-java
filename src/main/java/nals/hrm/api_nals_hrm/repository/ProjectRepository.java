package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
    Project findByIdProject(String idProject);
}
