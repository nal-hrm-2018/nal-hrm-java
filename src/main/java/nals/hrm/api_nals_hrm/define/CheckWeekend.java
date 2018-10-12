package nals.hrm.api_nals_hrm.define;

import java.util.Date;

public class CheckWeekend {

  public static int checkDate(Date date) {
    String fullDate = date.toString().split(" ")[0];
    switch (fullDate) {
      case "Mon":
        return 2;
      case "Tue":
        return 3;
      case "Wed":
        return 4;
      case "Thu":
        return 5;
      case "Fri":
        return 6;
      case "Sat":
        return 7;
      default:
        return 8;
    }
  }

  public static int countWeekend(Date from, Date to) {
    int count = 0;
    while (from.compareTo(to) <= 0) {
      if (checkDate(from) >= 7) count++;
      from.setDate(from.getDate() + 1);
    }
    return count;
  }
//    public static void main(String[] args) {
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//        Date startDate = null,endDate = null;
//        try {
//            startDate = df.parse("09/02/2018");
//            endDate = df.parse("09/02/2018");
//            System.out.println(countWeekend(startDate, endDate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }
}
