package com.youfan.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2018/10/7 0007.
 */
public class DateUtils {

    public static long getBetweendays(Date start, Date end){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh");
        System.out.println(dateFormat.format(start)+"=="+dateFormat.format(end));
        Calendar calendarstart = Calendar.getInstance();
        calendarstart.setTime(start);
        Calendar calendarend = Calendar.getInstance();

        calendarend.setTime(end);
        long days = 0;
//        while((calendarstart.getTime().getTime()-calendarend.getTime().getTime())<0){
//            days++;
//            calendarstart.add(Calendar.DAY_OF_MONTH,1);
//        }
        while(calendarstart.compareTo(calendarend)<=0){
            days++;
            calendarstart.add(Calendar.DAY_OF_MONTH,1);
        }
        return days;
    }

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh");
        try {
            Date datestart = dateFormat.parse("2018-05-07 06");
            Date dateend = dateFormat.parse("2018-05-06 12");
            long betweendays = getBetweendays(datestart,dateend);
            System.out.println("相差天数是："+betweendays);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
