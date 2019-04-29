package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from HistoryViewAwbTask
 * <p>
 * Author TanDT Date Apr 2, 2015
 */
public class HistoryViewAwbTask implements Task {
    private static final Log log = LogFactory.getLog(HistoryViewAwbTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentDao shipmentDao = new ShipmentDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        long shipmentId = Long.parseLong(context.getString(Attributes.WEBSHIP_HISTORY_SHIPMENTID));
        try {

            String awbBarcode = shipmentDao.selectAwbBarcode(shipmentId);
            context.put(Attributes.SHIPMENT_AWB_BARCODE, String.valueOf(awbBarcode));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
