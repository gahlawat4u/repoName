package com.gms.xms.common.utils;

import com.gms.xms.common.config.dto.PickupTime;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Ported from DateUtil.java
 * <p>
 * Author: Toan Tran Date: August 26, 2008 Time: 4:29:58 PM
 */
public class DateUtils {
    public enum Type {
        BY_DAY, BY_HOUR, BY_MINUTE, BY_SECOND, BY_MILLISECOND
    }

    public static final String GLOBAL_FORMAT = "yyMMddHHmmssS";

    // ------ Log object
    protected static Log log = LogFactory.getLog(DateUtils.class);

    /**
     * Returns a String date from Date object.
     *
     * @param date     - Date to convert
     * @param format   - date format
     * @param timeZone - timeZone in GMT
     * @return Date object. Return null on error
     */
    public static String convertDateToString(Date date, String format, String timeZone) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (!StringUtils.isBlank(timeZone)) {
                sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
            }
            return sdf.format(date);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    /**
     * Returns a Date object from String date.
     *
     * @param date     - string to convert
     * @param format   - date format
     * @param timeZone - timeZone in GMT
     * @return String object. Return null on error
     */
    public static Date convertStringToDate(String strDate, String format, String timeZone) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (!StringUtils.isBlank(timeZone)) {
                sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
            }
            return sdf.parse(strDate);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public static String convertTime2String(Time time, String format, String timeZone) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (!StringUtils.isBlank(timeZone)) {
                sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
            }
            return sdf.format(time);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    public static long dateDiff(Type diffUom, Date fromDate, Date toDate) {

        Calendar cal = Calendar.getInstance();

        if (diffUom == Type.BY_DAY) {
            cal.set(fromDate.getYear() + 1900, fromDate.getMonth(), fromDate.getDate(), 0, 0, 0);
        } else if (diffUom == Type.BY_HOUR) {
            cal.set(fromDate.getYear() + 1900, fromDate.getMonth(), fromDate.getDate(), fromDate.getHours(), 0, 0);
        } else if (diffUom == Type.BY_MINUTE) {
            cal.set(fromDate.getYear() + 1900, fromDate.getMonth(), fromDate.getDate(), fromDate.getHours(), fromDate.getMinutes(), 0);
        } else if (diffUom == Type.BY_SECOND)
            cal.set(fromDate.getYear() + 1900, fromDate.getMonth(), fromDate.getDate(), fromDate.getHours(), fromDate.getMinutes(), fromDate.getSeconds());
        else if (diffUom == Type.BY_MILLISECOND)
            cal.setTime(fromDate);

        long fromTime = cal.getTimeInMillis();

        if (diffUom == Type.BY_DAY) {
            cal.set(toDate.getYear() + 1900, toDate.getMonth(), toDate.getDate(), 0, 0, 0);
        } else if (diffUom == Type.BY_HOUR) {
            cal.set(toDate.getYear() + 1900, toDate.getMonth(), toDate.getDate(), toDate.getHours(), 0, 0);
        } else if (diffUom == Type.BY_MINUTE) {
            cal.set(toDate.getYear() + 1900, toDate.getMonth(), toDate.getDate(), toDate.getHours(), toDate.getMinutes(), 0);
        } else if (diffUom == Type.BY_SECOND)
            cal.set(toDate.getYear() + 1900, toDate.getMonth(), toDate.getDate(), toDate.getHours(), toDate.getMinutes(), toDate.getSeconds());
        else if (diffUom == Type.BY_MILLISECOND)
            cal.setTime(toDate);

        long endTime = cal.getTimeInMillis();

        long diffTimeInMillis = endTime - fromTime;

        if (diffUom == Type.BY_DAY) {
            return (diffTimeInMillis / 1000) / (60 * 60 * 24);
        } else if (diffUom == Type.BY_HOUR)
            return (diffTimeInMillis / 1000) / (60 * 60);
        else if (diffUom == Type.BY_MINUTE)
            return (diffTimeInMillis / 1000) / (60);
        else if (diffUom == Type.BY_SECOND)
            return (diffTimeInMillis / 1000);
        else if (diffUom == Type.BY_MILLISECOND)
            return diffTimeInMillis;
        return -1;
    }

