package com.gms.xms.workflow.task2.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ship.QuoteVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from GetDhlDomesticSurchargeTask
 * <p>
 * Author HungNT Date Apr 23, 2015
 */
public class GetQuoteResultTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetQuoteResultTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
        QuoteVo quoteVo = new QuoteVo();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Double customerCost = context.getDouble(Attributes.CUSTOMER_COST) != null ? context.getDouble(Attributes.CUSTOMER_COST) : 0d;
            Double carrierCost = context.getDouble(Attributes.CARRIER_COST) != null ? context.getDouble(Attributes.CARRIER_COST) : 0d;
            Double weight = context.getDouble(Attributes.SHIPMENT_TOTAL_WEIGHT) != null ? context.getDouble(Attributes.SHIPMENT_TOTAL_WEIGHT) : 0d;
            String weightType = context.getString(Attributes.SHIPMENT_WEIGHT_TYPE) != null ? context.getString(Attributes.SHIPMENT_WEIGHT_TYPE) : "";
            Double totalCharge = context.getDouble(Attributes.TOTAL_CHARGE) != null ? context.getDouble(Attributes.TOTAL_CHARGE) : 0d;
            Double totalCustomValue = context.getDouble(Attributes.TOTAL_CUSTOM_VALUE) != null ? context.getDouble(Attributes.TOTAL_CUSTOM_VALUE) : 0d;
            quoteVo.setBaseCharge(customerCost);
            quoteVo.setCarrierCharge(carrierCost);
            List<AccessorialVo> accessorialVos = new ArrayList<>();
            if (context.get(Attributes.SURCHARGE_LIST) != null) {
                accessorialVos = context.get(Attributes.SURCHARGE_LIST);
            }
            quoteVo.setAccessorial(accessorialVos);

            quoteVo.setWeight(weight);
            quoteVo.setWeightType(weightType);
            quoteVo.setTotalCharge(totalCharge);
            quoteVo.setTotalCustomValue(totalCustomValue);

            Double nonStandardCharge = context.getDouble(Attributes.NON_STANDARD_CHARGE) != null ? context.getDouble(Attributes.NON_STANDARD_CHARGE) : 0d;
            quoteVo.setNonStandardCharge(nonStandardCharge);

            Double insuranceValue = context.getDouble(Attributes.INSURANCE_VALUE) != null ? context.getDouble(Attributes.INSURANCE_VALUE) : 0d;
            quoteVo.setInsuranceValue(insuranceValue);

            Double manualHandlingSurcharge = context.getDouble(Attributes.MANUAL_HANDLING_SURCHARGE) != null ? context.getDouble(Attributes.MANUAL_HANDLING_SURCHARGE) : 0d;
            quoteVo.setManualHandlingSurcharge(manualHandlingSurcharge);

            context.put(Attributes.SHIPMENT_INFO_VO, shipmentInfoVo);
            context.put(Attributes.QUOTE_VO, quoteVo);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
