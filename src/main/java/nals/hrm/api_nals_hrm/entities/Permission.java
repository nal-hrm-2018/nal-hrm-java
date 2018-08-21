package nals.hrm.api_nals_hrm.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permissions")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    int idPermission;

    @Column(name = "name", nullable = false)
    String namePermission;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
//    private List<Employee> employee;

    public Permission() {
    }

    public Permission(int idPermission,String namePermission) {
        this.idPermission = idPermission;
        this.namePermission = namePermission;

    }


    public int getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(int idPermission) {
        this.idPermission = idPermission;
    }

    public String getNamePermission() {
        return namePermission;
    }

    public void setNamePermission(String namePermission) {
        this.namePermission = namePermission;
    }



//    public List<Employee> getEmployees() {
//        return employee;
//    }
//
//    public void setEmployees(List<Employee> employee) {
//        this.employee = employee;
//    }

    @Override
    public String toString() {
        return "Permission{" +
                "idPermission=" + idPermission +
                ", namePermission='" + namePermission + '\'' +
                '}';
    }
}
