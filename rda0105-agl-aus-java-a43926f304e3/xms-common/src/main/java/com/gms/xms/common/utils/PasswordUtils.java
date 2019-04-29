package com.gms.xms.common.utils;

import com.gms.xms.common.constants.AppConstants;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Posted from EmailUtils
 * <p>
 * Author DatTV Date Jul 9, 2015 10:29:17 AM
 */
public class PasswordUtils {
    public static String generate(int length) {
        String passwordChars = AppConstants.APP_SETTINGS.getPasswordChars();
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int max = passwordChars.length();
        for (int i = 0; i < length; i++) {
            builder.append(passwordChars.charAt(random.nextInt(max)));
        }
        return builder.toString();
    }

    public static boolean isValid(String pass) {
        Pattern pattern = Pattern.compile(AppConstants.APP_SETTINGS.getPasswordPattern());
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }
}
