package com.gms.xms.workflow.task.dhl.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetDhlDomesticRateSheet
 * <p>
 * Author HungNT Date Apr 22, 2015
 */
public class GetDhlDomesticRateSheet implements Task {
    private static final Log log = LogFactory.getLog(GetDhlDomesticBaseChargeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
        Integer shipmentTypeId = shipmentInfoVo.getShipmentTypeId();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);
            Long dhlDomesticRateSheetId = shipmentTypeVo.getCarrierPackageRate();
            Long dhlDomesticPerWeightRateSheetId = shipmentTypeVo.getCarrierPackagePerWeightRate();
            context.put(Attributes.DHL_DOMESTIC_RATE_SHEET_ID, String.valueOf(dhlDomesticRateSheetId));
            context.put(Attributes.DHL_DOMESTIC_PER_WEIGHT_RATE_SHEET_ID, String.valueOf(dhlDomesticPerWeightRateSheetId));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
