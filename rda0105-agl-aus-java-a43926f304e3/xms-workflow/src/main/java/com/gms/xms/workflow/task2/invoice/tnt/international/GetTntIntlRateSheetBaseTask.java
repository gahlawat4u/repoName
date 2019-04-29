package com.gms.xms.workflow.task2.invoice.tnt.international;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.TntIntlRateSheetVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from May 19, 2016 4:23:43 PM
 * <p>
 * Author huynd
 */
public class GetTntIntlRateSheetBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTntIntlRateSheetBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            TntIntlRateSheetVo tntIntlRateSheetsVo = new TntIntlRateSheetVo();
            Integer bound = context.getInt(Attributes.BOUND);
            ShipmentTypeVo shipmentTypeVo = context.get(Attributes.SHIPMENT_TYPE_RESULT);
            tntIntlRateSheetsVo.setCarrierDocumentRate(0L);
            tntIntlRateSheetsVo.setCarrierPackageRate(0L);
            tntIntlRateSheetsVo.setCarrierPackagePerWeightRate(0L);
            tntIntlRateSheetsVo.setCarrierPakRate(0L);
            tntIntlRateSheetsVo.setCarrierPakPerWeightRate(0L);

            tntIntlRateSheetsVo.setNonCarrierDocumentRate(0L);
            tntIntlRateSheetsVo.setNonCarrierPackageRate(0L);
            tntIntlRateSheetsVo.setNonCarrierPackagePerWeightRate(0L);
            tntIntlRateSheetsVo.setNonCarrierPakRate(0L);
            tntIntlRateSheetsVo.setNonCarrierPakPerWeightRate(0L);
            if (bound == 0) { // Outbound rate sheets
                tntIntlRateSheetsVo.setCarrierDocumentRate(shipmentTypeVo.getCarrierDocumentRate());
                tntIntlRateSheetsVo.setCarrierPackageRate(shipmentTypeVo.getCarrierPackageRate());
                tntIntlRateSheetsVo.setCarrierPackagePerWeightRate(shipmentTypeVo.getCarrierPackagePerWeightRate());
                tntIntlRateSheetsVo.setCarrierPakRate(shipmentTypeVo.getCarrierPakRate());
                tntIntlRateSheetsVo.setCarrierPakPerWeightRate(shipmentTypeVo.getCarrierPakPerWeightRate());

                tntIntlRateSheetsVo.setNonCarrierDocumentRate(shipmentTypeVo.getNonCarrierDocumentRate());
                tntIntlRateSheetsVo.setNonCarrierPackageRate(shipmentTypeVo.getNonCarrierPackageRate());
                tntIntlRateSheetsVo.setNonCarrierPackagePerWeightRate(shipmentTypeVo.getNonCarrierPackagePerWeightRate());
                tntIntlRateSheetsVo.setNonCarrierPakRate(shipmentTypeVo.getNonCarrierPakRate());
                tntIntlRateSheetsVo.setNonCarrierPakPerWeightRate(shipmentTypeVo.getNonCarrierPakPerWeightRate());
            } else { // Inbound rate sheets
                tntIntlRateSheetsVo.setCarrierDocumentRate(shipmentTypeVo.getCarrierDocumentInboundRate());
                tntIntlRateSheetsVo.setCarrierPackageRate(shipmentTypeVo.getCarrierPackageInboundRate());
                tntIntlRateSheetsVo.setCarrierPackagePerWeightRate(shipmentTypeVo.getCarrierPackageInboundPerWeightRate());
                tntIntlRateSheetsVo.setCarrierPakRate(shipmentTypeVo.getCarrierPakInboundRate());
                tntIntlRateSheetsVo.setCarrierPakPerWeightRate(shipmentTypeVo.getCarrierPakInboundPerWeightRate());

                tntIntlRateSheetsVo.setNonCarrierDocumentRate(shipmentTypeVo.getNonCarrierDocumentInboundRate());
                tntIntlRateSheetsVo.setNonCarrierPackageRate(shipmentTypeVo.getNonCarrierPackageInboundRate());
                tntIntlRateSheetsVo.setNonCarrierPackagePerWeightRate(shipmentTypeVo.getNonCarrierPackageInboundPerWeightRate());
                tntIntlRateSheetsVo.setNonCarrierPakRate(shipmentTypeVo.getNonCarrierPakInboundRate());
                tntIntlRateSheetsVo.setNonCarrierPakPerWeightRate(shipmentTypeVo.getNonCarrierPakInboundPerWeightRate());
            }
            context.put(Attributes.SHIPMENT_TYPE_RESULT, shipmentTypeVo);
            context.put(Attributes.TNT_RATE_SHEET, tntIntlRateSheetsVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
