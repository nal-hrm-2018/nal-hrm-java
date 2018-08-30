/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homnaylathumay;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * - Mục tiêu cao nhất là xét trong khoảng thời gian từ a đến b có bao nhiêu 
 * ngày thứ 7 và chủ nhật
 * - Mục tiêu 1: Xét 1 ngày kiểm tra đó là thứ mấy
 * - Mục tiêu 2: Chạy vòng lặp và đếm
 * - Bonus: Tính thêm ngày lễ nếu có
 * @author Tu.PhanThanh
 */
public class HomNayLaThuMay {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate =null,endDate = null;
        try {
            startDate = df.parse("10/25/1997");   
            endDate = df.parse("10/30/1998");
            System.out.println(chayNgay(startDate, endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }
    /**
     * Thuật toán áp dụng tại : https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week
     * @param sday
     * @return đại diện của tuần 0 -> t7, 1 = cn
     * @throws ParseException 
     */
    public static int checkDayInWeek(String sday) throws ParseException{
        //Chuyển string sang Date
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate =null;
        try {
            startDate = df.parse(sday);      
            System.out.println(startDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String fullDate = startDate.toString().split(" ")[0];
        switch (fullDate){
            case "Mon": return 2;
            case "Tue": return 3;
            case "Wed": return 4;
            case "Thu": return 5;
            case "Fri": return 6;
            case "Sat": return 7;
            case "Sun": return 8;
        }
        return 1;
    }
    public static int checkDate(Date date){
        String fullDate = date.toString().split(" ")[0];
        switch (fullDate){
            case "Mon": return 2;
            case "Tue": return 3;
            case "Wed": return 4;
            case "Thu": return 5;
            case "Fri": return 6;
            case "Sat": return 7;
            default: return 8;
        }
    }
    public static int chayNgay(Date date1,Date date2){
        int dem = 0;
        Date date = date1;
        while (date.compareTo(date2) <= 0){
//            System.out.println(date.toString());
            int check = checkDate(date);
            if (check >=7) dem++;
            date.setDate(date.getDate() +1);
        }
        return dem;
    }
}
