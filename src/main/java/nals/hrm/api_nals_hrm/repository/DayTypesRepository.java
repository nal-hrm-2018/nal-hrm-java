package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.DayTypes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DayTypesRepository extends CrudRepository<DayTypes, Integer> {

    List<DayTypes> findAll();
}
