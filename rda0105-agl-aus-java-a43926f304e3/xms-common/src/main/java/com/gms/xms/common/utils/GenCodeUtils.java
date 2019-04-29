package com.gms.xms.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * Posted from GenCodeUtils
 * <p>
 * Author DatTV Date May 22, 2015 6:47:15 PM
 */
public class GenCodeUtils {
    protected static Log log = LogFactory.getLog(GenCodeUtils.class);

    public static String generateCreditCode(String invoiceCode, Date date) {
        String creditCode = "C";
        creditCode += invoiceCode;
        creditCode += "-" + createDateFormat(date);
        return creditCode;
    }

    public static String generateInvoiceCode(String customerCode, Date date) {
        String invoiceCode = "";
        invoiceCode += customerCode + createDateFormat(date);
        return invoiceCode;
    }

    public static String generateFRInvoiceCode(String customerCode, Date date, final int currentUniqueNumber) {
        String invoiceCode = generateInvoiceCode(customerCode, date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR) % 100;
        int month = calendar.get(Calendar.MONTH) + 1;
        String result = String.format("%02d", year) + String.format("%02d", month) + String.format("%05d", currentUniqueNumber);
        return result + "-" + invoiceCode;
    }

    public static String generateFRInvoiceCode4Printable(String franchiseCode, Date date, final int currentUniqueNumber) {
        String result = generateFRInvoiceCode(franchiseCode + "00001", date, currentUniqueNumber);
        String prefix = result.substring(0, 12);
        String surfix = result.substring(18, 22);
        return prefix + surfix;
    }

    public static String generateFranchiseReceivableInvoiceCode(String franchiseCode, int year, int month, int counter, Date endDate) {
        String invoiceCode = generateInvoiceCode(franchiseCode, endDate);
        String result = String.format("%02d", year) + String.format("%02d", month) + String.format("%05d", counter);
        result += "-" + invoiceCode;
        return result;
    }

    public static String createDateFormat(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String result = "";
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        int startYear = 2001;
        if (year == startYear) {
            result += "A";
        } else {
            int diff = year - startYear;
            int current = 65 + diff;
            if (current >= 73) {
                current++;
                if (current > 90) {
                    if (current - 26 > 73) {
                        result += "Z" + (char) (current - 25);
                    } else {
                        result += "Z" + (char) (current - 26);
                    }
                } else {
                    result += (char) current;
                }
            }
        }

        switch (month) {
            case 1:
                result += "A";
                break;
            case 2:
                result += "B";
                break;
            case 3:
                result += "C";
                break;
            case 4:
                result += "D";
                break;
            case 5:
                result += "E";
                break;
            case 6:
                result += "F";
                break;
            case 7:
                result += "G";
                break;
            case 8:
                result += "H";
                break;
            case 9:
                result += "J";
                break;
            case 10:
                result += "K";
                break;
            case 11:
                result += "L";
                break;
            case 12:
                result += "M";
                break;
        }

        if (day >= 10) {
            result += day;
        } else {
            result += "0" + day;
        }

        return result;
    }

    public static String createQuoteJobNumber(String customerCode, String oldQuoteJobNumber) {
        StringBuilder builder = new StringBuilder();
        int length = 4;
        String currentChar = oldQuoteJobNumber;
        if (StringUtils.isBlank(currentChar)) {
            builder.append("AAAA");
        } else {
            int startPos = oldQuoteJobNumber.length() - 4;
            int endPost = oldQuoteJobNumber.length();
            currentChar = oldQuoteJobNumber.substring(startPos, endPost);

            String[] ret_arr = new String[4];
            int i;
            for (i = length - 1; i >= 0; i--) {
                int new_char = currentChar.charAt(i) + 1;
                if (new_char > 90) {
                    ret_arr[i] = "A";
                } else {
                    ret_arr[i] = String.valueOf((char) new_char);
                    break;
                }
            }
            i--;
            while (i >= 0) {
                ret_arr[i] = String.valueOf(currentChar.charAt(i));
                i--;
            }
            for (i = 0; i < length; i++) {
                builder.append(ret_arr[i]);
            }
        }

        return customerCode + builder.toString();
    }
}
