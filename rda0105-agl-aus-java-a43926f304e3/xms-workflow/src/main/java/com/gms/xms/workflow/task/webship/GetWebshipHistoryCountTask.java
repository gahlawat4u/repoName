package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.txndb.vo.ShipmentFilter;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetWebshipHistoryCountTask
 * <p>
 * Author TanDT Date Apr 2, 2015
 */
public class GetWebshipHistoryCountTask implements Task {
    private static final Log log = LogFactory.getLog(GetWebshipHistoryCountTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentDao shipmentDao = new ShipmentDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        ShipmentFilter fillter = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_FILLTER), ShipmentFilter.class);
        try {

            long countShipment = shipmentDao.getWebshipHistoryCount(fillter);
            context.put(Attributes.WEBSHIP_HISTORY_LIST_RESULT_COUNT, String.valueOf(countShipment));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
