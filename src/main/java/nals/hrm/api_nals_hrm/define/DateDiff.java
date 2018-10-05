package nals.hrm.api_nals_hrm.define;

import nals.hrm.api_nals_hrm.exception.CustomException;

import java.util.Date;

public class DateDiff {
  public static long dateDiff(Date startDate, Date endDate) {

    try {

      long getDiff = endDate.getTime() - startDate.getTime();
//            long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
      return getDiff / (24 * 60 * 60 * 1000);
    } catch (Exception e) {
      throw new CustomException("Error server!", 500);
    }
  }
//    public static void main(String[] args) {
//        String startDate = "10-08-2018";
//        String endDate = "20-08-2018";
//
//        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        Date date1 = null;
//        Date date2 = null;
//        try {
//            date1 = simpleDateFormat.parse(startDate);
//            date2 = simpleDateFormat.parse(endDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(dateDiff(date1,date2));
//    }
}