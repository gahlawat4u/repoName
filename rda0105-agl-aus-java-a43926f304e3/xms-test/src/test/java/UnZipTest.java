import com.gms.xms.common.utils.ZipFileUtils;
import com.gms.xms.workflow.service.TntDomImportRateSheetParser;

import java.util.HashMap;
import java.util.List;

/**
 * Posted from Nov 22, 2016 2:40:33 PM
 * <p>
 * Author dattrinh
 */
public class UnZipTest {
    public static void main(String[] args) {
        try {
            List<String> files = ZipFileUtils.unZipIt("D:\\opt\\Rates 2016-11.zip", "D:\\opt\\result");
            TntDomImportRateSheetParser parser;
            for (String file : files) {
                parser = new TntDomImportRateSheetParser(new HashMap<String, String>(), file, "TNT Domestic - Test");
                try {
                    parser.parser();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
