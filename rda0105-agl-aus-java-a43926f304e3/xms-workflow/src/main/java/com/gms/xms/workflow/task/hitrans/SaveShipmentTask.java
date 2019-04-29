package com.gms.xms.workflow.task.hitrans;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentDetailDao;
import com.gms.xms.persistence.dao.webship.history.ShipmentDao;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.ShipmentDetailVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.webship.ship.QuoteVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Posted from SaveShipmentTask
 * <p>
 * Author TANDT
 */
public class SaveShipmentTask implements Task {
    private static final Log log = LogFactory.getLog(SaveShipmentTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentDao dao = new ShipmentDao();
        ShipmentDetailDao dDao = new ShipmentDetailDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentVo shipmentVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_REQUEST_VO), ShipmentVo.class);
            QuoteVo quote = GsonUtils.fromGson(context.get(Attributes.QUOTE_VO), QuoteVo.class);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            dao.inser(addInfo, shipmentVo);
            for (AccessorialVo accessorial : quote.getAccessorial()) {
                ShipmentDetailVo shipmentDetailVo = new ShipmentDetailVo();
                shipmentDetailVo.setAccessorialId(accessorial.getAccessorialId());
                shipmentDetailVo.setShipmentId(shipmentVo.getShipmentId());
                shipmentDetailVo.setAmount(BigDecimal.valueOf(accessorial.getValue()));
                // Put signal to the context to log insert shipment detail as Shipment Detail.
                addInfo.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Shipment Detail");
                dDao.insert(addInfo, shipmentDetailVo);
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
