package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

  List<Team> findAll();
}
