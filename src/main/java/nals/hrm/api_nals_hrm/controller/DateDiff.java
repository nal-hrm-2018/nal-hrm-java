package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.exception.CustomException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDiff {
    public static long dateDiff(Date startDate, Date endDate){
        try {
            long getDiff = endDate.getTime() - startDate.getTime();
            return getDiff / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            throw new CustomException("Error server!",500);
        }
    }
}
