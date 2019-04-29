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
 * Posted from May 18, 2016 2:50:58 PM
 * <p>
 * Author huynd
 */
public class TntIntlBaseChargeTest {
    public static void main(String[] args) throws Exception {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase2 context = new ContextBase2(addInfoMap);
        ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
        shipmentBillingFilter.setAirbillNumber("326299665");
        shipmentBillingFilter.setShipmentId(535668L);
        ShipmentBillingDao billingDao = new ShipmentBillingDao();
        ShipmentBillingVo shipmentBilling = billingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
        context.put(Attributes.SHIPMENT_BILLING_VO, shipmentBilling);
        context.put(Attributes.CUSTOMER_CODE, 13300086L);
        context.put(Attributes.WFP_NAME, "Wfl-GetTntIntlBaseCharge");
        context = WorkFlowManager2.getInstance().process(context);
        shipmentBilling = context.get(Attributes.SHIPMENT_BILLING_VO);
        System.out.println("TNT International Zone: " + shipmentBilling.getZone());
        System.out.println("TNT International Rate Sheet: \n" + context.get(Attributes.TNT_RATE_SHEET));
        ShipmentBillingVo billingCost = context.get(Attributes.BILLING_COST);
        System.out.println("Carrier cost: " + billingCost.getCarrierCost());
        System.out.println("Franchise cost: " + billingCost.getFranchiseCost());
        System.out.println("Customer cost: " + billingCost.getCustomerCost());
        System.out.println("Customer tax percent: " + billingCost.getCustomerTaxPercent());
        System.out.println("Customer tax amount: " + billingCost.getCustomerTaxAmount());
    }
}
