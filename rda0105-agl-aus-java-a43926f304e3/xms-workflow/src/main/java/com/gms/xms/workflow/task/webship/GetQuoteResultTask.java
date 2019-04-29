package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ship.QuoteVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetDhlDomesticSurchargeTask
 * <p>
 * Author HungNT Date Apr 23, 2015
 */
public class GetQuoteResultTask implements Task {
    private static final Log log = LogFactory.getLog(GetQuoteResultTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
        QuoteVo quoteVo = new QuoteVo();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            quoteVo.setBaseCharge(Double.parseDouble(context.get(Attributes.CUSTOMER_COST)));
            quoteVo.setCarrierCharge(Double.parseDouble(context.get(Attributes.CARRIER_COST)));
            List<AccessorialVo> accessorialVos = GsonUtils.fromGson(context.get(Attributes.SURCHARGE_LIST), new TypeToken<List<AccessorialVo>>() {
            }.getType());
            quoteVo.setAccessorial(accessorialVos);
            quoteVo.setWeight(Double.parseDouble(context.get(Attributes.SHIPMENT_TOTAL_WEIGHT)));
            quoteVo.setWeightType(context.get(Attributes.SHIPMENT_WEIGHT_TYPE));
            quoteVo.setTotalCharge(Double.parseDouble(context.get(Attributes.TOTAL_CHARGE)));
            quoteVo.setTotalCustomValue(Double.parseDouble(context.get(Attributes.TOTAL_CUSTOM_VALUE)));

            Double nonStandardCharge = 0D;
            try {
                nonStandardCharge = Double.parseDouble(context.get(Attributes.NON_STANDARD_CHARGE));
            } catch (Exception e) {
            }
            quoteVo.setNonStandardCharge(nonStandardCharge);

            Double insuranceValue = 0D;
            try {
                insuranceValue = Double.parseDouble(context.get(Attributes.INSURANCE_VALUE));
            } catch (Exception e) {
            }
            quoteVo.setInsuranceValue(insuranceValue);

            context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
            context.put(Attributes.QUOTE_VO, GsonUtils.toGson(quoteVo));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
