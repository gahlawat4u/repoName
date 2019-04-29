package test.com.gms.xms.workflow.ratesheets;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.RateSheetColumnModel;
import com.gms.xms.model.RateSheetDetailModel;
import com.gms.xms.model.RateSheetRowModel;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from Jun 23, 2016 11:10:42 AM
 * <p>
 * Author huynd
 */
public class ImportStartrackRateSheetTest {
    public static void main(String[] args) throws Exception {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase2 context = new ContextBase2(addInfoMap);
        context.put(Attributes.SHIPMENT_TYPE_ID, 228L);
        context.put(Attributes.SHEET_NAME, "Test");
        context.put(Attributes.CARRIER_COST, 1);
        context.put(Attributes.PER_WEIGHT, 0);
        context.put(Attributes.CONTENT, 0);
        context.put(Attributes.BOUND, 0);
        context.put(Attributes.RATE_SHEET_FILE, "C:\\Users\\H P\\OneDrive\\Projects\\XMS 2.0\\Import rate\\Rate_road_express_18Dec.csv");
        context.put(Attributes.WFP_NAME, "Wfl-ImportRateSheetStartrack");
        context = WorkFlowManager2.getInstance().process(context);
        List<RateSheetColumnModel> columnDataList = context.get(Attributes.RATE_SHEET_COLUMN_DATA);
        List<RateSheetRowModel> rowDataList = context.get(Attributes.RATE_SHEET_ROW_DATA);
        List<RateSheetDetailModel> detailDataList = context.get(Attributes.RATE_SHEET_DETAIL_DATA);
        System.out.println("Column data list: \n" + columnDataList.size());
        System.out.println("Row data list: \n" + rowDataList.size());
        System.out.println("Detail data: \n" + detailDataList.size());
    }
}
