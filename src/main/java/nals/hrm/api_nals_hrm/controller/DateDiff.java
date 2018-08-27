package nals.hrm.api_nals_hrm.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDiff {
    public static void main(String[] args) {

        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date currentDate = new Date();
        Date date1 = null;
        Date date2 = null;

        try {
            String startDate = "20-08-2018";
            String endDate = "10-08-2018";

            date1 = simpleDateFormat.parse(startDate);
            date2 = simpleDateFormat.parse(endDate);

            long getDiff = date2.getTime() - date1.getTime();

            long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);

            System.out.println("Differance between date " + startDate + " and " + endDate + " is " + getDaysDiff + " days.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
