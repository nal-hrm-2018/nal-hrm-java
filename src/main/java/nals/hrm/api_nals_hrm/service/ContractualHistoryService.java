package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.dto.ExpiringContractsDTO;
import nals.hrm.api_nals_hrm.entities.ContractualHistory;
import nals.hrm.api_nals_hrm.repository.ContractualHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContractualHistoryService {

    @Autowired
    ContractualHistoryRepository contractualHistoryRepository;

    public ExpiringContractsDTO expiringContracts() {
        int internship = 0;
        int probation = 0;
        int oneYear = 0;
        int threeYear = 0;
        List<ContractualHistory> contractualHistoryList = contractualHistoryRepository.findExpiringContracts();
        for(ContractualHistory contractualHistory : contractualHistoryList){
            switch (contractualHistory.getContractualTypes().getName()){
                case "Internship":
                    internship += 1;
                    break;
                case "Probationary":
                    probation += 1;
                    break;
                case "One-year":
                    oneYear += 1;
                    break;
                case "Three-year":
                    threeYear += 1;
                    break;

            }
        }
        return new ExpiringContractsDTO(internship, probation, oneYear, threeYear);
    }
}
