package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Absence;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer> {

    List<Absence> findByEmployeeIdAndDeleteFlag(int idEmployee, int deleteFlag);

    ArrayList<Absence> findByEmployeeIdAndDeleteFlagOrderByUpdateAtDesc(int idEmployee,int deleteFlag, Pageable pageable);

    Absence findByIdAbsencesAndDeleteFlag(int idAbsences,int deleteFlag);

    Absence save(Absence absence);

    ArrayList<Absence> findByDeleteFlagOrderByUpdateAtDesc(int deleteFlag,Pageable pageable);

    ArrayList<Absence> findByDeleteFlag(int deleteFlag);

//    List<Absence> findByEmployeeIdAndDeleteFlagAndFromDateGreaterThanEqualAndToDateLessThanEqualOrderByFromDateDesc(int idEmployee, int i, String startDate, String endDate);

    @Query(value = "SELECT * FROM absences " +
            " WHERE YEAR(from_date) = ?1 AND delete_flag = 0" +
            " ORDER BY updated_at DESC ", nativeQuery = true)
    ArrayList<Absence> findByYear(int fromYear, PageRequest of);

    @Query(value = "SELECT COUNT(*) FROM absences " +
            " WHERE YEAR(from_date) = ?1 AND delete_flag = 0" +
            " ORDER BY updated_at DESC ", nativeQuery = true)
    int findByYear(int fromYear);

    @Query(value = "SELECT * FROM absences " +
            " WHERE MONTH(from_date) = ?1" +
            " AND YEAR(from_date) = ?2 AND delete_flag = 0" +
            " ORDER BY updated_at DESC ", nativeQuery = true)
    ArrayList<Absence> findByMonthAndYear(int fromMonth, int fromYear,PageRequest of);

    @Query(value = "SELECT COUNT(*) FROM absences " +
            " WHERE MONTH(from_date) = ?1" +
            " AND YEAR(from_date) = ?2 AND delete_flag = 0" +
            " ORDER BY updated_at DESC ", nativeQuery = true)
    int findByMonthAndYear(int fromMonth, int fromYear);


    @Query(value = "SELECT * FROM absences\n" +
            "WHERE employee_id = ?1\n" +
            "AND delete_flag = 0\n" +
            "AND ((to_date >= ?2 AND to_date <= ?3 ) OR (from_date >= ?2 AND from_date <= ?3))\n" +
            "ORDER BY updated_at DESC", nativeQuery = true)
    List<Absence> findAbsenceInProject(int idEmployee, String startDateProject, String endDateProject);

    @Query(value = "SELECT * FROM absences INNER JOIN absence_types ON absences.absence_type_id = absence_types.id\n" +
            "WHERE absences.employee_id = ?1 AND absence_types.name = ?2 AND YEAR(absences.from_date) = ?3 AND absences.delete_flag = 0", nativeQuery = true)
    ArrayList<Absence> listLeave(int idEmployee, String typeLeave, int year);
}
