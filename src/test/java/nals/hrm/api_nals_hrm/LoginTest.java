package nals.hrm.api_nals_hrm;

import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.respository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoginTest{
    Employee employee;
    String email = "hr1@nal.com";
    String password = "123456";

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    EmployeeRepository employeeRepository;


    @Test
    public void checkUsernameFail() {
        employee = new Employee(email,password);
        Assert.assertEquals("a",email);
    }

    @Test
    public void checkNullEmail() throws Exception{
        employee = new Employee("",password);
        Assert.assertEquals(false, employee.checkNullEmail());
    }

    @Test
    public void checkNullPassword() throws Exception{
        employee = new Employee(email,"");
        Assert.assertEquals(false, employee.checkNullPassword());
    }

    @Test
    public void checkEmployeeNull() throws NullPointerException {
        employee=new Employee(email,"");
//        entityManager.persist(employee);
//        entityManager.flush();

//       Employee employeeFound= employeeRepository.findByEmail("hr1@nals.com");
//        as
        Assert.assertNull(employeeRepository.findByEmail("hr1@nald.com"));

    }

    @Test
    public  void checkEmployeeNotNull(){
        Assert.assertNotNull(employeeRepository.findByEmail("hr1@nal.com"));
    }

}
