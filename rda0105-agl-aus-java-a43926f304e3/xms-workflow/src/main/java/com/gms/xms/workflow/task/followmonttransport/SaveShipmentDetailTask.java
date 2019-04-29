package com.gms.xms.workflow.task.followmonttransport;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.webship.history.ShipmentDao;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from SaveShipmentTask
 * <p>
 * Author TANDT
 */
public class SaveShipmentDetailTask implements Task {
    private static final Log log = LogFactory.getLog(SaveShipmentDetailTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentDao dao = new ShipmentDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            ShipmentVo shipmentVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_REQUEST_VO), ShipmentVo.class);
            dao.inser(addInfo, shipmentVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
