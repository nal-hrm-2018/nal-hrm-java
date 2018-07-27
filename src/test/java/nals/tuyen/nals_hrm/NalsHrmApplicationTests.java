package nals.tuyen.nals_hrm;

import nals.tuyen.nals_hrm.entities.Employees;
import nals.tuyen.nals_hrm.respository.EmployeesRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NalsHrmApplicationTests {

  Employees employees1;
  Employees employees2;
  EmployeesRepository employeesRepository;

  @Test
  public void contextLoads() {

  }

  @Test
  public void login(){


  }

}
