package nals.tuyen.nals_hrm.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employees")
public class Employees implements Serializable {
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  int idEmployee;

  @Column(name = "email")
  String email;

  @Column(name = "password")
  String password;

  @Column(name = "role_id")
  String idRole;


  public Employees() {
    super();
  }

  public Employees(int idEmployee, String email, String password) {
    this.idEmployee = idEmployee;
    this.email = email;
    this.password = password;
  }
}
