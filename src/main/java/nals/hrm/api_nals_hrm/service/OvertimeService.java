package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.define.CheckWeekend;
import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.dto.ListOvertimeDTO;
import nals.hrm.api_nals_hrm.dto.OvertimeDTO;
import nals.hrm.api_nals_hrm.entities.*;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.repository.*;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class OvertimeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    OvertimeRepository overtimeRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProcessesRepository processesRepository;

    @Autowired
    private OvertimeStatusesRepository overtimeStatusesRepository;

    @Autowired
    private HolidayDefaultRepository holidayDefaultRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private DayTypesRepository dayTypesRepository;


    public Object getListOvertimeByToken(HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {
        Employee employee = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)), 0, 0);
        return getListOvertimeByIdEmployee(employee.getIdEmployee(), page, pageSize, true);
    }

    public ListOvertimeDTO getListOvertimeByIdEmployee(int idEmployee, Optional<Integer> page, Optional<Integer> pageSize, boolean check) {

        float normal = 0;
        float dayOff = 0;
        float holiday = 0;

        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        ArrayList<Overtime> overtimeList;
        int total = 0;
        if (check) {
            overtimeList = overtimeRepository.findByEmployeeIdAndDeleteFlagOrderByUpdatedAtDesc(idEmployee, 0, PageRequest.of(evalPage, evalPageSize));
            total = overtimeRepository.findByEmployeeIdAndDeleteFlagOrderByUpdatedAtDesc(idEmployee, 0).size();
        } else {
            overtimeList = overtimeRepository.findByEmployeeIdHR(idEmployee, PageRequest.of(evalPage, evalPageSize));
            total = overtimeRepository.findByEmployeeIdHR(idEmployee).size();

        }
        ArrayList<Object> result = new ArrayList<>();

        mapOvertime(overtimeList, result);

        //list overtime of employee in month now
        ArrayList<Overtime> listOvertimeMonthNow = overtimeRepository.findMonthNow(idEmployee);

        for (Overtime obj : listOvertimeMonthNow) {
            if (obj.getOvertimeStatuses().getName().equals("Rejected") || obj.getOvertimeStatuses().getName().equals("Accepted")) {
                switch (obj.getDateTypes().getNameDayType()) {
                    case "normal":
                        if (obj.getCorrectTotalTime() != null) {
                            normal += obj.getCorrectTotalTime();
                        }
                        break;
                    case "weekend":
                        if (obj.getCorrectTotalTime() != null) {
                            dayOff += obj.getCorrectTotalTime();
                        }
                        break;
                    default:
                        if (obj.getCorrectTotalTime() != null) {
                            holiday += obj.getCorrectTotalTime();
                        }

                }
            }

        }
        return new ListOvertimeDTO(normal, dayOff, holiday, new ListDTO(total, result));
    }

    public ListDTO getListOvertimeEmployeeHR(HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {
        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        Employee employeeCEO = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)), 0, 0);
        ArrayList<Overtime> listOT = new ArrayList<>();
        int total;
        if (employeeCEO.getIsManager() == 1) {
            //this is CEO
            //view list OT of CEO and HR
            //can accept or reject form OT
            listOT = overtimeRepository.findOTOfPoOrHr(PageRequest.of(evalPage, evalPageSize));
            total = overtimeRepository.findOTOfPoOrHr().size();
        } else {
            listOT = overtimeRepository.findOTHR(PageRequest.of(evalPage, evalPageSize));
            total = overtimeRepository.findOTHR().size();
        }


        ArrayList<Object> result = new ArrayList<>();

        mapOvertime(listOT, result);

        return new ListDTO(total, result);
    }

    public ListDTO getListOvertimeProjectManageRolePO(String idProject, HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {

        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;


        //get project by id
        Project project = projectRepository.findByIdProjectAndEndDateAndDeleteFlag(idProject, null, 0);
        if (project == null) {
            throw new CustomException("project not found", 404);
        }

        //find po of project
        //xác định xem có đúng project mà employee đó làm PO hay k?

        Employee employeePO = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)), 0, 0);

        Processes obj = processesRepository.findByEmployeeIdAndProjectIdAndRoleIdAndCheckProjectExitAndDeleteFlag(
                employeePO.getIdEmployee(), idProject, roleRepository.findByNameRole("PO").getIdRole(), 0, 0);
        if (obj == null) {
            throw new CustomException("Access Denied Exception", 403);

        }

        ArrayList<Overtime> overtimeArrayList = overtimeRepository.findOTByIdProject(project.getIdProject(), employeePO.getIdEmployee(), PageRequest.of(evalPage, evalPageSize));

        ArrayList<Object> listResult = new ArrayList<>();

        mapOvertime(overtimeArrayList, listResult);

        return new ListDTO(overtimeRepository.findOTByIdProject(idProject, employeePO.getIdEmployee()).size(), listResult);

    }

    public void mapOvertime(ArrayList<Overtime> overtimeList, ArrayList<Object> result) {
        OvertimeDTO overTimeDTO = new OvertimeDTO();
        for (Overtime obj : overtimeList) {
            overTimeDTO = modelMapper.map(obj, overTimeDTO.getClass());
            overTimeDTO.setEmployeeId(obj.getEmployeeId());
            overTimeDTO.setNameEmployee(obj.getEmployee().getNameEmployee());
            if (obj.getProcesses() != null) {
                overTimeDTO.setIdProject(obj.getProcesses().getProjectId());
                overTimeDTO.setNameProject(obj.getProcesses().getProject().getNameProject());
            }

            result.add(overTimeDTO);
        }

    }

    public String addOvertime(OvertimeDTO overtimeDTO, HttpServletRequest req) {
        Employee employee = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)), 0, 0);

        //check duplicate

        if (overtimeRepository.findByEmployeeIdAndDateAndDeleteFlag(employee.getIdEmployee(), overtimeDTO.getDate(), 0).size() > 0) {
            throw new CustomException("duplicate form ot", 400);
        }

        Overtime overtimeAdd = new Overtime();

        Date startTime;
        Date endTime;

        try {
            startTime = new SimpleDateFormat("hh:mm").parse(overtimeDTO.getStartTime());
            endTime = new SimpleDateFormat("hh:mm").parse(overtimeDTO.getEndTime());

        } catch (ParseException e) {
            throw new CustomException("data error", 400);
        }

        float numberEnd = endTime.getHours() + (float) endTime.getMinutes() / 60;
        float numberStart = startTime.getHours() + (float) startTime.getMinutes() / 60;

        if (endTime.before(startTime) || ((numberEnd - numberStart) < overtimeDTO.getTotalTime())) {
            throw new CustomException("data error", 400);
        }

        Date now = new Date();

        String strNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now);
        Processes processes;
        if (overtimeDTO.getIdProject() != null) {
            processes = processesRepository.findByProjectIdAndEmployeeIdAndCheckProjectExitAndDeleteFlag(overtimeDTO.getIdProject(), employee.getIdEmployee(), 0, 0);
            if (processes == null) {
                throw new CustomException("not joining project", 400);
            } else {
                overtimeAdd.setProcessId(processes.getIdProcesses());
            }

        }

        overtimeAdd.setEmployeeId(employee.getIdEmployee());
        overtimeAdd.setReason(overtimeDTO.getReason());
        overtimeAdd.setDate(overtimeDTO.getDate());
        overtimeAdd.setStartTime(overtimeDTO.getStartTime());
        overtimeAdd.setEndTime(overtimeDTO.getEndTime());
        overtimeAdd.setTotalTime(overtimeDTO.getTotalTime());
        overtimeAdd.setOvertimeStatusId(overtimeStatusesRepository.findByName("Not yet").getId());
        overtimeAdd.setCorrectTotalTime(null);
        overtimeAdd.setReasonReject(null);
        overtimeAdd.setCreatedAt(strNow);
        overtimeAdd.setUpdatedAt(strNow);

        checkDaysType(overtimeDTO, overtimeAdd);

        overtimeRepository.save(overtimeAdd);

        return "Submit overtime success!";
    }

    public String editOvertime(int id, OvertimeDTO overtimeDTO, HttpServletRequest req) {

        Employee employee = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)), 0, 0);
        Overtime overtimeOld = overtimeRepository.findByIdAndEmployeeIdAndDeleteFlag(id, employee.getIdEmployee(), 0);

        if (overtimeOld == null || !overtimeOld.getOvertimeStatuses().getName().equals("Not yet")) {
            throw new CustomException("Access Denied Exception!", 403);
        }

        //check duplicate
        if (!overtimeOld.getDate().equals(overtimeDTO.getDate()) && overtimeRepository.findByEmployeeIdAndDateAndDeleteFlag(employee.getIdEmployee(), overtimeDTO.getDate(), 0).size() > 0) {
            throw new CustomException("duplicate form ot", 400);
        }

        Date startTime;
        Date endTime;
        try {
            startTime = new SimpleDateFormat("hh:mm").parse(overtimeDTO.getStartTime());
            endTime = new SimpleDateFormat("hh:mm").parse(overtimeDTO.getEndTime());

        } catch (ParseException e) {
            throw new CustomException("data error", 400);
        }

        float numberEnd = endTime.getHours() + (float) endTime.getMinutes() / 60;
        float numberStart = startTime.getHours() + (float) startTime.getMinutes() / 60;

        if (endTime.before(startTime) || ((numberEnd - numberStart) < overtimeDTO.getTotalTime())) {
            throw new CustomException("data error", 400);
        }

        Date now = new Date();

        String strNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now);
        Processes processes;
        if (overtimeDTO.getIdProject() != null) {
            processes = processesRepository.findByProjectIdAndEmployeeIdAndCheckProjectExitAndDeleteFlag(overtimeDTO.getIdProject(), employee.getIdEmployee(), 0, 0);
            if (processes != null) {
                overtimeOld.setProcessId(processes.getIdProcesses());
            } else {
                throw new CustomException("not joining project", 400);
            }

        }

        overtimeOld.setReason(overtimeDTO.getReason());
        overtimeOld.setDate(overtimeDTO.getDate());
        overtimeOld.setStartTime(overtimeDTO.getStartTime());
        overtimeOld.setEndTime(overtimeDTO.getEndTime());
        overtimeOld.setTotalTime(overtimeDTO.getTotalTime());
        overtimeOld.setUpdatedAt(strNow);

        checkDaysType(overtimeDTO, overtimeOld);

        overtimeRepository.save(overtimeOld);

        return "Updated overtime success!";
    }

    public String confirmOvertime(int id, Overtime overtime, HttpServletRequest req) {

        Employee employeeConfirm = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)), 0, 0);
        Overtime overtimeOld = overtimeRepository.findByIdAndDeleteFlag(id, 0);
        if (!overtimeOld.getOvertimeStatuses().getName().equals("Not yet") && !overtimeOld.getOvertimeStatuses().getName().equals("Reviewing")) {
            throw new CustomException("Access Denied Exception!", 403);
        }
        if (employeeConfirm.getIsManager() != 1) {
            //PO only confirm to projects managed by PO
            //xac dinh employeeConfirm có đang duyet dung form OT
            Processes obj = processesRepository.findByEmployeeIdAndProjectIdAndRoleIdAndCheckProjectExitAndDeleteFlag(
                    employeeConfirm.getIdEmployee(), overtimeOld.getProcesses().getProjectId(), roleRepository.findByNameRole("PO").getIdRole(), 0, 0);
            if (obj == null) {
                throw new CustomException("Access Denied Exception", 403);
            }

        }
        //if CEO can confirm all form
        //can use url for confirm if have id
        if (overtime.getOvertimeStatusId() < 3) {
            throw new CustomException("Can't confirm", 400);
        }
        overtimeOld.setOvertimeStatusId(overtime.getOvertimeStatusId());
        if (overtime.getOvertimeStatusId() == 4) {
            if (overtime.getReasonReject().length() < 1) {
                throw new CustomException("Can't confirm", 400);
            }
            overtimeOld.setReasonReject(overtime.getReasonReject());
            if (overtime.getCorrectTotalTime() != null) {
                Date startTime;
                Date endTime;
                try {
                    startTime = new SimpleDateFormat("hh:mm").parse(overtimeOld.getStartTime());
                    endTime = new SimpleDateFormat("hh:mm").parse(overtimeOld.getEndTime());
                    float numberEnd = endTime.getHours() + (float) endTime.getMinutes() / 60;
                    float numberStart = startTime.getHours() + (float) startTime.getMinutes() / 60;
                    if ((numberEnd - numberStart) < overtime.getCorrectTotalTime()) {
                        throw new CustomException("data error", 400);
                    }
                } catch (ParseException e) {
                    throw new CustomException("data error", 400);
                }

                overtimeOld.setCorrectTotalTime(overtime.getCorrectTotalTime());
            } else {
                overtimeOld.setCorrectTotalTime(0.0f);

            }
        } else {
            overtimeOld.setCorrectTotalTime(overtimeOld.getTotalTime());
        }

        overtimeRepository.save(overtimeOld);

        return "Confirm OT success!";
    }

    public void checkDaysType(OvertimeDTO overtimeDTO, Overtime overtime) {
        Date dateOT;
        try {
            dateOT = new SimpleDateFormat("yyyy-MM-dd").parse(overtimeDTO.getDate());
        } catch (ParseException e) {
            throw new CustomException("data error", 400);
        }

        if (holidayDefaultRepository.findByDateHolidayDefaultAndDeleteFlag(overtimeDTO.getDate(), 0) != null) {
            overtime.setDayTypeId(dayTypesRepository.findByNameDayTypeAndDeleteFlag("holiday", 0).getIdDayType());
        } else {
            if (holidayRepository.findByDateHolidayAndDeleteFlag(overtimeDTO.getDate(), 0) != null) {
                Holiday holiday = holidayRepository.findByDateHolidayAndDeleteFlag(overtimeDTO.getDate(), 0);
                overtime.setDayTypeId(holiday.getDayTypeId());
            } else {
                if (CheckWeekend.checkDate(dateOT) > 6) {
                    //this weekend
                    overtime.setDayTypeId(dayTypesRepository.findByNameDayTypeAndDeleteFlag("weekend", 0).getIdDayType());

                } else {
                    overtime.setDayTypeId(dayTypesRepository.findByNameDayTypeAndDeleteFlag("normal", 0).getIdDayType());

                }
            }
        }
    }


}
