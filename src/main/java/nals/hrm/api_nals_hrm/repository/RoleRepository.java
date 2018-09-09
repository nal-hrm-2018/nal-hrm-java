package nals.hrm.api_nals_hrm.repository;


import nals.hrm.api_nals_hrm.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByIdRole(int idRole);

    List<Role> findAll();

    Role findByNameRole(String nameRole);
}
