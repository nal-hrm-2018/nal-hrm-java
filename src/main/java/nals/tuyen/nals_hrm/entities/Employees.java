package nals.tuyen.nals_hrm.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employees")
public class Users implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  int idUser;


}
