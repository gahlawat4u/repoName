package com.gms.xms.workflow.task2.invoice.dhl.international.downloadbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.dhl.DhlRateSheetsVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jul 25, 2016 4:37:50 PM
 * <p>
 * Author huynd
 */
public class GetDhlIntlDownloadBillingRateSheetBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetDhlIntlDownloadBillingRateSheetBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            SaveImportBillingVo saveImportBillingVo = context.get(Attributes.IMPORT_BILLING_VO);
            ShipmentBillingVo shipmentBilling = saveImportBillingVo.getBillingBaseCharge();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            DhlRateSheetsVo dhlRateSheetsVo = new DhlRateSheetsVo();
            Integer bound = context.getInt(Attributes.BOUND);
            ShipmentTypeFilter shipmentTypeFilter = new ShipmentTypeFilter();
            shipmentTypeFilter.setShipmentTypeName(shipmentBilling.getDescription());
            shipmentTypeFilter.setServiceId(Integer.valueOf(shipmentBilling.getCarrier().toString()));
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectByShipmentTypeNameAndCarrier(shipmentTypeFilter);
            if (bound == 0 || bound == -1 || bound == 15) { // Outbound
                // ratesheet
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
            context.put(Attributes.SHIPMENT_TYPE_RESULT, shipmentTypeVo);
            context.put(Attributes.DHL_RATE_SHEET, dhlRateSheetsVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
