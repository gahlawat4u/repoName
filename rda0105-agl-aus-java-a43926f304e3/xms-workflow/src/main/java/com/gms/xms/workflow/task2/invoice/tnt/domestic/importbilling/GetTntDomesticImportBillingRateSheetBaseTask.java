package com.gms.xms.workflow.task2.invoice.tnt.domestic.importbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.TntDomesticRateSheetVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jul 22, 2016 10:36:11 AM
 * <p>
 * Author huynd
 */
public class GetTntDomesticImportBillingRateSheetBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTntDomesticImportBillingRateSheetBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            SaveImportBillingVo saveImportBillingVo = context.get(Attributes.IMPORT_BILLING_VO);
            ShipmentBillingVo shipmentBilling = saveImportBillingVo.getBillingBaseCharge();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            TntDomesticRateSheetVo domesticRateSheetVo = new TntDomesticRateSheetVo();

            ShipmentTypeFilter shipmentTypeFilter = new ShipmentTypeFilter();
            shipmentTypeFilter.setShipmentTypeName(shipmentBilling.getDescription());
            shipmentTypeFilter.setServiceId(Integer.valueOf(shipmentBilling.getCarrier().toString()));
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectByShipmentTypeNameAndCarrier(shipmentTypeFilter);
            domesticRateSheetVo.setCarrierDocumentRate(shipmentTypeVo.getCarrierDocumentRate());

            context.put(Attributes.SHIPMENT_TYPE_RESULT, shipmentTypeVo);
            context.put(Attributes.TNT_RATE_SHEET, domesticRateSheetVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
