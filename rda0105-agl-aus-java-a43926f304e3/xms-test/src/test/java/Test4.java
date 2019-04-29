import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.txndb.vo.admin.ratesheets.ViewRateSheetBaseRateVo;
import com.gms.xms.txndb.vo.admin.ratesheets.ViewRateSheetRequestVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test4 {
    public static void main(String[] args) {
        ViewRateSheetRequestVo requestVo = new ViewRateSheetRequestVo();
        requestVo.setCustomerCode("10200002");
        requestVo.setShipmentTypeId(122);
        requestVo.setContent(1);
        requestVo.setBound(0);
        requestVo.setMinimumBaseCharge(20.5);
        List<ViewRateSheetBaseRateVo> baseRates = new ArrayList<>();
        // Add base rate.
        ViewRateSheetBaseRateVo baseRate = new ViewRateSheetBaseRateVo();
        baseRate.setWeight(0.0);
        baseRate.setRateType(3);
        baseRate.setRate(0.0);
        Map<String, Double> zoneRates = new HashMap<>();
        zoneRates.put("1", 50.0);
        zoneRates.put("1.1", 0.0);
        zoneRates.put("2", 50.0);
        zoneRates.put("3", 50.0);
        zoneRates.put("4", 50.0);
        zoneRates.put("5", 50.0);
        zoneRates.put("6", 50.0);
        zoneRates.put("7", 50.0);
        zoneRates.put("8", 50.0);
        zoneRates.put("9", 50.0);
        baseRate.setZoneRates(zoneRates);
        baseRates.add(baseRate);
        // Add base rate.
        baseRate = new ViewRateSheetBaseRateVo();
        baseRate.setWeight(20.0);
        baseRate.setRateType(2);
        baseRate.setRate(10.0);
        zoneRates = new HashMap<>();
        zoneRates.put("1", 10.0);
        zoneRates.put("1.1", 0.0);
        zoneRates.put("2", 30.0);
        zoneRates.put("3", 50.0);
        zoneRates.put("4", 70.0);
        zoneRates.put("5", 50.0);
        zoneRates.put("6", 50.0);
        zoneRates.put("7", 90.0);
        zoneRates.put("8", 50.0);
        zoneRates.put("9", 80.0);
        baseRate.setZoneRates(zoneRates);
        baseRates.add(baseRate);
        requestVo.setBaseRates(baseRates);
        // Convert to json.
        System.out.println(GsonUtils.toGson(requestVo));
        // Json String
        String jsonResult = "{\"customerCode\":\"10200002\",\"shipmentTypeId\":122,\"content\":1,\"bound\":0,\"minimumBaseCharge\":20.5,\"baseRates\":[{\"weight\":0.0,\"rateType\":3,\"rate\":0.0,\"zoneRates\":{\"3\":50.0,\"2\":50.0,\"1\":50.0,\"7\":50.0,\"6\":50.0,\"5\":50.0,\"4\":50.0,\"9\":50.0,\"8\":50.0,\"1.1\":0.0}},{\"weight\":20.0,\"rateType\":2,\"rate\":10.0,\"zoneRates\":{\"3\":50.0,\"2\":30.0,\"1\":10.0,\"7\":90.0,\"6\":50.0,\"5\":50.0,\"4\":70.0,\"9\":80.0,\"8\":50.0,\"1.1\":0.0}}]}";
        requestVo = null;
        requestVo = GsonUtils.fromGson(jsonResult, ViewRateSheetRequestVo.class);
        System.out.println(requestVo);
    }
}
