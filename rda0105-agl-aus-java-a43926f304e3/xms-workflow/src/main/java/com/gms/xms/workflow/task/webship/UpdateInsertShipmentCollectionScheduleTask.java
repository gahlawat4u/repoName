package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.ScheduleCollectionDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.workflow.client.integration.request.HistoryRequest;
import com.gms.xms.workflow.client.integration.response.HistoryResponse;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from UpdateInsertShipmentCollectionScheduleTask
 * <p>
 * Author TanDT Date Apr 23, 2015
 */
public class UpdateInsertShipmentCollectionScheduleTask implements Task {
    private static final Log log = LogFactory.getLog(UpdateInsertShipmentCollectionScheduleTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        ScheduleCollectionDao scheduleCollectionDao = new ScheduleCollectionDao();
        AddressDao addressDao = new AddressDao();
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            Integer statusUpdateAddress = 0;
            Integer statusUpdateScheduleCollection = 0;
            Integer statusInsertAddress = 0;
            Integer statusInsertScheduleCollection = 0;
            HistoryRequest request = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESQUEST), HistoryRequest.class);
            HistoryResponse response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
            if (request.getScheduleCollectionVo().getScheduleCollectionId() != null) {
                ScheduleCollectionVo scheduleCollectionVo = new ScheduleCollectionVo();
                scheduleCollectionVo = request.getScheduleCollectionVo();
                statusUpdateScheduleCollection = scheduleCollectionDao.updateScheduleCollectionById(addInfo, scheduleCollectionVo);
                response.setStatusUpdateScheduleCollection(statusUpdateScheduleCollection);
            } else {
                ScheduleCollectionVo scheduleCollectionVo = new ScheduleCollectionVo();
                scheduleCollectionVo = request.getScheduleCollectionVo();
                statusInsertScheduleCollection = scheduleCollectionDao.insertScheduleCollection(addInfo, scheduleCollectionVo);
                response.setStatusInsertScheduleCollection(statusInsertScheduleCollection);
            }
            if (request.getAddressVo() != null) {
                AddressVo addressVo = new AddressVo();
                addressVo = request.getAddressVo();
                statusUpdateAddress = addressDao.update(addInfo, addressVo);
                response.setStatusUpdateAddress(statusUpdateAddress);
            } else {
                AddressVo addressVo = new AddressVo();
                addressVo = request.getAddressVo();
                statusInsertAddress = addressDao.insert(addInfo, addressVo);
                response.setStatusInsertAddress(statusInsertAddress);
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
