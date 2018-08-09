package nals.hrm.api_nals_hrm.config;

import nals.hrm.api_nals_hrm.filter.JwtTokenFilterConfigurer;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    protected void configure(HttpSecurity https) throws Exception {
//        https.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);
        https.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
        https.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        https.csrf().disable();
        https
                .authorizeRequests()
                .antMatchers("/show-login","/resources/**","/api/login").permitAll()
                .antMatchers("/","/api/profile")
                    .access("hasAuthority('Dev') or hasAuthority('HR') or hasAuthority('PO') or hasAuthority('ACCOUNTANT') or hasAuthority('SM')")
                .antMatchers("/api/list/employees")
                    .access("hasAuthority('HR')")
                .and()
                .formLogin()
                .loginPage("/show-login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/show-login?error")
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/403")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/show-login?logout");

    }


    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
