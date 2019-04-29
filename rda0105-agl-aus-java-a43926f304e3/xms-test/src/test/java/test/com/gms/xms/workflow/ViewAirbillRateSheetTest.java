package test.com.gms.xms.workflow;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.admin.customer.baserate.CustBaseRateVo;
import com.gms.xms.txndb.vo.admin.customer.baserate.ServiceTypeVo;
import com.gms.xms.txndb.vo.airbill.ShipmentBillingInfoVo;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from SCOverviewReportTest
 * <p>
 * Author DatTV Oct 30, 2015
 */
public class ViewAirbillRateSheetTest {
    public static void main(String[] args) {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase2 context = new ContextBase2(addInfoMap);
        context.put(Attributes.SHIPMENT_ID, 558123L);
        context.put(Attributes.AIRBILL_NUMBER, "INX01280");
        context.put(Attributes.WFP_NAME, "Wfl-ViewAirbillRateSheet");
        try {
            WorkFlowManager2.getInstance().process(context);
            if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
            } else {
                ShipmentBillingInfoVo billingInfoVo = context.get(Attributes.SHIPMENT_BILLING_VO);
                System.out.println("Airbill info: " + billingInfoVo);
                Double minumumBaseCharge = context.getDouble(Attributes.MINIMUM_BASE_CHARGE);
                Double serviceMarkupRate = context.getDouble(Attributes.SERVICE_MARKUP_RATE);
                System.out.println("Minimum base charge: " + minumumBaseCharge);
                System.out.println("Service markup rate: " + serviceMarkupRate);
                // Show customer base rate.
                ServiceTypeVo serviceTypeVo = context.get(Attributes.SERVICE_TYPE_VO);
                if (serviceTypeVo.getCustBaseRates() != null && serviceTypeVo.getCustBaseRates().size() > 0) {
                    for (CustBaseRateVo baseRateVo : serviceTypeVo.getCustBaseRates()) {
                        System.out.println("Customer base rate:");
                        System.out.println(baseRateVo);
                        if (baseRateVo.getZoneCheck()) {
                            System.out.println("Customer base rate detail:");
                            System.out.println(baseRateVo.getCustBaseRateDetails());
                        }
                    }
                } else {
                    System.out.println("No customer base rate.");
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
