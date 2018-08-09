package nals.hrm.api_nals_hrm.security;


import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.entities.Role;
import nals.hrm.api_nals_hrm.respository.EmployeeRepository;
import nals.hrm.api_nals_hrm.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Employee employee = employeeRepository.findByEmail(username);

    if (employee == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }

//    return org.springframework.security.core.userdetails.User//
//        .withUsername(username)//
//        .password(employee.getPassword())//
//        .authorities(employee.getRoles())//
//        .accountExpired(false)//
//        .accountLocked(false)//
//        .credentialsExpired(false)//
//        .disabled(false)//
//        .build();

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    Role role = roleRepository.findByIdRole(employee.getIdRole());

    //add permission of employee
    System.out.println("role: "+role.toString());
    grantedAuthorities.add(new SimpleGrantedAuthority(role.getNameRole()));



    return new org.springframework.security.core.userdetails.User(
            employee.getEmail(), employee.getPassword(), grantedAuthorities);
  }

}