    public static List<Date> createPeriodList(Date now, int periodType, Date start, Date end, Integer numberOfPeriods) {
        List<Date> periodList = new ArrayList<Date>();
        Date startDate = null;
        Integer num = numberOfPeriods;
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        switch (periodType) {
            case 1:
                cal.add(Calendar.DATE, -(numberOfPeriods - 1));
                startDate = cal.getTime();
                break;
            case 2:
                cal.add(Calendar.DATE, -(numberOfPeriods - 1) * 7);
                cal.add(Calendar.DATE, (8 - cal.get(Calendar.DAY_OF_WEEK)));
                cal.add(Calendar.DATE, -6);
                startDate = cal.getTime();
                break;
            case 3:
                cal.setTime(start);
                startDate = cal.getTime();
                num = -1;
                break;
            case 4:
                cal.add(Calendar.DATE, -(numberOfPeriods - 1) * 7);
                cal.add(Calendar.DATE, (8 - cal.get(Calendar.DAY_OF_WEEK)));
                cal.add(Calendar.DATE, -6);
                startDate = cal.getTime();
                break;
        }
        cal.setTime(startDate);
        if (num != -1) {
            while (num-- > 0) {
                if (periodType == 1 || periodType == 3) {
                    periodList.add(cal.getTime());
                    cal.add(Calendar.DATE, 1);
                } else {
                    periodList.add(cal.getTime());
                    cal.add(Calendar.DATE, 7);
                }
            }
        } else {
            while (!startDate.after(end)) {
                periodList.add(cal.getTime());
                cal.add(Calendar.DATE, 1);
                startDate = cal.getTime();
            }
        }
        return periodList;
    }

    public static List<Date> createDailyPeriod(Date startDate, Date endDate) {
        List<Date> periodList = new ArrayList<Date>();
        Date start = startDate;
        Date end = endDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        while (!start.after(end)) {
            periodList.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
            start = cal.getTime();
        }
        return periodList;
    }

    public static List<Date> createDailyPeriod(Date startDate, int days) {
        List<Date> periodList = new ArrayList<Date>();
        Date start = startDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        int n = days;
        for (int i = 0; i < n; i++) {
            periodList.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }
        return periodList;
    }

    public static List<Date> createWeeklyPeriod(Date startDate, Date endDate) {
        List<Date> periodList = new ArrayList<Date>();
        Date start = startDate;
        Date end = endDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.DATE, (2 - cal.get(Calendar.DAY_OF_WEEK)));
        start = cal.getTime();
        while (!start.after(end)) {
            periodList.add(cal.getTime());
            cal.add(Calendar.DATE, 7);
            start = cal.getTime();
        }
        return periodList;
    }

    public static List<Date> createWeeklyPeriod(Date startDate, int weeks) {
        List<Date> periodList = new ArrayList<Date>();
        Date start = startDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.DATE, (2 - cal.get(Calendar.DAY_OF_WEEK)));
        start = cal.getTime();
        int n = weeks;
        for (int i = 0; i < n; i++) {
            periodList.add(cal.getTime());
            cal.add(Calendar.DATE, 7);
        }
        return periodList;
    }

    public static XMLGregorianCalendar convertDate2XMLGregorianCalendar(Date date) {
        try {
            GregorianCalendar gregory = new GregorianCalendar();
            gregory.setTime(date);
            XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
            return calendar;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public static Date convertXMLGregorianCalendar2Date(XMLGregorianCalendar xmlGC) {
        if (xmlGC == null) {
            return null;
        } else {
            return xmlGC.toGregorianCalendar().getTime();
        }
    }

    public static List<PickupTime> createPickupTimeList(int startHour, int endHour) throws Exception {
        if (startHour > endHour) {
            throw new Exception("start hour cannot greater than end hour.");
        }
        if (startHour > 24) {
            throw new Exception("start hour cannot greater than 24.");
        }
        if (endHour > 24) {
            throw new Exception("end hour cannot greater than 24.");
        }
        List<PickupTime> pickupTimeList = new ArrayList<PickupTime>();
        String period = "";
        String timeValue = "";
        String timeDisplay = "";
        int j = 0;
        PickupTime pickupTime = null;
        String sNumber = "";
        for (int i = startHour; i < endHour; i++) {
            j = i > 12 ? i - 12 : i;
            period = i < 12 ? "AM" : "PM";
            sNumber = i >= 10 ? String.valueOf(i) : "0" + String.valueOf(i);
            timeValue = sNumber + ":00:00";
            timeDisplay = String.valueOf(j) + ":00 " + period;
            pickupTime = new PickupTime(timeValue, timeDisplay);
            pickupTimeList.add(pickupTime);
            timeValue = sNumber + ":30:00";
            timeDisplay = String.valueOf(j) + ":30 " + period;
            pickupTime = new PickupTime(timeValue, timeDisplay);
            pickupTimeList.add(pickupTime);
        }
        return pickupTimeList;
    }
}
