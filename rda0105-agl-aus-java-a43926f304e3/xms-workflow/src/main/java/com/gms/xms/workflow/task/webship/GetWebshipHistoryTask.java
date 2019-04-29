package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.txndb.vo.ShipmentFilter;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class GetWebshipHistoryTask implements Task {
    private static final Log log = LogFactory.getLog(GetWebshipHistoryTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentDao shipmentDao = new ShipmentDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        ShipmentFilter fillter = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_FILLTER), ShipmentFilter.class);
        try {

            List<ShipmentVo> listWebshipHistory = shipmentDao.getWebshipHistory(fillter);
            context.put(Attributes.WEBSHIP_HISTORY_LIST_RESULT, GsonUtils.toGson(listWebshipHistory, new TypeToken<List<ShipmentVo>>() {
            }.getType()));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
