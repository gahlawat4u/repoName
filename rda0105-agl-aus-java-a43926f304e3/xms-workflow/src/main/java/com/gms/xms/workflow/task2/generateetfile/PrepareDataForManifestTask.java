package com.gms.xms.workflow.task2.generateetfile;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.toll.TollShipmentVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from Sep 16, 2016 2:46:30 PM
 * <p>
 * Author huynd
 */
public class PrepareDataForManifestTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataForManifestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            List<TollShipmentVo> tollShipmentVos = context.get(GenerateETFileConstants.TOLL_SHIPMENT_GENERATE_LIST);
            ShipmentDao shipmentDao = new ShipmentDao();
            ShipmentVo shipmentInfo = null;
            String shipmentIdStr = "";

            for (TollShipmentVo tollShipment : tollShipmentVos) {
                shipmentInfo = shipmentDao.getShipmentInfoForETFile(tollShipment.getShipmentId());
                if (shipmentInfo != null) {
                    shipmentIdStr += shipmentInfo.getShipmentId().toString() + ",";
                }
            }
            context.put(GenerateETFileConstants.SHIPMENT_ID_STRING, shipmentIdStr.substring(0, shipmentIdStr.length() - 1));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
