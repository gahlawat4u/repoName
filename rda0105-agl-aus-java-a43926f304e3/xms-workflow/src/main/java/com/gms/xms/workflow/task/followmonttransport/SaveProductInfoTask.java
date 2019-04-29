package com.gms.xms.workflow.task.followmonttransport;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentProductDetailDao;
import com.gms.xms.txndb.vo.ShipmentProductDetailVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from SaveProductInfoTask
 * <p>
 * Author TANDT
 */
public class SaveProductInfoTask implements Task {
    private static final Log log = LogFactory.getLog(SaveProductInfoTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentProductDetailDao dao = new ShipmentProductDetailDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            ShipmentProductDetailVo shipmentVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_REQUEST_VO), ShipmentProductDetailVo.class);
            dao.insert(addInfo, shipmentVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
