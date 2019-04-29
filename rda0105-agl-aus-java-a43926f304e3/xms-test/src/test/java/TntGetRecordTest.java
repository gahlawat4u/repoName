import com.gms.xms.common.constants.AppConstants.PADDING_TYPE;
import com.gms.xms.common.utils.AppUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Posted from Sep 25, 2016 4:30:35 PM
 * <p>
 * Author dattrinh
 */
public class TntGetRecordTest {
    public static void main(String[] args) {
        System.out.println("[" + getRecord("123", "CHAR", 20, "BF") + "]");
        System.out.println("[" + getRecord("123", "CHAR", 20, "BF", PADDING_TYPE.RIGHT) + "]");
        System.out.println("[" + getRecord("123433.873671454", "NUM9.3", 9, "BF") + "]");
    }

    public static String pad(String text, int length, char paddingChar, PADDING_TYPE paddingType) {
        if (paddingType == PADDING_TYPE.LEFT) {
            return StringUtils.leftPad(text, length, paddingChar);
        } else {
            return StringUtils.rightPad(text, length, paddingChar);
        }
    }

    public static String getRecord(String text, final String type, final int length, final String format) {
        return getRecord(text, type, length, format, PADDING_TYPE.LEFT);
    }

    public static String getRecord(String text, final String type, final int length, final String format, PADDING_TYPE padding) {
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
                String[] s = flen.split("\\.");
                int point = Integer.valueOf(s[s.length - 1]);
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

    public static boolean isFloat(String text) {
        if (StringUtils.isBlank(text))
            return false;
        try {
            Double.valueOf(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
