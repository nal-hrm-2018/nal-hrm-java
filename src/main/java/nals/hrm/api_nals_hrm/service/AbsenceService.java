package nals.hrm.api_nals_hrm.service;

import nals.hrm.api_nals_hrm.define.CheckWeekend;
import nals.hrm.api_nals_hrm.define.DateDiff;
import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.AbsenceDTO;
import nals.hrm.api_nals_hrm.dto.ListAbsenceDTO;
import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.entities.*;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.repository.*;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProcessesRepository processesRepository;

    @Autowired
    private HolidayDefaultRepository holidayDefaultRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AbsenceTimeRepository absenceTimeRepository;

    @Autowired
    AbsenceTypeRepository absenceTypeRepository;


    public ListAbsenceDTO getListAbsenceEmployeeByToken(HttpServletRequest req, Optional<Integer> page, Optional<Integer> pageSize) {

        //find employee by token
        Employee employee = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)),0,0);

        return getListAbsenceEmployeeByIdEmployee(employee.getIdEmployee(), page, pageSize);
    }

    public String addAbsence(Absence absence, HttpServletRequest req) {
        Date fromDate;
        Date toDate;

//        Date fromDateAbsence;
//        Date toDateAbsence;

        Date now = new Date();
        //this email default of CEO
        String email = "nals_pmo@nal.com";
        String subject = "Đơn xin nghỉ phép";
        String content = "";


        try {
            //convert date string to date
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(absence.getFromDate());
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse(absence.getToDate());

            if (fromDate.equals(toDate) || fromDate.before(toDate)) {

                //find employee submit form absence
                //find by token login
                Employee employee = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)),0,0);

                //check form duplicate

                List<Absence> absenceDuplicate = absenceRepository.findAbsenceByDate(employee.getIdEmployee(),absence.getFromDate(),absence.getToDate());
                if(absenceDuplicate.size() > 0){
                    throw new CustomException("Duplicate form", 400);
                }
                AbsenceType absenceType = absenceTypeRepository.findByIdAbsenceType(absence.getAbsenceTypeId());
                String absenceNameType = "Nghỉ ốm";
                switch (absenceType.getNameAbsenceType()) {
                    case "annual_leave":
                        absenceNameType = "Nghỉ phép năm";
                        break;
                    case "unpaid_leave":
                        absenceNameType = "Nghỉ không lương";
                        break;
                    case "maternity_leave":
                        absenceNameType = "Nghỉ cưới hỏi";
                        break;
                    case "marriage_leave":
                        absenceNameType = "Nghỉ thai sản";
                        break;
                    case "bereavement_leave":
                        absenceNameType = "Nghỉ tang";
                        break;
                }

                AbsenceTime absenceTime = absenceTimeRepository.findByIdAbsenceTime(absence.getAbsenceTimeId());
                String absenceNameTime = "Cả ngày";
                switch (absenceTime.getNameAbsenceTime()) {
                    case "morning":
                        absenceNameTime = "Sáng";
                        break;
                    case "afternoon":
                        absenceNameTime = "Chiều";
                        break;
                }

                content = "<html><body>Kính gửi HR,<br><br>Nhân sự " + employee.getNameEmployee() + " xin nghỉ phép từ " + absence.getFromDate() + " đến " + absence.getToDate();
                content += "<br><br>Kiểu nghỉ: " + absenceNameType + ", nghỉ: " + absenceNameTime;
                content += "<br><br>Lý do: " + absence.getReason() + "<br><br>Ghi chú: " + absence.getDescription();
                content += "<br><br>Mong HR xem xét.<br><br>Cảm ơn.";
                content += "<br><br><br><strong style=\"color: red\">P/S: PO/PM dự án duyệt đơn, trường hợp nhân sự không làm dự án nào thì PO/Mentor của team sẽ duyệt.</strong> </body></html>";

                absence.setEmployeeId(employee.getIdEmployee());

                String strNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now);
                absence.setCreatedAt(strNow);
                absence.setUpdateAt(strNow);

                //xác định xác PO của dự án mà member này đang tham gia
                //nếu k thuộc 1 project nào email được default send to CEO

                //tim nhung project dang dien ra ma member do tham gia :o
                List<Processes> processesList = processesRepository.findByEmployeeIdAndCheckProjectExitAndDeleteFlag(employee.getIdEmployee(), 1, 0);
                if (processesList.size() > 0) {
                    List<Processes> processesByRole; //find list project processes by role, idProject
                    Processes processesPO;
                    Employee employeePO;
                    //xac dinh id cua role PO
                    Role role = roleRepository.findByNameRole("PO");
                    for (Processes obj : processesList) {
                        //xac dinh PO cua tung project va goi email cho PO do
                        processesByRole = processesRepository.findByProjectIdAndCheckProjectExitAndRoleIdAndDeleteFlag(obj.getProjectId(), 1, role.getIdRole(), 0);
                        //mỗi project chỉ có 1 PO
                        //phong tru truong hop lo nhu database bị sai ai do them vao ma co den 2 PO
                        //nen tam tra ket qua ve list roi lay PO dau tien thoi :o
                        //tim thong tin cua po
                        if (processesByRole.size() > 0) {
                            processesPO = processesByRole.get(0);
                            //xac dinh duoc PO cua du an
                            employeePO = employeeRepository.findByIdEmployeeAndIsEmployeeAndDeleteFlag(processesPO.getEmployeeId(), 1, 0);
                            //neu PO xin nghi thi goi don cho CEO
                            if (employeePO != null && !employeePO.getEmail().equals(employee.getEmail())) {
                                content = "<html><body>Kính gửi " + employeePO.getNameEmployee() + ",<br><br>Nhân sự " + employee.getNameEmployee() + " xin nghỉ phép từ " + absence.getFromDate() + " đến " + absence.getToDate();
                                content += "<br><br>Kiểu nghỉ: " + absenceNameType + ", nghỉ: " + absenceNameTime;
                                content += "<br><br>Lý do: " + absence.getReason() + "<br><br>Ghi chú: " + absence.getDescription();
                                content += "<br><br>Mong " + employeePO.getNameEmployee() + " xem xét.<br><br>Cảm ơn.";
                                content += "<br><br><br><strong style=\"color: red\">P/S: PO/PM dự án duyệt đơn, trường hợp nhân sự không làm dự án nào thì PO/Mentor của team sẽ duyệt.</strong> </body></html>";
                                sendEmail(employeePO.getEmail(), subject, content);
                            }
                            sendEmail(email, subject, content);
                        } else {
                            //send email to CEO
                            sendEmail(email, subject, content);
                        }
                    }
                } else {
                    //send email to CEO
                    sendEmail(email, subject, content);
                }

                //insert absence to database if send mail to PO success
                handleAbsentDays(absence, fromDate, toDate);
                return "Insert absence success!";
            } else {
                throw new CustomException("Can't submit form absence!", 400);
            }
        } catch (ParseException e) {
            throw new CustomException("Error server", 500);
        }

    }

    public String editAbsence(int id, Absence absenceEdit) {
        Date fromDate;
        Date toDate;
        try {

            //find absence old by id get client
            Absence absenceOld = absenceRepository.findByIdAbsencesAndDeleteFlag(id, 0);

            //validate fromDate, toDate edit
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(absenceEdit.getFromDate());
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse(absenceEdit.getToDate());

            List<Absence> absenceListDuplicate = absenceRepository.findAbsenceByDate(absenceOld.getEmployeeId(), absenceEdit.getFromDate(), absenceEdit.getFromDate());
            for (Absence objAbsence : absenceListDuplicate){
                if(objAbsence.getIdAbsences() != id ){
                    //ton tai 1 đơn khac don dang sua
                    //co ngay trung voi ngay chinh sua
                    throw new CustomException("Duplicate form", 400);
                }
            }

            Date now = new Date();
            String strNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now);

            //set employeeId of absence absenceEdit
            //can't change employeeId
            absenceEdit.setEmployeeId(absenceOld.getEmployeeId());

            //set idAbsenceEdit get from absence
            absenceEdit.setIdAbsences(absenceOld.getIdAbsences());

            absenceEdit.setUpdateAt(strNow);

            //if time edit < 30 day(equal fromDate or equal createAt) =>can edit absence
            if (DateDiff.dateDiff(new SimpleDateFormat("yyyy-MM-dd").parse(absenceOld.getFromDate()), now) <= 30 || DateDiff.dateDiff(new SimpleDateFormat("yyyy-MM-dd").parse(absenceOld.getCreatedAt()), now) <= 30) {

                //don khong duoc sua thanh 2 nam khac nhau
                //hoac 2 thang chuyen tiep 6 7
                if ((fromDate.getYear() != toDate.getYear())
                        || (Integer.parseInt(new SimpleDateFormat("MM").format(fromDate)) <= 6
                        && Integer.parseInt(new SimpleDateFormat("MM").format(toDate)) >= 7)) {
                    //can't edit
                    throw new CustomException("Can't edit!", 400);

                } else {
                    if (fromDate.equals(toDate) || fromDate.before(toDate)) {
                        absenceRepository.save(absenceEdit);
                    } else {
                        throw new CustomException("Error data", 400);
                    }
                }
                return "Update absence success!";
            } else {
                throw new CustomException("Can't edit!", 400);
            }

        } catch (ParseException e) {
            throw new CustomException("Error server", 500);
        }

    }

    public String deleteAbsence(int idAbsence) {
        //time can delete the same edit (30 day)
        Absence absence = absenceRepository.findByIdAbsencesAndDeleteFlag(idAbsence, 0);
        Date now = new Date();
        try {
            if (DateDiff.dateDiff(new SimpleDateFormat("yyyy-MM-dd").parse(absence.getFromDate()), now) <= 30 || DateDiff.dateDiff(new SimpleDateFormat("yyyy-MM-dd").parse(absence.getCreatedAt()), now) <= 30) {
                absence.setDeleteFlag(1);
                absenceRepository.save(absence);
                return "Delete absence success!";
            } else {
                throw new CustomException("Can't delete!", 400);
            }
        } catch (ParseException e) {
            throw new CustomException("Error server!", 500);
        }

    }

    public ListDTO getListAbsenceEmployeeHR(Optional<Integer> page, Optional<Integer> pageSize) {
        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;
        ArrayList<Absence> listAbsence = absenceRepository.findByDeleteFlagOrderByUpdateAtDesc(0, PageRequest.of(evalPage, evalPageSize));
        ArrayList<Object> listResult = new ArrayList<>();
        mapListAbsence(listAbsence, listResult);
        return new ListDTO(absenceRepository.findByDeleteFlag(0).size(), listResult);
    }

    public ListAbsenceDTO getListAbsenceEmployeeByIdEmployee(int idEmployee, Optional<Integer> page, Optional<Integer> pageSize) {

        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        Employee employee = employeeRepository.findByIdEmployeeAndIsEmployeeAndDeleteFlag(idEmployee, 1, 0);

        //find list absence employee not yet remove deleteFlag = 0
        //all absence of employee all year
        //paging result
        ArrayList<Absence> absenceList = absenceRepository.findByEmployeeIdAndDeleteFlagOrderByUpdateAtDesc(employee.getIdEmployee(), 0, PageRequest.of(evalPage, evalPageSize));

        //map Absence to Object
        ArrayList<Object> result = new ArrayList<>();

        result = modelMapper.map(absenceList, result.getClass());

        Date now = new Date();
        int yearNow = Integer.parseInt(new SimpleDateFormat("yyyy").format(now));

        ArrayList<Absence> listLeave; //list leave by type

        double allowAbsence = 0; //number absence allow

        double totalRemain = 0;

        //số ngày phép năm ngoái còn lại
        //được lấy dữ liệu từ database
        double remainingAbsenceDays = employee.getRemainingAbsenceDays();

        //có 2 đoạn thoi gian de tinh so ngay nghi con lai cua nam ngoai:
        //1/1-31/6
        //1/7-31/12 ->bo het so ngay nghi con lai

        //số ngày phép đã nghĩ (theo đăng ký)
        // số ngày này có thể vượt mức ngày phép cho phép hằng năm
        //tinh ở năm hiện tại

        //1.find list absence with type:  annual_leave in year now
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "annual_leave", yearNow);
        //so ngay nghi hop le(da tru thu 7, cn,le, bu le)
        double annualLeave = checkAbsenceDayInvalid(listLeave);

        //neu thoi gian hien tai sau  sau ngày 30/6
        String strDateChangeRemain = yearNow + "-06-30";
        Date dateChangeRemain;
        try {
            dateChangeRemain = new SimpleDateFormat("yyyy-MM-dd").parse(strDateChangeRemain);
        } catch (ParseException e) {
            throw new CustomException("Error server", 500);
        }


        //số ngày nghỉ không trả lương
        //2.find list absence with type:  unpaid_leave in year now
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "unpaid_leave", yearNow);
        double unpaidLeave = checkAbsenceDayInvalid(listLeave);

        //nghi thai san
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "maternity_leave", yearNow);
        double maternityLeave = 0;
        for (Absence obj : listLeave) {
            try {
                maternityLeave += DateDiff.dateDiff(new SimpleDateFormat("yyyy-MM-dd").parse(obj.getFromDate()), new SimpleDateFormat("yyyy-MM-dd").parse(obj.getToDate())) + 1;
            } catch (ParseException e) {
                throw new CustomException("Error server", 500);
            }
        }

        //nghỉ cưới
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "marriage_leave", yearNow);
        //so ngay nghi hop le(da tru thu 7, cn,le, bu le)
        double marriageLeave = checkAbsenceDayInvalid(listLeave);

        //nghỉ tang
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "bereavement_leave", yearNow);
        //so ngay nghi hop le(da tru thu 7, cn,le, bu le)
        double bereavementLeave = checkAbsenceDayInvalid(listLeave);

        //nghỉ dau om
        listLeave = absenceRepository.listLeave(employee.getIdEmployee(), "sick_leave", yearNow);
        //so ngay nghi hop le(da tru thu 7, cn,le, bu le)
        double sickLeave = checkAbsenceDayInvalid(listLeave);

        //tinh số ngày nghỉ duoc phep hang nam
        String strStartWorkDate = employee.getStartWorkDate();
        Date startWorkDate;
        try {
            startWorkDate = new SimpleDateFormat("yyyy-MM-dd").parse(strStartWorkDate);
        } catch (ParseException e) {
            throw new CustomException("Error server", 500);
        }


        //so nam da lam viec cua nhan vien
        int timeWork = Integer.parseInt(new SimpleDateFormat("yyyy").format(now)) - Integer.parseInt(new SimpleDateFormat("yyyy").format(startWorkDate));

        switch (timeWork) {
            case 0:
                allowAbsence = 12 - Integer.parseInt(new SimpleDateFormat("MM").format(startWorkDate));
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                allowAbsence = 12;
                break;
            case 6:
                allowAbsence = 13;
                break;
            case 7:
                allowAbsence = 14;
                break;
            case 8:
                allowAbsence = 15;
                break;
            default:
                allowAbsence = 16;
        }
