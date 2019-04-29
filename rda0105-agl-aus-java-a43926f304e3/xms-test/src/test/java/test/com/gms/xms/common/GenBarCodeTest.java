package test.com.gms.xms.common;

import com.gms.xms.common.utils.AppUtils;

import java.io.IOException;

/**
 * Posted from GenBarCodeTest
 * <p>
 * Author HungNT Date Aug 4, 2015
 */
public class GenBarCodeTest {
    public static void main(String[] args) throws IOException {
        AppUtils.createBarCode("hung dep trai", "png", "D:/barcode2.png", 500, 200);
    }
}
