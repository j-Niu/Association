package com.future.association.community.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class DateUtils {

    public static SimpleDateFormat sdfMysql = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
    public static SimpleDateFormat sdfChinese = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINESE);
    public static SimpleDateFormat sdf_ymd_hms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
            Locale.CHINESE);
    public static SimpleDateFormat sdf_ymd_hms_2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",
            Locale.CHINESE);

    public static String chineseToMysql(String chineseDate) {
        String mysqlDate = null;
        try {
            mysqlDate = sdfMysql.format(sdfChinese.parse(chineseDate));
        } catch (Exception e) {
            return null;
        }
        return mysqlDate;
    }

    public static String mysqlToChinese(String mysqlDate) {
        String chineseDate = null;
        try {
            chineseDate = sdfChinese.format(sdfMysql.parse(mysqlDate));
        } catch (Exception e) {
            return null;
        }
        return chineseDate;
    }

    /**
     * @param date
     * @return
     */
    public static String getDateTimeString(Date date) {
        String dateTimeString = null;
        try {
            dateTimeString = sdf_ymd_hms.format(date);
        } catch (Exception e) {
            return null;
        }
        return dateTimeString;
    }

    /**
     * @param date   年月日格式时间
     * @param format
     * @return
     */
    public static String dateFormat(String date, String format) {
        try {
            return getDateForTimestamp(getStamp4Date(date, "yyyy年MM月dd日") * 1000 + "", format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 通过时间获取时间戳 秒
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static long getStamp4Date(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(dateStr);
            return date.getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    //
    public static Calendar getWeekFirstDay() {
        //
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 2);

        return c;
    }

    //
    public static int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }

        return age;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smTime 较小的时间
     * @param bTime  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(String smTime, String bTime) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf1.parse(smTime);
        Date d2 = sdf1.parse(bTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date smdate = sdf.parse(sdf.format(d1));
        Date bdate = sdf.parse(sdf.format(d2));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days + 1));
    }

    /**
     * 将时间戳格式化
     *
     * @param timeStamp
     * @param dateFormat
     * @return
     */
    public static String getDateForTimestamp(String timeStamp, String dateFormat) {
        long timestamp = StringUtils.stringIsInteger(timeStamp);
        if (timestamp != -1) {
            Date date = new Date();
            if ((timestamp + "").length() < 12) {
                timestamp = timestamp * 1000;
            }
            date.setTime(timestamp);
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            return sdf.format(date);
        } else {
            return timeStamp;
        }
    }

    /**
     * 计算某月有多少天
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static int getDayOfMonth(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, date.getYear());
            calendar.set(Calendar.MONTH, date.getMonth());
            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            return days;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算某月有多少周
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static int getWeekOfMonth(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, date.getYear()+1900);
            calendar.set(Calendar.MONTH, date.getMonth());
            int days = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
            return days;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取某个月的第一天是星期几
     * @param dateStr
     * @param fromat
     * @return
     */
    public static int getMonthMinDayOfWeek(String dateStr,String fromat){
        SimpleDateFormat sdf = new SimpleDateFormat(fromat) ;
        try {
            Date date = sdf.parse(dateStr) ;
            Calendar calendar = Calendar.getInstance() ;
            calendar.set(Calendar.YEAR,date.getYear()+1900);
            calendar.set(Calendar.MONTH,date.getMonth());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            int result = calendar.get(Calendar.DAY_OF_WEEK)-1 ;
            return result == 0?7:result;//格林威治时间1代表的是星期天
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0 ;
    }
    /**
     * 获取某个月的最后一天是星期几
     * @param dateStr
     * @param fromat
     * @return
     */
    public static int getMonthMaxDayOfWeek(String dateStr,String fromat){
        SimpleDateFormat sdf = new SimpleDateFormat(fromat) ;
        try {
            Date date = sdf.parse(dateStr) ;
            Calendar calendar = Calendar.getInstance() ;
            calendar.set(Calendar.YEAR,date.getYear()+1900);
            calendar.set(Calendar.MONTH,date.getMonth());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            int result = calendar.get(Calendar.DAY_OF_WEEK)-1 ;
            return result == 0?7:result;//格林威治时间1代表的是星期天
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0 ;
    }
    /**
     * 获取当前日期
     *
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取任意时间的下一个月
     *
     * @param repeatDate
     * @return
     */
    public static String getPreMonth(String repeatDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(repeatDate);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR,date.getYear()+1900);
            cal.set(Calendar.MONTH,date.getMonth()+1);
            date = cal.getTime() ;
            return sdf.format(date) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return repeatDate;
    }

    /**
     * 获取任意时间的上一个月
     *
     * @param repeatDate
     * @return
     */
    public static String getLastMonth(String repeatDate,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(repeatDate);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR,date.getYear()+1900);
            cal.set(Calendar.MONTH,date.getMonth()-1);
            date = cal.getTime() ;
            return sdf.format(date) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return repeatDate;
    }
}
