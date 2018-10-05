package nals.hrm.api_nals_hrm;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        ApiNalsHrmApplication.class,
        Jsr310JpaConverters.class
})
public class ApiNalsHrmApplication extends SpringBootServletInitializer {
//    @PostConstruct
//    void init() {
//        TimeZone.setDefault(TimeZone.getTimeZone("ICT"));
//    }

  public static void main(String[] args) {
    SpringApplication.run(ApiNalsHrmApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}