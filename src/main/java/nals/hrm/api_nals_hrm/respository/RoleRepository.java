package nals.hrm.api_nals_hrm.respository;


import nals.hrm.api_nals_hrm.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByIdRole(int idRole);
}
