package com.gms.xms.common.utils;

import com.gms.xms.common.constants.AppConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

/**
 * Posted from FormaterUtils
 * <p>
 * Author DatTV Dec 17, 2015
 */
public class FormaterUtils {
    protected static Log log = LogFactory.getLog(FormaterUtils.class);

    public static String int2String(Long num) {
        NumberFormat formater = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultIntNumberFormat());
        return formater.format(num);
    }

    public static String double2String(Double num) {
        NumberFormat formater = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultNumberFormat());
        return formater.format(num);
    }

    public static String date2String(Date date) {
        return DateUtils.convertDateToString(date, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
    }

    public static double string2Double(String n) {
        n = n.replace(",", "");
        return Double.valueOf(n);
    }
}
