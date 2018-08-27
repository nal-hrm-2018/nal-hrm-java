package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Absence;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AbsenceRepository extends CrudRepository<Absence, Integer> {
    List<Absence> findByEmployeeId(int idEmployee);

    ArrayList<Object> findByEmployeeId(int idEmployee, Pageable pageable);

//    @Query(value = "select a.name_role from role a, users b where b.email=?1 and a.id_role = b.id_role", nativeQuery = true)
    @Query(value = "select count(*) from absences inner join absence_types on absences.absence_type_id = absence_types.id\n" +
            "where absences.employee_id = ?1 and absence_types.name = ?2", nativeQuery = true)
    int countLeave(int idEmployee, String unpaid_leave);
}
