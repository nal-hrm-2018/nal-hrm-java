package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.OverTime;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface OvertimeRepository extends PagingAndSortingRepository<OverTime, Integer> {
//    ArrayList<OverTime> findByIdEmployeeAndDeleteFlag(int idEmployee, int i, PageRequest of);

    ArrayList<OverTime> findByEmployeeIdAndDeleteFlagOrderByUpdatedAtDesc(int idEmployee, int deleteFlag, Pageable pageable);

    ArrayList<OverTime> findByEmployeeIdAndDeleteFlag(int idEmployee, int i);
}
