package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.OvertimeStatuses;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OvertimeStatusesRepository extends PagingAndSortingRepository<OvertimeStatuses, Integer> {
    List<OvertimeStatuses> findAll();
}
