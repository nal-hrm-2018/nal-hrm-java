package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.entities.Role;
import nals.hrm.api_nals_hrm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {
  @Autowired
  RoleRepository roleRepository;

  public Role findByIdRole(int idRole) {
    return roleRepository.findByIdRole(idRole);
  }

  public List<Role> findAll() {
    return roleRepository.findAll();
  }
}
