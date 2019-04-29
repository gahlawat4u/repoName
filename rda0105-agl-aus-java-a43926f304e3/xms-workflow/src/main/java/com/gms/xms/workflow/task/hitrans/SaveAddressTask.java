package com.gms.xms.workflow.task.hitrans;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from SaveAddressTask
 * <p>
 * Author TANDT
 */
public class SaveAddressTask implements Task {
    private static final Log log = LogFactory.getLog(SaveAddressTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        AddressDao dao = new AddressDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            AddressVo sAddress = shipmentInfoVo.getSenderAddress();
            AddressVo rAddress = shipmentInfoVo.getReceiverAddress();
            // Put signal to the context to log insert address as sender address.
            addInfo.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Sender Address");
            dao.insert(addInfo, sAddress);
            // Put signal to the context to log insert address as receiver address.
            addInfo.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Receiver Address");
            dao.insert(addInfo, rAddress);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
