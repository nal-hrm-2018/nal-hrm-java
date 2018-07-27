package nals.tuyen.nals_hrm.service;

import nals.tuyen.nals_hrm.entities.Employees;
import nals.tuyen.nals_hrm.entities.Roles;
import nals.tuyen.nals_hrm.respository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("mail: "+email);
        Employees employees = employeesRepository.findByEmail(email);
//        System.out.println("emp: "+employees.toString());

        if (employees == null) {
            throw new UsernameNotFoundException("Employee not found");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Roles> roles = employees.getRoles();
        for (Roles role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getNameRole()));
        }

        return new org.springframework.security.core.userdetails.User(
                employees.getEmail(), employees.getPassword(), grantedAuthorities);
    }
}

