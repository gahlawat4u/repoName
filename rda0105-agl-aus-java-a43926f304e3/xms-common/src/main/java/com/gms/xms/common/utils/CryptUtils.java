package com.gms.xms.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Posted from CryptUtils.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 4:58:50 PM
 */
public class CryptUtils {
    /**
     * Encrypts a text with key.
     *
     * @param text - string to encrypt
     * @param key  - key to encrypt
     * @return - encrypted string
     * @throws UnsupportedEncodingException - on error
     */
    public static String Encrypt(String text, String key) throws UnsupportedEncodingException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char kc;

            int pos = (i % key.length()) - 1;
            if (pos >= 0) {
                kc = key.charAt(pos);
            } else {
                kc = key.charAt(key.length() + pos);
            }

            byteArray.write(c + kc);
        }

        return new String(Base64.encodeBase64(byteArray.toByteArray()), "ISO-8859-1");
    }

    /**
     * Return a string from string encrypted
     *
     * @param text - encrypted string
     * @param key  - encrypted key
     * @return - string
     * @throws UnsupportedEncodingException
     */
    public static String Decrypt(String text, String key) throws UnsupportedEncodingException {
        String plainText = "";
        String s = new String(Base64.decodeBase64(text.getBytes()), "ISO-8859-1");

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char kc;

            int pos = (i % key.length()) - 1;
            if (pos >= 0) {
                kc = key.charAt(pos);
            } else {
                kc = key.charAt(key.length() + pos);
            }

            c = (char) (c - kc);
            plainText += c;
        }

        return plainText;
    }
}
