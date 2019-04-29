package com.gms.xms.workflow.task.couriersplease;

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
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            AddressVo sAddress = shipmentInfoVo.getSenderAddress();
            AddressVo rAddress = shipmentInfoVo.getReceiverAddress();
            dao.insert(addInfo, sAddress);
            dao.insert(addInfo, rAddress);
            shipmentInfoVo.setReceiverAddressId(rAddress.getAddressId());
            shipmentInfoVo.setSenderAddressId(sAddress.getAddressId());
            context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
