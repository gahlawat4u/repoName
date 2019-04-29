package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GetShipmentTypeByShipmentTypeIdTask implements Task {
    private static final Log log = LogFactory.getLog(GetShipmentTypeByShipmentTypeIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        int shipmentTypeId = Integer.parseInt(context.getString(Attributes.SHIPMENT_TYPE_ID));

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);
            context.put(Attributes.SHIPMENT_TYPE_RESULT, GsonUtils.toGson(shipmentTypeVo));

        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }

        return true;
    }

}
