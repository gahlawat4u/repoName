package test.com.gms.xms.workflow.calculate.basecharge;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.txndb.vo.ShipmentBillingFilter;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from May 19, 2016 3:35:57 PM
 * <p>
 * Author huynd
 */
public class DhlDomesticBaseChargeTest {
    public static void main(String[] args) throws Exception {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase2 context = new ContextBase2(addInfoMap);
        ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
        shipmentBillingFilter.setAirbillNumber("1873791614");
        shipmentBillingFilter.setShipmentId(534445L);
        ShipmentBillingDao billingDao = new ShipmentBillingDao();
        ShipmentBillingVo shipmentBilling = billingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
        context.put(Attributes.SHIPMENT_BILLING_VO, shipmentBilling);
        context.put(Attributes.CUSTOMER_CODE, 12500028L);
        context.put(Attributes.WFP_NAME, "Wfl-GetDhlDomesticBaseCharge");
        context = WorkFlowManager2.getInstance().process(context);
        shipmentBilling = context.get(Attributes.SHIPMENT_BILLING_VO);
        System.out.println("DHL Domestic Zone: " + shipmentBilling.getZone());
        System.out.println("DHL Domestic Rate Sheet ID: " + context.get(Attributes.DHL_DOMESTIC_RATE_SHEET_ID));
        System.out.println("DHL Domestic Perweight Rate Sheet ID: " + context.get(Attributes.DHL_DOMESTIC_PER_WEIGHT_RATE_SHEET_ID));
        ShipmentBillingVo billingCost = context.get(Attributes.BILLING_COST);
        System.out.println("Carrier cost: " + billingCost.getCarrierCost());
        System.out.println("Franchise cost: " + billingCost.getFranchiseCost());
        System.out.println("Customer cost: " + billingCost.getCustomerCost());
        System.out.println("Customer tax percent: " + billingCost.getCustomerTaxPercent());
        System.out.println("Customer tax amount: " + billingCost.getCustomerTaxAmount());
    }
}
