package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.AbsenceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceTypeRepository extends CrudRepository<AbsenceType, Integer> {

    List<AbsenceType> findTop6ByNameAbsenceTypeNotNull();

    AbsenceType findByIdAbsenceType(int absenceTypeId);

    AbsenceType findByNameAbsenceType(String annual_leave);
}
