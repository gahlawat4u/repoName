package test.com.gms.xms.workflow.generateetfile;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from Sep 26, 2016 8:41:33 AM
 * <p>
 * Author huynd
 */
public class GenerateTollIpecETFileTest {
    public static void main(String[] args) throws Exception {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase2 context = new ContextBase2(addInfoMap);
        context.put(GenerateETFileConstants.FILE_PATH, "D:\\xampp\\htdocs\\xms2\\xms-admin\\src\\main\\webapp\\edi_file\\toll_manifest_file\\");
        context.put(Attributes.WFP_NAME, "Wfl-GenerateTollIpecETFile");
        context = WorkFlowManager2.getInstance().process(context);
        // System.out.println("Toll shipment list: " +
        // context.get(GenerateETFileConstants.TOLL_SHIPMENT_GENERATE_LIST));
    }
}
