package nals.hrm.api_nals_hrm.security;


import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.entities.Permission;
import nals.hrm.api_nals_hrm.entities.Role;
import nals.hrm.api_nals_hrm.repository.EmployeeRepository;
import nals.hrm.api_nals_hrm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Employee employee = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(username, 0, 0);

    if (employee == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }


    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    Role role = roleRepository.findByIdRole(employee.getIdRole());
//
    grantedAuthorities.add(new SimpleGrantedAuthority(role.getNameRole()));

    List<Permission> listPermissions = employee.getPermissions();
    for (Permission objPermission : listPermissions) {
      grantedAuthorities.add(new SimpleGrantedAuthority(objPermission.getNamePermission()));
    }

    if (employee.getIsManager() == 1) {
      grantedAuthorities.add(new SimpleGrantedAuthority("CEO"));
    }


    return org.springframework.security.core.userdetails.User//
            .withUsername(username)//
            .password(employee.getPassword())//
            .authorities(grantedAuthorities)//
            .accountExpired(false)//
            .accountLocked(false)//
            .credentialsExpired(false)//
            .disabled(false)//
            .build();
  }

}
