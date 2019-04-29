package com.gms.xms.workflow.task2.ratesheets;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.RateSheetModel;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from May 9, 2016 5:19:36 PM
 * <p>
 * Author huynd
 */
public class PrepareRateSheetTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareRateSheetTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            RateSheetModel rateSheet = new RateSheetModel();
            rateSheet.setSheetName(context.getString(Attributes.SHEET_NAME));
            rateSheet.setShipmentTypeId(context.getLong(Attributes.SHIPMENT_TYPE_ID).toString());
            rateSheet.setCarrierCost(context.getInt(Attributes.CARRIER_COST).toString());
            rateSheet.setIsPerWeight(context.getInt(Attributes.PER_WEIGHT).toString());
            rateSheet.setContent(context.getInt(Attributes.CONTENT).toString());
            rateSheet.setBound(context.getInt(Attributes.BOUND).toString());
            context.put(Attributes.RATE_SHEET_MODEL, rateSheet);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
