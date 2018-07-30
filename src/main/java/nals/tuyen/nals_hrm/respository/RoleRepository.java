package nals.tuyen.nals_hrm.respository;

import nals.tuyen.nals_hrm.entities.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Roles, Integer> {

   // Roles findByName(String name);

}
