package test.com.gms.xms.weblib;

import java.io.File;

/*
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
*/
public class Test {
    public static void main(String[] args) {
        File file = new File("D:/JavaSource/xms2/xms-webship/src/main/webapp/tmp/tntDomViewAirbill5097231457368683574.pdf");
        File file2 = new File("D:/JavaSource/xms2/xms-webship/src/main/webapp/tmp/980205184260.pdf");
        file.renameTo(file2);
    }
}