//find id of type absence = "annual_leave"
        int idTypeAbsence = absenceTypeRepository.findByNameAbsenceType("annual_leave").getIdAbsenceType();

        //tinh so ngay nghi phep hang nam tu 1/1 - >30/6
        ArrayList<Absence> listAbsenceFromToTypeAnnualLeave = absenceRepository.findByEmployeeIdAndDeleteFlagAndAbsenceTypeIdAndFromDateGreaterThanEqualAndToDateLessThanEqualOrderByFromDateDesc(employee.getIdEmployee(), 0, idTypeAbsence, yearNow + "-01-01", yearNow + "-06-30");

        //so ngay nghi phep hang nam truoc 1/7
        double before7 = checkAbsenceDayInvalid(listAbsenceFromToTypeAnnualLeave);

        //so ngay nghi hang nam sau thang 7
        double after7 = annualLeave - before7;


        //neu time now > 30/6
        if (now.after(dateChangeRemain)) {


            //so ngay nghi con lai cua nam ngoai = 0
            remainingAbsenceDays = 0;

            //neu so ngay nghi nam ngoai con lai >= numberAbsenceAnnualLeave
            //nghĩa là số ngày đăng ký nghỉ phép hằng năm được trừ từ ngày nghỉ còn lại của năm ngoái
            if (employee.getRemainingAbsenceDays() > before7) {
                //neu so ngay nghi truoc 1/7 nho hon so ngay con lai cua nam ngoái
                //chua su dung het ngay remain
                if (after7 > allowAbsence) {
                    unpaidLeave = unpaidLeave + after7 - allowAbsence;
                    annualLeave = allowAbsence + before7;
                } else {
                    totalRemain = allowAbsence - after7;
                }

            } else {
                //neu so ngay nghi phép hang nam truoc thang 7 vượt qua so ngay remain
                // da su dụng het ngay remain
                //tinh so ngay vượt mức
                double annualTmp = before7 - employee.getRemainingAbsenceDays();
                if (after7 + annualTmp > allowAbsence) {
                    unpaidLeave = unpaidLeave + after7 + annualTmp - allowAbsence;
                    annualLeave = allowAbsence + employee.getRemainingAbsenceDays();
                } else {
                    totalRemain = allowAbsence - after7 - annualTmp;
                }
            }





        } else {
            if (remainingAbsenceDays >= before7) {
                //so ngay nghi truoc thang 7 it hon so ngay phep con lai
                //chua nghi het ~ chua su dung ngay allowAbsence
                remainingAbsenceDays = remainingAbsenceDays - before7;
                if(after7 > allowAbsence){
                    //so ngay dang ky nghi sau thang 7 vuot muc cho phep
                    //chuyen ngay vuot muc thanh khong luong
                    unpaidLeave = unpaidLeave + after7 - allowAbsence;
                    totalRemain = remainingAbsenceDays - before7;

                }else{
                    totalRemain = allowAbsence + remainingAbsenceDays - after7;
                }

            } else {
                if (before7 > allowAbsence + employee.getRemainingAbsenceDays()) {
                    //da su dung het ngay phep nam nay va nam ngoai
                    unpaidLeave = unpaidLeave + annualLeave - allowAbsence - employee.getRemainingAbsenceDays();
                    annualLeave = allowAbsence + remainingAbsenceDays;
                } else {
                    //
                    if(annualLeave > allowAbsence + employee.getRemainingAbsenceDays()){
                        //het ngay phep
                        unpaidLeave = unpaidLeave + annualLeave - allowAbsence - employee.getRemainingAbsenceDays();
                        annualLeave = allowAbsence + employee.getRemainingAbsenceDays();
                    }else {
                        totalRemain = allowAbsence + employee.getRemainingAbsenceDays() - annualLeave;
                    }
                }

                remainingAbsenceDays = 0;
            }
        }


        int total = absenceRepository.findByEmployeeIdAndDeleteFlag(employee.getIdEmployee(), 0).size();
        return new ListAbsenceDTO(allowAbsence, remainingAbsenceDays, totalRemain, annualLeave, unpaidLeave, marriageLeave, bereavementLeave, maternityLeave, sickLeave, new ListDTO(total, result));
    }

    public ListDTO findAbsenceProjectProcessesManageRolePO(String id) {
        try {
            //get project by id
            Project project = projectRepository.findByIdProjectAndDeleteFlag(id, 0);

            //get list member of project
            //any member not exit project
            List<Employee> listMember = employeeRepository.findAllNotExit(id);

            AbsenceDTO absenceDTO = new AbsenceDTO();
            ArrayList<Object> listResult = new ArrayList<>();
            List<Absence> absenceList;
            Date now = new Date();
            String strNow = new SimpleDateFormat("yyyy-MM-dd").format(now);
            String startDateProject;
            String endDateProject;
            startDateProject = project.getStartDate() != null ? project.getStartDate() : strNow;
            endDateProject = strNow;
            //get list absence of a member
            for (Employee objEmp : listMember) {
                //get list absence of objEmp
                //when project start to end
                absenceList = absenceRepository.findAbsenceByDate(objEmp.getIdEmployee(), startDateProject, endDateProject);
                for (Absence objAbs : absenceList) {
                    absenceDTO = modelMapper.map(objAbs, absenceDTO.getClass());
                    //find nameEmployee
                    //if name != null=> return name else return ""
                    absenceDTO.setNameEmployee(objEmp.getNameEmployee());
                    absenceDTO.setIdEmployee(objEmp.getIdEmployee());
                    absenceDTO.setNameProject(project.getNameProject());
                    absenceDTO.setIdProject(project.getIdProject());
                    listResult.add(absenceDTO);
                }
            }

            return new ListDTO(listResult.size(), listResult);

        } catch (Exception e) {
            throw new CustomException("Error server", 500);
        }
    }

    public ListDTO searchAbsenceEmployeeHR(Optional<Integer> month, Optional<Integer> year, Optional<Integer> page, Optional<Integer> pageSize) {
        try {
            int evalPageSize = pageSize.orElse(Define.initialPageSize);
            int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

            int evalMonth = month.orElse(0);
            int evalYear = year.orElse(0);
            ArrayList<Absence> listAbsence;
            int total = 0;
            if (evalMonth != 0 && evalYear != 0) {
                listAbsence = absenceRepository.findByMonthAndYear(evalMonth, evalYear, PageRequest.of(evalPage, evalPageSize));
                total = absenceRepository.findByMonthAndYear(evalMonth, evalYear);
            } else {
                total = absenceRepository.findByYear(evalYear);
                listAbsence = absenceRepository.findByYear(evalYear, PageRequest.of(evalPage, evalPageSize));
            }

            ArrayList<Object> listResult = new ArrayList<>();
            mapListAbsence(listAbsence, listResult);
            return new ListDTO(total, listResult);
        } catch (Exception e) {
            throw new CustomException("Error server", 500);
        }

    }

    public void mapListAbsence(ArrayList<Absence> listAbsence, ArrayList<Object> listResult) {
        String nameEmployee;
        Employee employee;
        for (Absence objAbsence : listAbsence) {
            AbsenceDTO absenceDTO = new AbsenceDTO();
            absenceDTO = modelMapper.map(objAbsence, absenceDTO.getClass());
            //find nameEmployee
            //if name != null=> return name else return ""
            employee = employeeRepository.findByIdEmployeeAndIsEmployeeAndDeleteFlag(objAbsence.getEmployeeId(), 1, 0);
            nameEmployee = employee != null ? employee.getNameEmployee() : "";
            absenceDTO.setIdEmployee(objAbsence.getEmployeeId());
            absenceDTO.setNameEmployee(nameEmployee);
            listResult.add(absenceDTO);

        }

    }

    public void handleAbsentDays(Absence absence, Date fromDate, Date toDate) {
        //kiem tra truong hop đơn xin nghĩ được submit trong khoảng thời gian giữa 2 năm
        //ex: from: 29/12/2017 - to: 3/1/2018
        //chia làm 2 đơn để lưu
        int year = 0;
        year = Integer.parseInt(new SimpleDateFormat("yyyy").format(fromDate));
        absence.setFromDate(absence.getFromDate());
        if (fromDate.getYear() != toDate.getYear() || (Integer.parseInt(new SimpleDateFormat("MM").format(fromDate)) == 6 && Integer.parseInt(new SimpleDateFormat("MM").format(toDate))  == 7)) {
            //chia làm 2 đơn để save vào database
            if (Integer.parseInt(new SimpleDateFormat("MM").format(fromDate)) <= 6 && Integer.parseInt(new SimpleDateFormat("MM").format(toDate))  >= 7) {
                //đơn từ from - year-06-30
                absence.setToDate(year + "-06-30");
            } else {
                //đơn thứ nhất từ fromDate - 31/12/fromYear
                absence.setToDate(year + "-12-31");
            }

            absenceRepository.save(absence);

            //creat absence for form 2
            Absence absenceForm2 = new Absence();
            absenceForm2.setUpdateAt(absence.getUpdateAt());
            absenceForm2.setCreatedAt(absence.getCreatedAt());
            absenceForm2.setEmployeeId(absence.getEmployeeId());
            absenceForm2.setReason(absence.getReason());
            absenceForm2.setAbsenceTimeId(absence.getAbsenceTimeId());
            absenceForm2.setDescription(absence.getDescription());
            absenceForm2.setAbsenceTypeId(absence.getAbsenceTypeId());


            year = Integer.parseInt(new SimpleDateFormat("yyyy").format(toDate));
            //đơn thứ 2 từ 1/1/toYear - toDate
            if (Integer.parseInt(new SimpleDateFormat("MM").format(fromDate)) == 6 && Integer.parseInt(new SimpleDateFormat("MM").format(toDate))  == 7) {
                //đơn từ from - year-06-31
                absenceForm2.setFromDate(year + "-07-01");
            } else {
                //đơn thứ nhất từ fromDate - 31/12/fromYear
                absenceForm2.setFromDate(year + "-01-01");
            }

            absenceForm2.setToDate(new SimpleDateFormat("yyyy-MM-dd").format(toDate));
            absenceRepository.save(absenceForm2);

        } else {
            if (fromDate.equals(toDate) || fromDate.before(toDate)) {
                absenceRepository.save(absence);
            } else {
                throw new CustomException("Error data", 400);
            }
        }

    }

    //kiem tra xem so ngay nghi hop le(tru di ngay le, ngay thu 7, cn, nghi bu)
    public double checkAbsenceDayInvalid(ArrayList<Absence> listLeave) {
        double leave = 0;
        double tmp = 0;
        int countWeekend = 0;
        int countHolidayDefault = 0;
        int countHoliday = 0;
        Date from;
        Date to;
        String strFrom;
        for (Absence objAbsence : listLeave) {
            try {

                from = new SimpleDateFormat("yyyy-MM-dd").parse(objAbsence.getFromDate());
                to = new SimpleDateFormat("yyyy-MM-dd").parse(objAbsence.getToDate());
                countHoliday = 0;
                countHolidayDefault = 0;

                //đếm số ngày đăng ký nghỉ của mỗi đơn.
                //chưa trừ thứ 7, cn và ngày lễ
                tmp = DateDiff.dateDiff(from, to) + 1;

                //đếm số thứ 7, chủ nhật trong khoảng thời gian nghỉ
                countWeekend = CheckWeekend.countWeekend(from, to);

                from = new SimpleDateFormat("yyyy-MM-dd").parse(objAbsence.getFromDate());
                to = new SimpleDateFormat("yyyy-MM-dd").parse(objAbsence.getToDate());

                //danh sách ngày lễ default
                while (from.compareTo(to) <= 0) {
                    strFrom = new SimpleDateFormat("yyyy-MM-dd").format(from);
                    //kiểm tra xem nếu không phải thứ 7 or chủ nhật thì mới kiểm tra ngày nghỉ đó có thuộc ngày lễ hay không
                    //nếu là ngày lễ thì countHolidayDefault += 1
                    if (CheckWeekend.checkDate(from) < 7 && holidayDefaultRepository.findByDateHolidayDefaultAndDeleteFlag(strFrom, 0) != null) {
                        countHolidayDefault += 1;
                    }
                    //kiem tra xe ngay nghi do co thuoc ngay nghi bu nao khong
                    //neu có thì countHoliday += 1
                    if (holidayRepository.findByDateHolidayAndDeleteFlag(strFrom, 0) != null) {
                        countHoliday += 1;
                    }
                    from.setDate(from.getDate() + 1);
                }

                tmp = tmp - countWeekend - countHolidayDefault - countHoliday;
                if (objAbsence.getAbsenceTime().getNameAbsenceTime().equals("all")) {
                    leave += tmp;
                } else {
                    leave += (tmp / 2);
                }

            } catch (ParseException e) {
                throw new CustomException("Error server", 500);
            }
        }
        return leave;
    }

    public void sendEmail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper sendEmail = null;
        try {
            sendEmail = new MimeMessageHelper(message, true);
            sendEmail.setTo(to);
            sendEmail.setSubject(subject);
            sendEmail.setText(content, true);
        } catch (MessagingException e) {
            throw new CustomException("Error server!", 500);
        }

        javaMailSender.send(message);
    }

}
