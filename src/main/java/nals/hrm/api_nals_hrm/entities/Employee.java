package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  int idEmployee;

  @Column(name = "email")
  String email;

  @JsonIgnore
  @Column(name = "password")
  String password;

  @JsonIgnore
  @Column(name = "role_id")
  int idRole;

  @Column(name = "delete_flag")
  int deleteFlag;

  Role role;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "permission_employee",
          joinColumns = @JoinColumn(name = "employee_id"),
          inverseJoinColumns = @JoinColumn(name = "permission_id"))
  private List<Permission> permissions;

  public Employee() {
    super();
  }


  public Employee(String email, int idRole, Role role, Permission permission) {
    this.email = email;
    this.idRole = idRole;
    this.role = role;
    this.permissions = permissions;
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
    this.password = password.replace("$2y$","$2a$");
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getIdRole() {
    return idRole;
  }

  public void setIdRole(int idRole) {
    this.idRole = idRole;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public List<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Permission> permissions) {
    this.permissions = permissions;
  }

  @Override
  public String toString() {
    return "Employee{" +
            "idEmployee=" + idEmployee +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", idRole=" + idRole +
            '}';
  }
}
