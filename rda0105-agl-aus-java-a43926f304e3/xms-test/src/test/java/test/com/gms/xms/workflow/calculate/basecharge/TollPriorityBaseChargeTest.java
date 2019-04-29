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
 * Posted from May 20, 2016 10:34:42 AM
 * <p>
 * Author huynd
 */
public class TollPriorityBaseChargeTest {
    public static void main(String[] args) throws Exception {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase2 context = new ContextBase2(addInfoMap);
        ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
        shipmentBillingFilter.setAirbillNumber("AQRD078276");
        shipmentBillingFilter.setShipmentId(532782L);
        ShipmentBillingDao billingDao = new ShipmentBillingDao();
        ShipmentBillingVo shipmentBilling = billingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
        context.put(Attributes.SHIPMENT_BILLING_VO, shipmentBilling);
        context.put(Attributes.CUSTOMER_CODE, 10200438L);
        context.put(Attributes.WFP_NAME, "Wfl-GetTollPriorityBaseCharge");
        context = WorkFlowManager2.getInstance().process(context);
        shipmentBilling = context.get(Attributes.SHIPMENT_BILLING_VO);
        System.out.println("Toll Ipec Sender Zone Code: " + context.get(Attributes.SENDER_ZONE_CODE));
        System.out.println("Toll Ipec Receiver Zone Code: " + context.get(Attributes.RECEIVER_ZONE_CODE));
        ShipmentBillingVo billingCost = context.get(Attributes.BILLING_COST);
        System.out.println("Carrier cost: " + billingCost.getCarrierCost());
        System.out.println("Franchise cost: " + billingCost.getFranchiseCost());
        System.out.println("Customer cost: " + billingCost.getCustomerCost());
        System.out.println("Customer tax percent: " + billingCost.getCustomerTaxPercent());
        System.out.println("Customer tax amount: " + billingCost.getCustomerTaxAmount());
    }
}
