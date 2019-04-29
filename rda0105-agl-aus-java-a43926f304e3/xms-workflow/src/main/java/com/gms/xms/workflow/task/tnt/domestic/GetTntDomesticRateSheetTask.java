package com.gms.xms.workflow.task.tnt.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.TntDomesticRateSheetVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetDhlIntlRateSheetTask
 * <p>
 * Author HungNT Date May 18, 2015
 */
public class GetTntDomesticRateSheetTask implements Task {
    private static final Log log = LogFactory.getLog(GetTntDomesticRateSheetTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        TntDomesticRateSheetVo domesticRateSheetVo = new TntDomesticRateSheetVo();
        ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
        Integer shipmentTypeId = shipmentInfoVo.getShipmentTypeId();

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);
            domesticRateSheetVo.setCarrierDocumentRate(shipmentTypeVo.getCarrierDocumentRate());
            context.put(Attributes.TNT_RATE_SHEET, GsonUtils.toGson(domesticRateSheetVo));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
