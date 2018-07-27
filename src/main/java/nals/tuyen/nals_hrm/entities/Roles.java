package nals.tuyen.nals_hrm.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Roles implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  int idRole;

  @Column(name = "name", nullable = false)
  String nameRole;

  @Column(name = "description")
  String descriptionRole;

  @Column(name = "updated_at")
  String updatedAtRole;


  @Column(name = "created_at")
  String createdAtRole;


  @Column(name = "delete_flag")
  int deleteFlagRole;

  @ManyToMany(mappedBy = "roles")
  private Set<Employees> employees;

  public Roles() {
  }

  public Roles(int idRole, String nameRole, String descriptionRole, String updatedAtRole, String createdAtRole, int deleteFlagRole) {
    this.idRole = idRole;
    this.nameRole = nameRole;
    this.descriptionRole = descriptionRole;
    this.updatedAtRole = updatedAtRole;
    this.createdAtRole = createdAtRole;
    this.deleteFlagRole = deleteFlagRole;
  }

  public int getIdRole() {
    return idRole;
  }

  public void setIdRole(int idRole) {
    this.idRole = idRole;
  }

  public String getNameRole() {
    return nameRole;
  }

  public void setNameRole(String nameRole) {
    this.nameRole = nameRole;
  }

  public String getDescriptionRole() {
    return descriptionRole;
  }

  public void setDescriptionRole(String descriptionRole) {
    this.descriptionRole = descriptionRole;
  }

  public String getUpdatedAtRole() {
    return updatedAtRole;
  }

  public void setUpdatedAtRole(String updatedAtRole) {
    this.updatedAtRole = updatedAtRole;
  }



  public String getCreatedAtRole() {
    return createdAtRole;
  }

  public void setCreatedAtRole(String createdAtRole) {
    this.createdAtRole = createdAtRole;
  }



  public int getDeleteFlagRole() {
    return deleteFlagRole;
  }

  public void setDeleteFlagRole(int deleteFlagRole) {
    this.deleteFlagRole = deleteFlagRole;
  }

  public Set<Employees> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employees> employees) {
    this.employees = employees;
  }
}
