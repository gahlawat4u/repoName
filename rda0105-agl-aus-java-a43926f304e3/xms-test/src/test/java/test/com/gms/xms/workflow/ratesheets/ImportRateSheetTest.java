package test.com.gms.xms.workflow.ratesheets;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from May 9, 2016 5:20:03 PM
 * <p>
 * Author huynd
 */
public class ImportRateSheetTest {
    public static void main(String[] args) throws Exception {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase2 context = new ContextBase2(addInfoMap);
        context.put(Attributes.SHIPMENT_TYPE_ID, 1L);
        context.put(Attributes.SHEET_NAME, "Test");
        context.put(Attributes.CARRIER_COST, 1);
        context.put(Attributes.PER_WEIGHT, 0);
        context.put(Attributes.CONTENT, 0);
        context.put(Attributes.BOUND, 0);
        context.put(Attributes.RATE_SHEET_FILE, "C:\\Users\\H P\\Desktop\\Draft\\Book1.xls");
        context.put(Attributes.WFP_NAME, "Wfl-ImportRateSheet");
        context = WorkFlowManager2.getInstance().process(context);
    }
}
