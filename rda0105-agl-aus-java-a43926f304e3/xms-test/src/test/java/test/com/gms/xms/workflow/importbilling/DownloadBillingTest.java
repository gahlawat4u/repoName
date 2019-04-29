package test.com.gms.xms.workflow.importbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from Jun 2, 2016 2:08:51 PM
 * <p>
 * Author huynd
 */
public class DownloadBillingTest {
    public static void main(String[] args) throws Exception {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase2 context = new ContextBase2(addInfoMap);
        String filePath = "D:\\xampp\\htdocs\\xms2\\xms-admin\\src\\main\\webapp\\download_billing_files\\";
        context.put(Attributes.DOWNLOAD_FILE_PATH, filePath);
        context.put(Attributes.WFP_NAME, "Wfl-DownloadBillingDHL");
        context = WorkFlowManager2.getInstance().process(context);
        System.out.println("Billing results: " + context.get(Attributes.BILLING_RESULTS));
    }
}
