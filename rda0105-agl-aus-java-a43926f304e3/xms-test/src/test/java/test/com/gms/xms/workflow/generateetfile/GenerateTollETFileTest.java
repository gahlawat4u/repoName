package test.com.gms.xms.workflow.generateetfile;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from Sep 15, 2016 4:55:26 PM
 * <p>
 * Author huynd
 */
public class GenerateTollETFileTest {
    public static void main(String[] args) throws Exception {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase2 context = new ContextBase2(addInfoMap);
        context.put(GenerateETFileConstants.FILE_PATH, "D:\\xampp\\htdocs\\xms2\\xms-admin\\src\\main\\webapp\\edi_file\\toll_manifest_file\\");
        context.put(Attributes.WFP_NAME, "Wfl-GenerateTollETFile");
        context = WorkFlowManager2.getInstance().process(context);
        // System.out.println("Toll shipment list: " +
        // context.get(GenerateETFileConstants.TOLL_SHIPMENT_GENERATE_LIST));
    }
}
