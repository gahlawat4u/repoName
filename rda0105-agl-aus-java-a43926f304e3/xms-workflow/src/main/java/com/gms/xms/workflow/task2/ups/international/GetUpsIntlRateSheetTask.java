package com.gms.xms.workflow.task2.ups.international;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.ups.UpsIntlRateSheetVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetUpsIntlRateSheetTask
 * <p>
 * Author Thinhdd Date Mar 29, 2017
 */
public class GetUpsIntlRateSheetTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetUpsIntlRateSheetTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        UpsIntlRateSheetVo upsIntlRateSheetVo = new UpsIntlRateSheetVo();
        ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
        ShipmentTypeVo shipmentTypeVo = context.get(Attributes.SHIPMENT_TYPE_RESULT);
        Integer bound = shipmentInfoVo.getBound();

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            upsIntlRateSheetVo.setCarrierDocumentRate(0L);
            upsIntlRateSheetVo.setCarrierPackageRate(0L);
            upsIntlRateSheetVo.setCarrierPackagePerWeightRate(0L);
            upsIntlRateSheetVo.setCarrierPakRate(0L);
            upsIntlRateSheetVo.setCarrierPakPerWeightRate(0L);

            upsIntlRateSheetVo.setNonCarrierDocumentRate(0L);
            upsIntlRateSheetVo.setNonCarrierPackageRate(0L);
            upsIntlRateSheetVo.setNonCarrierPackagePerWeightRate(0L);
            upsIntlRateSheetVo.setNonCarrierPakRate(0L);
            upsIntlRateSheetVo.setNonCarrierPakPerWeightRate(0L);
            if (bound == 0) { // Outbound rate sheets
                upsIntlRateSheetVo.setCarrierDocumentRate(shipmentTypeVo.getCarrierDocumentRate());
                upsIntlRateSheetVo.setCarrierPackageRate(shipmentTypeVo.getCarrierPackageRate());
                upsIntlRateSheetVo.setCarrierPackagePerWeightRate(shipmentTypeVo.getCarrierPackagePerWeightRate());
                upsIntlRateSheetVo.setCarrierPakRate(shipmentTypeVo.getCarrierPakRate());
                upsIntlRateSheetVo.setCarrierPakPerWeightRate(shipmentTypeVo.getCarrierPakPerWeightRate());

                upsIntlRateSheetVo.setNonCarrierDocumentRate(shipmentTypeVo.getNonCarrierDocumentRate());
                upsIntlRateSheetVo.setNonCarrierPackageRate(shipmentTypeVo.getNonCarrierPackageRate());
                upsIntlRateSheetVo.setNonCarrierPackagePerWeightRate(shipmentTypeVo.getNonCarrierPackagePerWeightRate());
                upsIntlRateSheetVo.setNonCarrierPakRate(shipmentTypeVo.getNonCarrierPakRate());
                upsIntlRateSheetVo.setNonCarrierPakPerWeightRate(shipmentTypeVo.getNonCarrierPakPerWeightRate());
            } else { // Inbound rate sheets
                upsIntlRateSheetVo.setCarrierDocumentRate(shipmentTypeVo.getCarrierDocumentInboundRate());
                upsIntlRateSheetVo.setCarrierPackageRate(shipmentTypeVo.getCarrierPackageInboundRate());
                upsIntlRateSheetVo.setCarrierPackagePerWeightRate(shipmentTypeVo.getCarrierPackageInboundPerWeightRate());
                upsIntlRateSheetVo.setCarrierPakRate(shipmentTypeVo.getCarrierPakInboundRate());
                upsIntlRateSheetVo.setCarrierPakPerWeightRate(shipmentTypeVo.getCarrierPakInboundPerWeightRate());

                upsIntlRateSheetVo.setNonCarrierDocumentRate(shipmentTypeVo.getNonCarrierDocumentInboundRate());
                upsIntlRateSheetVo.setNonCarrierPackageRate(shipmentTypeVo.getNonCarrierPackageInboundRate());
                upsIntlRateSheetVo.setNonCarrierPackagePerWeightRate(shipmentTypeVo.getNonCarrierPackageInboundPerWeightRate());
                upsIntlRateSheetVo.setNonCarrierPakRate(shipmentTypeVo.getNonCarrierPakInboundRate());
                upsIntlRateSheetVo.setNonCarrierPakPerWeightRate(shipmentTypeVo.getNonCarrierPakInboundPerWeightRate());
            }
            context.put(Attributes.UPS_RATE_SHEET, upsIntlRateSheetVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
