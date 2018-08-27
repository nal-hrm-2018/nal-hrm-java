package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.AbsenceDTO;
import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.entities.Absence;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.repository.AbsenceRepository;
import nals.hrm.api_nals_hrm.repository.EmployeeRepository;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class AbsenceService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AbsenceRepository absenceRepository;

    public AbsenceDTO findByIdEmployee(HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {

        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        //find employee by token
        Employee employee = employeeRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));

        //find list absence employee
        //paging result
        ArrayList<Object> absenceList = absenceRepository.findByEmployeeId(employee.getIdEmployee(), PageRequest.of(evalPage, evalPageSize));

        int allowAbsence = 0; //number absence allow
        //số ngày phép năm ngoái còn lại
        int remainingAbsenceDays = employee.getRemainingAbsenceDays();

        //số ngày phép đã nghĩ (theo đăng ký)
        // số ngày này có thể vượt mức ngày phép cho phép hằng năm
        int annualLeave = absenceRepository.countLeave(employee.getIdEmployee(),"annual_leave");

        //số ngày nghỉ không trả lương
        int unpaidLeave = absenceRepository.countLeave(employee.getIdEmployee(),"unpaid_leave");

        //nghỉ thai sản
        int maternityLeave = absenceRepository.countLeave(employee.getIdEmployee(),"maternity_leave");

        //nghỉ cưới
        int marriageLeave = absenceRepository.countLeave(employee.getIdEmployee(),"marriage_leave");

        //nghỉ tang
        int bereavementLeave = absenceRepository.countLeave(employee.getIdEmployee(),"bereavement_leave");

        //tinh số ngày nghỉ duoc phep hang nam
        String strStartWorkDate = employee.getStartWorkDate();
        System.out.println(strStartWorkDate);
        Date startWorkDate;
        try {
            startWorkDate = new SimpleDateFormat("yyyy-MM-dd").parse(strStartWorkDate);
        } catch (ParseException e) {
            throw new CustomException("Error server",500);
        }
        Date dateNow = new Date();
        Calendar calendarNow = Calendar.getInstance();
        Calendar calendarWork = Calendar.getInstance();
        calendarNow.setTime(dateNow);
        calendarWork.setTime(startWorkDate);

        int timeWork = calendarNow.get(Calendar.YEAR) - calendarWork.get(Calendar.YEAR);
        switch (timeWork){
            case 0:
                allowAbsence = 12 - calendarWork.get(Calendar.MONTH);
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                allowAbsence = 12 + remainingAbsenceDays;
                break;
            case 6:
                allowAbsence = 13 + remainingAbsenceDays;
                break;
            case 7:
                allowAbsence = 14 + remainingAbsenceDays;
                break;
            case 8:
                allowAbsence = 15 + remainingAbsenceDays;
                break;
            default:
                allowAbsence = 16 + remainingAbsenceDays;

        }
        if(annualLeave > allowAbsence){
            unpaidLeave = unpaidLeave + annualLeave - allowAbsence;
        }


        int total = absenceRepository.findByEmployeeId(employee.getIdEmployee()).size();
        ListDTO result = new ListDTO(total, absenceList);
        return new AbsenceDTO(allowAbsence,remainingAbsenceDays, annualLeave, unpaidLeave,maternityLeave, marriageLeave,bereavementLeave,result);
    }


    public String save(Absence absence, HttpServletRequest req) {
        Date fromDate;
        Date toDate;
        try {
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(absence.getFromDate());
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse(absence.getToDate());
            //check if fromdDate before toDate => true
            if (fromDate.equals(toDate) || fromDate.before(toDate)){
                Employee employee = employeeRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
                absence.setEmployeeId(employee.getIdEmployee());
                absenceRepository.save(absence);
                return "Insert absence success!";
            }else{
                throw new CustomException("Error data",400);
            }
        } catch (ParseException e) {
            throw new CustomException("Error server",500);
        }

    }

}
