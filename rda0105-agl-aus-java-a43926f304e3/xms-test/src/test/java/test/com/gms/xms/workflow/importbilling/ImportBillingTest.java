package test.com.gms.xms.workflow.importbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from May 24, 2016 3:49:51 PM
 * <p>
 * Author huynd
 */
public class ImportBillingTest {
    public static void main(String[] args) throws Exception {
        String carrierId = "72";
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase2 context = new ContextBase2(addInfoMap);
        context.put(Attributes.INVOICE_DATE, "22-06-2016");
        context.put(Attributes.DATE_FORMAT, "ddMMyyyy");
        context.put(Attributes.HAS_HEADER, true);
        context.put(Attributes.EXTENSION, "csv");
        context.put(Attributes.SERVICE_ID, Integer.valueOf(carrierId));
        switch (carrierId) {
            case "1":
                break;
            case "3":
                // TNT Domestic
                context.put(Attributes.BILLING_FILE, "C:\\Users\\H P\\OneDrive\\Projects\\XMS 2.0\\Import billing\\tnt_domestic\\tnt_domestic_import_file.csv");
                context.put(Attributes.WFP_NAME, "Wfl-ImportBillingTntDomestic");
                break;
            case "15":
                break;
            case "54":
                // TNT International
                context.put(Attributes.BILLING_FILE, "C:\\Users\\H P\\OneDrive\\Projects\\XMS 2.0\\Import billing\\tnt_intl\\tnt_intl_import_file.csv");
                context.put(Attributes.WFP_NAME, "Wfl-ImportBillingTntIntl");
                break;
            case "52":
                // Toll Priority
                context.put(Attributes.BILLING_FILE, "C:\\Users\\H P\\OneDrive\\Projects\\XMS 2.0\\Import billing\\toll_priority\\toll_priority_import_file_2.csv");
                context.put(Attributes.WFP_NAME, "Wfl-ImportBillingTollPriority");
                break;
            case "59":
                // Toll Ipec
                context.put(Attributes.BILLING_FILE, "C:\\Users\\H P\\OneDrive\\Projects\\XMS 2.0\\Import billing\\toll_ipec\\toll_ipec_import_file.csv");
                context.put(Attributes.WFP_NAME, "Wfl-ImportBillingTollIpec");
                break;
            case "72":
                // Startrack
                context.put(Attributes.BILLING_FILE, "C:\\Users\\H P\\OneDrive\\Projects\\XMS 2.0\\Import billing\\startrack\\startrack_import_file.csv");
                context.put(Attributes.WFP_NAME, "Wfl-ImportBillingStartrack");
                break;
        }
        context = WorkFlowManager2.getInstance().process(context);
        BillingDataModel billingData = context.get(Attributes.BILLING_DATA);
        System.out.println("Billing data: \n" + billingData);
        System.out.println("Number of row: " + billingData.getRowData().size());
        System.out.println("Imported successfull airbill: " + context.get(Attributes.COUNT_IMPORT_BILLING_SUCCESS));
        System.out.println("Imported unsuccessfull airbill: " + context.get(Attributes.COUNT_IMPORT_BILLING_UNSUCCESS));
    }
}
