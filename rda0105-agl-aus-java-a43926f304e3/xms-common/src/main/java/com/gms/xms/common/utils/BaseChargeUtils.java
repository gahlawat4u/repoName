package com.gms.xms.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from Apr 9, 2016 10:39:36 AM
 * <p>
 * Author dattrinh
 */
public class BaseChargeUtils {
    protected static Log log = LogFactory.getLog(BaseChargeUtils.class);

    public static Map<String, Double> calculateBaseCharge(Integer rateType, Double baseRate, Double miniumBaseChargePercent, Double rowName, Double cRate, Double ncRate) {
        Double mRate = 0D;
        Double mPercent = 0D;
        Double tmpRate = 0D;
        switch (rateType) {
            case 0: // DHL
                ncRate = ncRate - ((ncRate * baseRate) / 100);

                mRate = ncRate - cRate;
                if (ncRate == 0D) {
                    mPercent = 0D;
                } else {
                    mPercent = (mRate / ncRate) * 100;
                }

                if (mPercent < miniumBaseChargePercent) {
                    baseRate = miniumBaseChargePercent;
                    tmpRate = 1 - (baseRate / 100);
                    if (tmpRate != 0D)
                        ncRate = cRate / tmpRate;
                    else
                        ncRate = cRate * baseRate;
                    mRate = ncRate - cRate;
                    if (ncRate == 0)
                        mPercent = 0D;
                    else
                        mPercent = (mRate / ncRate) * 100;
                }
                break;
            case 1: // Mark up
                ncRate = (cRate + ((baseRate / 100) * cRate));
                mRate = (ncRate - cRate);
                if (ncRate != 0) {
                    mPercent = (mRate / ncRate) * 100;
                } else {
                    mPercent = 0D;
                }
                if (mPercent < miniumBaseChargePercent) {
                    baseRate = miniumBaseChargePercent;
                    tmpRate = 1 - (baseRate / 100);
                    if (!tmpRate.equals(0)) {
                        ncRate = (cRate / tmpRate);
                    } else {
                        ncRate = cRate * baseRate;
                    }
                }
                break;
            case 2: // Margin
                if (baseRate < miniumBaseChargePercent) {
                    baseRate = miniumBaseChargePercent;
                }
                tmpRate = 1 - (baseRate / 100);
                if (!tmpRate.equals(0)) {
                    ncRate = cRate / tmpRate;
                } else {
                    ncRate = cRate * baseRate;
                }
                break;
            case 3: // Topup
                ncRate = (cRate / (1 - (baseRate / 100)));
                ncRate = rowName * ncRate;
                cRate = rowName * cRate;
                break;
        }
        Map<String, Double> rate = new HashMap<>();
        rate.put("ncRate", ncRate);
        rate.put("cRate", cRate);
        return rate;
    }
}
