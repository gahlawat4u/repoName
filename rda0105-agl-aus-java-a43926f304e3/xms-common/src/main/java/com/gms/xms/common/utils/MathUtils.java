package com.gms.xms.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Posted from MathUtils
 * <p>
 * Author DatTV Date May 29, 2015 3:29:38 PM
 */
public class MathUtils {
    protected static Log log = LogFactory.getLog(MathUtils.class);

    public static double round3Decimal(double num) {
        BigDecimal val = new BigDecimal(num).setScale(3, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
        return val.doubleValue();
    }

    public static Double round(Double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(String.valueOf(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double roundUp(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, BigDecimal.ROUND_UP);
        return bd.doubleValue();
    }

    public static String double2String(Double value, Integer places) {
        String string = new BigDecimal(value).setScale(places, BigDecimal.ROUND_HALF_UP).toString();
        return string;
    }

    public static Double shipmentWeightRound(Double value, boolean isCeil) {
        Double result = null;
        if (isCeil) {
            result = Math.ceil(value);
        } else {
            int intVal = value.intValue();
            Double frac = value - intVal;

            if (frac == 0 || frac == 0.5) {
                result = value;
            } else if (frac < 0.5) {
                result = intVal + 0.5D;
            } else {
                result = intVal + 1.0D;
            }
        }
        return result;
    }

    public static String formatWeightStandard(Double weight)
    {
        NumberFormat formatter = new DecimalFormat("###,##0.00");
        return formatter.format(weight);
    }

    public static Double dimUnitConvert(Double value, String unit) {
        if (unit.equalsIgnoreCase("in")) {
            value = MathUtils.round((value * 2.54), 2);
        }
        return value;
    }
}
