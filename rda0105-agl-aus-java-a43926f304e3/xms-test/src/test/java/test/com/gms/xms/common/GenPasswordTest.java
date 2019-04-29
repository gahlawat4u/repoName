package test.com.gms.xms.common;

import com.gms.xms.common.utils.PasswordUtils;

public class GenPasswordTest {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(PasswordUtils.generate(12));
        }
    }
}