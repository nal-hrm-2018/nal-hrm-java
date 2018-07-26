package nals.tuyen.nals_hrm.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_types")
public class EmployeeTypes implements Serializable{
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  int idEmployeeType;

  @Column(name = "name", length = 45, nullable = false)
  String nameEmployeeType;

  @Column(name = "description")
  String descriptionEmployeeType;

  @Column(name = "updated_at")
  String updatedAtEmployeeType;

  @Column(name = "updated_by_employee")
  int updatedByEmployee;

  @Column(name = "created_at")
  String createdAtEmployeeType;

  @Column(name = "created_by_employee")
  int createdByEmployee;

  @Column(name = "delete_flag")
  int deleteFlagEmployeeType;
}
