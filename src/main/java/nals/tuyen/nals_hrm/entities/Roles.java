package nals.tuyen.nals_hrm.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  int idRole;

  @Column(name = "name", length = 45, nullable = false)
  String nameRole;

  @Column(name = "description")
  String descriptionRole;

  @Column(name = "updated_at")
  String updatedAtRole;

  @Column(name = "updated_by_employee")
  int updatedByEmployeeRole;

  @Column(name = "created_at")
  String createdAtRole;

  @Column(name = "created_by_employee")
  int createdByEmployeeRole;

  @Column(name = "delete_flag")
  int deleteFlagRole;

  public Roles() {
  }

  public Roles(int idRole, String nameRole, String descriptionRole, String updatedAtRole, int updatedByEmployeeRole, String createdAtRole, int createdByEmployeeRole, int deleteFlagRole) {
    this.idRole = idRole;
    this.nameRole = nameRole;
    this.descriptionRole = descriptionRole;
    this.updatedAtRole = updatedAtRole;
    this.updatedByEmployeeRole = updatedByEmployeeRole;
    this.createdAtRole = createdAtRole;
    this.createdByEmployeeRole = createdByEmployeeRole;
    this.deleteFlagRole = deleteFlagRole;
  }
}
