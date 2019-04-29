package com.gms.xms.workflow.task2.dhl.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetDhlDomesticRateSheet
 * <p>
 * Author HungNT Date Apr 22, 2015
 */
public class GetDhlDomesticRateSheet implements Task2 {
    private static final Log log = LogFactory.getLog(GetDhlDomesticBaseChargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
        Integer shipmentTypeId = shipmentInfoVo.getShipmentTypeId();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);
            Long dhlDomesticRateSheetId = shipmentTypeVo.getCarrierPackageRate();
            Long dhlDomesticPerWeightRateSheetId = shipmentTypeVo.getCarrierPackagePerWeightRate();
            context.put(Attributes.DHL_DOMESTIC_RATE_SHEET_ID, dhlDomesticRateSheetId);
            context.put(Attributes.DHL_DOMESTIC_PER_WEIGHT_RATE_SHEET_ID, dhlDomesticPerWeightRateSheetId);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
