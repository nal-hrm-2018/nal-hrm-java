package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Absence;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface AbsenceRepository extends PagingAndSortingRepository<Absence, Integer> {
    List<Absence> findByEmployeeIdAndDeleteFlag(int idEmployee, int deleteFlag);

    ArrayList<Object> findByEmployeeIdAndDeleteFlag(int idEmployee,int deleteFlag, Pageable pageable);

    @Query(value = "select count(*) from absences inner join absence_types on absences.absence_type_id = absence_types.id\n" +
            "where absences.employee_id = ?1 and absence_types.name = ?2 and absences.delete_flag = 0", nativeQuery = true)
    int countLeave(int idEmployee, String unpaid_leave);

    Absence findByIdAbsencesAndDeleteFlag(int idAbsences,int deleteFlag);

    Absence save(Absence absence);

    ArrayList<Absence> findByDeleteFlag(int deleteFlag,Pageable pageable);

    ArrayList<Absence> findByDeleteFlag(int deleteFlag);

//    List<Absence> findByEmployeeIdAndDeleteFlagAndFromDateLessThanEqualAndToDateGreaterThanEqual(int idEmployee, int deleteFlag, String startDateProject, String endDateProject);

    List<Absence> findByEmployeeIdAndDeleteFlagAndFromDateGreaterThanEqualAndToDateLessThanEqual(int idEmployee, int i, String startDate, String endDate);
}
