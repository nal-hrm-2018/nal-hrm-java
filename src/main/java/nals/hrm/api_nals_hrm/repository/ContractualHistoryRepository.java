package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.ContractualHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractualHistoryRepository extends JpaRepository<ContractualHistory, Integer> {
  @Query(value = "SELECT * FROM contractual_history\n" +
          "INNER JOIN employees ON contractual_history.employee_id = employees.id\n" +
          "INNER JOIN contractual_type \n" +
          "ON contractual_type.id = contractual_history.contractual_type_id\n" +
          "WHERE employees.delete_flag = 0\n" +
          "AND contractual_history.delete_flag = 0\n" +
          "AND employees.is_employee = 1\n" +
          "AND contractual_type.delete_flag = 0\n" +
          "AND YEAR(NOW()) = YEAR(contractual_history.end_date)\n" +
          "AND MONTH(NOW()) = MONTH(contractual_history.end_date)\n" +
          "AND (contractual_type.name != \"Indefinite\" AND contractual_type.name != \"Part-time\")", nativeQuery = true)
  List<ContractualHistory> findExpiringContracts();
}
