package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.exception.CustomException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDiff {
    public static long dateDiff(Date startDate, Date endDate){

//        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//
//        Date currentDate = new Date();
//        Date date1 = null;
//        Date date2 = null;

        try {


//            date1 = simpleDateFormat.parse(startDate);
//            date2 = simpleDateFormat.parse(endDate);

            long getDiff = endDate.getTime() - startDate.getTime();

            long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);

            return getDaysDiff;
        } catch (Exception e) {
            throw new CustomException("Error server!",500);
        }
    }
    public static void main(String[] args) {
//        String startDate = "20-08-2018";
//        String endDate = "10-08-2018";
//        System.out.println(dateDiff(startDate,endDate));
    }
}
