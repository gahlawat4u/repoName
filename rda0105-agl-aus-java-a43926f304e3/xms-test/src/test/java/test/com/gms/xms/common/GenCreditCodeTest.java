package test.com.gms.xms.common;

import com.gms.xms.common.utils.GenCodeUtils;

import java.util.Calendar;

public class GenCreditCodeTest {
    public static void main(String[] args) throws Exception {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());
        System.out.println(GenCodeUtils.generateCreditCode("99800007PC21", cal.getTime()));
        String quoteJobNumber = "10000001ACAB"; //GenCodeUtils.createQuoteJobNumber("10000001", "");
        System.out.println("Old quote job number: " + quoteJobNumber);
        System.out.println("New quote job number: " + GenCodeUtils.createQuoteJobNumber("10000001", quoteJobNumber));
    }
}