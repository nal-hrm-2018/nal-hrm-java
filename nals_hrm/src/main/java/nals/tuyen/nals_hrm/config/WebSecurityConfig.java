package nals.tuyen.nals_hrm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity https) throws Exception {
    https
            .authorizeRequests()
              .antMatchers("/show-login","/resources/**").permitAll()
              .antMatchers("/").hasRole("DEV")
              .antMatchers("/hr").hasRole("HR")
              .and()
            .formLogin()
              .loginPage("/show-login")
              .usernameParameter("username")
              .passwordParameter("password")
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

/*  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("resources/js/**","resources/css/**",
            "resources/img/**","resources/assets/**",
            "resources/contactform/**",
            "resources/fonts/**");
  }*/
}

