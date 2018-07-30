package nals.tuyen.nals_hrm.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

  @ManyToMany
  @JoinTable(
          name = "employee_role",
          joinColumns = @JoinColumn(name = "employee_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Roles> roles;

  public Employees() {
    super();
  }

  public Employees(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public Employees(int idEmployee, String email, String password) {
    this.idEmployee = idEmployee;
    this.email = email;
    this.password = password;
  }

  public int getIdEmployee() {
    return idEmployee;
  }

  public void setIdEmployee(int idEmployee) {
    this.idEmployee = idEmployee;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Roles> getRoles() {
    return roles;
  }

  public void setRoles(Set<Roles> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "Employees{" +
            "idEmployee=" + idEmployee +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", roles=" + roles +
            '}';
  }



}
