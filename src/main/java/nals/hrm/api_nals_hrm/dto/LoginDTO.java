package nals.hrm.api_nals_hrm.dto;

import io.swagger.annotations.ApiModelProperty;
import nals.hrm.api_nals_hrm.entities.Role;

public class LoginDTO {
  @ApiModelProperty(position = 0)
  int idEmployee;
  @ApiModelProperty(position = 1)
  String email;
  @ApiModelProperty(position = 3)
  Role role;

  public LoginDTO() {
  }

  public LoginDTO(int id, String email, Role role) {
    this.idEmployee = id;
    this.email = email;
    this.role = role;
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


  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
