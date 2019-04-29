package com.gms.xms.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Posted from May 26, 2016 4:22:12 PM
 * <p>
 * Author dattrinh
 */
public class GlobalFunctions {

    protected static Log log = LogFactory.getLog(GlobalFunctions.class);

    public static double weightUnitConvert(String weightUnit, double totWeight) {
        if ("lb".equalsIgnoreCase(weightUnit)) {
            // 1 kilogram = 2.20462262 pounds
            double conv = 2.20462262;
            totWeight = totWeight / conv;
            totWeight = new BigDecimal(totWeight).setScale(2, RoundingMode.CEILING).doubleValue();
        }
        return totWeight;
    }

    public static double weightUnitConvertKgToPound(String weightUnit, double displayWeight) {
        if ("lb".equalsIgnoreCase(weightUnit)) {
            // 1 kilogram = 2.20462262 pounds
            double conv = 2.20462262;
            displayWeight = displayWeight * conv;
            displayWeight = new BigDecimal(displayWeight).setScale(2, RoundingMode.CEILING).doubleValue();
        }
        return displayWeight;
    }

    public static double dimensionUnitConvert(String unit, double length) {
        if ("in".equalsIgnoreCase(unit)) {
            length = new BigDecimal(length * 2.54).setScale(2, RoundingMode.CEILING).doubleValue();
        }
        return length;
    }

    public static double getGrossWeight(double l, double w, double h, String dimensionUnit, int forceVolWeight) {
        double grossWeight = l * w * h / forceVolWeight;
        if (!"cm".equalsIgnoreCase(dimensionUnit)) {
            double cm = 2.54;
            grossWeight = grossWeight * (cm * cm * cm);
        }
        return grossWeight;
    }

    public static double getQuoteWeightByPiece(double weight, double l, double w, double h, String weightUnit, int forceVolWeight, String roundType) {
        String dimUnit = "kg".equalsIgnoreCase(weightUnit) ? "cm" : "in";
        weight = weightUnitConvert(weightUnit, weight);
        double grossWeight = getGrossWeight(l, w, h, dimUnit, forceVolWeight);
        if ("CEIL".equalsIgnoreCase(roundType)) {
            weight = Math.ceil(weight);
            grossWeight = Math.ceil(grossWeight);
        }
        return (weight > grossWeight) ? weight : grossWeight;
    }
}
