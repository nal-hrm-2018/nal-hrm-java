package nals.hrm.api_nals_hrm.respository;

import nals.hrm.api_nals_hrm.entities.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Integer> {

}
