package com.gms.xms.dto.edigenerate.tntrecord;

import com.gms.xms.common.constants.AppConstants.PADDING_TYPE;
import com.gms.xms.common.utils.AppUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Posted from Sep 23, 2016 11:35:44 AM
 * <p>
 * Author dattrinh
 */
public abstract class TntRecordBase {
    private String recordType;

    public abstract String getRow();

    public abstract boolean isValidRow();

    protected String getRecord(String text, final String type, final int length, final String format) {
        return getRecord(text, type, length, format, PADDING_TYPE.LEFT);
    }

    private String pad(String text, int length, char paddingChar, PADDING_TYPE paddingType) {
        if (paddingType == PADDING_TYPE.LEFT) {
            return StringUtils.leftPad(text, length, paddingChar);
        } else {
            return StringUtils.rightPad(text, length, paddingChar);
        }
    }

    protected String getRecord(String text, final String type, final int length, final String format, PADDING_TYPE padding) {
        if (text.length() > length) {
            text = text.substring(0, length);
            // return "NO1";
        }
        if (StringUtils.isBlank(format)) {
            if (text.length() != length)
                return "NO2";
            else
                return text;
        } else {
            if ("CHAR".equalsIgnoreCase(type) && isFloat(text)) {
                // return "NO3";
            } else if ("NUM".equalsIgnoreCase(type.substring(0, 3)) && isFloat(text) && !StringUtils.isBlank(text)) {
                // return "NO4";
            }
            if ("CHAR".equalsIgnoreCase(type)) {
                if ("BF".equalsIgnoreCase(format)) {
                    return pad(text, length, ' ', padding);
                } else if ("ZF".equalsIgnoreCase(format)) {
                    return pad(text, length, '0', padding);
                }
            } else if ("NUM".equalsIgnoreCase(type.substring(0, 3))) {
                String flen = type.substring(3).trim();
                if (!StringUtils.isBlank(flen) && Double.valueOf(flen).intValue() != length) {
                    return "NO5";
                }
                int point = 0;
                if (!StringUtils.isBlank(flen)) {
                    String[] s = flen.split("\\.");
                    if (s != null && s.length > 0) {
                        point = Integer.valueOf(s[s.length - 1]);
                    }
                }
                if (isFloat(text) && String.valueOf(Double.valueOf(text).intValue()).length() != length - 2) {
                    text = AppUtils.formatNumber(text, point);
                    if (text.length() > length) {
                        text = text.substring(0, 8);
                    }
                }
                if ("BF".equalsIgnoreCase(format)) {
                    text = pad(text, length, ' ', padding);
                } else if ("ZF".equalsIgnoreCase(format)) {
                    text = pad(text, length, '0', padding);
                }
                return text;
            }
            return "NO9";
        }
    }

    protected boolean isFloat(String text) {
        if (StringUtils.isBlank(text))
            return false;
        try {
            Double.valueOf(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}
