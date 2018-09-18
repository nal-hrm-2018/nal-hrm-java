package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.OverTime;
import nals.hrm.api_nals_hrm.entities.OvertimeTypes;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OvertimeTypesRepository extends PagingAndSortingRepository<OvertimeTypes, Integer> {
    List<OvertimeTypes> findAll();
}
