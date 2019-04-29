package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.txndb.vo.ShipmentFilter;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.client.integration.request.HistoryRequest;
import com.gms.xms.workflow.client.integration.response.HistoryResponse;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from ShowWebshipHistoryTask
 * <p>
 * Author TanDT Date Apr 21, 2015
 */
public class ShowWebshipHistoryTask implements Task {
    private static final Log log = LogFactory.getLog(ShowWebshipHistoryTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentDao shipmentDao = new ShipmentDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        HistoryRequest request = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESQUEST), HistoryRequest.class);
        HistoryResponse response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        try {
            if (request.getShipmentFilter() != null) {
                Integer recordSize = 25;
                if (request.getShipmentFilter().getRecordSize() != null) {
                    recordSize = request.getShipmentFilter().getRecordSize();
                } else {
                    request.getShipmentFilter().setRecordSize(recordSize);
                }

                Integer page = 1;
                if (request.getShipmentFilter().getPage() != null) {
                    page = request.getShipmentFilter().getPage();
                } else {
                    request.getShipmentFilter().setPage(page);
                }
                ShipmentFilter shipmentFilter = request.getShipmentFilter();
                List<ShipmentVo> shipmentVos;
                shipmentVos = shipmentDao.getWebshipHistory(shipmentFilter);
                Integer totalRecordShipment = shipmentDao.getWebshipHistoryCount(shipmentFilter);
                response.setShipmentVos(shipmentVos);
                response.setCountShipment(totalRecordShipment);
            }
            context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
