package com.gms.xms.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Posted from EmailUtils
 * <p>
 * Author DatTV Date Jul 9, 2015 10:29:17 AM
 */
public class EmailUtils {
    private static Pattern pattern;
    private static Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    static {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public static boolean isValidEmail(final String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
