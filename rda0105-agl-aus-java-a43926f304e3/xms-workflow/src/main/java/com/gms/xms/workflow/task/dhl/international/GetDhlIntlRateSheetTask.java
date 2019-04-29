package com.gms.xms.workflow.task.dhl.international;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.dhl.DhlRateSheetsVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetDhlIntlRateSheetTask
 * <p>
 * Author HungNT Date May 18, 2015
 */
public class GetDhlIntlRateSheetTask implements Task {
    private static final Log log = LogFactory.getLog(GetDhlIntlRateSheetTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        DhlRateSheetsVo dhlRateSheetsVo = new DhlRateSheetsVo();
        ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
        Integer shipmentTypeId = shipmentInfoVo.getShipmentTypeId();
        Integer bound = shipmentInfoVo.getBound();

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);
            if (bound == 0 || bound == -1 || bound == 15) { // Outbound rate sheets
                dhlRateSheetsVo.setCarrierDocumentRate(shipmentTypeVo.getCarrierDocumentRate());
                dhlRateSheetsVo.setCarrierPackageRate(shipmentTypeVo.getCarrierPackageRate());
                dhlRateSheetsVo.setCarrierPackagePerWeightRate(shipmentTypeVo.getCarrierPackagePerWeightRate());
                dhlRateSheetsVo.setCarrierPakRate(shipmentTypeVo.getCarrierPakRate());
                dhlRateSheetsVo.setCarrierPakPerWeightRate(shipmentTypeVo.getCarrierPakPerWeightRate());

                dhlRateSheetsVo.setNonCarrierDocumentRate(shipmentTypeVo.getNonCarrierDocumentRate());
                dhlRateSheetsVo.setNonCarrierPackageRate(shipmentTypeVo.getNonCarrierPackageRate());
                dhlRateSheetsVo.setNonCarrierPackagePerWeightRate(shipmentTypeVo.getNonCarrierPackagePerWeightRate());
                dhlRateSheetsVo.setNonCarrierPakRate(shipmentTypeVo.getNonCarrierPakRate());
                dhlRateSheetsVo.setNonCarrierPakPerWeightRate(shipmentTypeVo.getNonCarrierPakPerWeightRate());
            } else { // Inbound rate sheets
                dhlRateSheetsVo.setCarrierDocumentRate(shipmentTypeVo.getCarrierDocumentInboundRate());
                dhlRateSheetsVo.setCarrierPackageRate(shipmentTypeVo.getCarrierPackageInboundRate());
                dhlRateSheetsVo.setCarrierPackagePerWeightRate(shipmentTypeVo.getCarrierPackageInboundPerWeightRate());
                dhlRateSheetsVo.setCarrierPakRate(shipmentTypeVo.getCarrierPakInboundRate());
                dhlRateSheetsVo.setCarrierPakPerWeightRate(shipmentTypeVo.getCarrierPakInboundPerWeightRate());

                dhlRateSheetsVo.setNonCarrierDocumentRate(shipmentTypeVo.getNonCarrierDocumentInboundRate());
                dhlRateSheetsVo.setNonCarrierPackageRate(shipmentTypeVo.getNonCarrierPackageInboundRate());
                dhlRateSheetsVo.setNonCarrierPackagePerWeightRate(shipmentTypeVo.getNonCarrierPackageInboundPerWeightRate());
                dhlRateSheetsVo.setNonCarrierPakRate(shipmentTypeVo.getNonCarrierPakInboundRate());
                dhlRateSheetsVo.setNonCarrierPakPerWeightRate(shipmentTypeVo.getNonCarrierPakInboundPerWeightRate());

                if (dhlRateSheetsVo.getNonCarrierDocumentRate() == 0) {
                    dhlRateSheetsVo.setNonCarrierDocumentRate(dhlRateSheetsVo.getCarrierDocumentRate());
                }
                if (dhlRateSheetsVo.getNonCarrierPackageRate() == 0) {
                    dhlRateSheetsVo.setNonCarrierPackageRate(dhlRateSheetsVo.getCarrierPackageRate());
                }
                if (dhlRateSheetsVo.getNonCarrierPackagePerWeightRate() == 0) {
                    dhlRateSheetsVo.setNonCarrierPackagePerWeightRate(dhlRateSheetsVo.getCarrierPackagePerWeightRate());
                }
            }
            context.put(Attributes.DHL_RATE_SHEET, GsonUtils.toGson(dhlRateSheetsVo));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
