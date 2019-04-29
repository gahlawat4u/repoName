package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.client.integration.request.SearchHistoryRequest;
import com.gms.xms.workflow.client.integration.response.SearchHistoryResponse;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from SearchHistoryTask
 * <p>
 * Author TanDT Date Apr 17, 2015
 */
public class SearchHistoryTask implements Task {
    private static final Log log = LogFactory.getLog(SearchHistoryTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentDao shipmentDao = new ShipmentDao();
        SearchHistoryRequest searchHistoryRequest = new SearchHistoryRequest();
        SearchHistoryResponse historyResponse = new SearchHistoryResponse();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            searchHistoryRequest = GsonUtils.fromGson(Attributes.RESHIP_HISTORY_RESQUEST, SearchHistoryRequest.class);
            List<ShipmentVo> shipmentVos = shipmentDao.getWebshipHistory(searchHistoryRequest.getShipmentFilter());
            historyResponse.setShipmentVos(shipmentVos);
            searchHistoryRequest.getShipmentFilter().setTotalRecord(Integer.parseInt(Attributes.WEBSHIP_HISTORY_LIST_RESULT_COUNT));
            historyResponse.setShipmentFilter(searchHistoryRequest.getShipmentFilter());
            context.put(Attributes.WEBSHIP_HISTORY_LIST_RESULT, GsonUtils.toGson(historyResponse));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
