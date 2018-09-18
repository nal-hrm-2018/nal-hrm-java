package nals.hrm.api_nals_hrm.entities;

import javax.persistence.*;

@Entity
@Table(name="overtime_statuses")
public class OvertimeStatuses {
    @Id
    @SequenceGenerator(name="seq",sequenceName="oracle_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name="name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
