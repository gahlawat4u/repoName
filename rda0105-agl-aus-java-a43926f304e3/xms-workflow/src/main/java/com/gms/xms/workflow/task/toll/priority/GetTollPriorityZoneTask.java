package com.gms.xms.workflow.task.toll.priority;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.webship.TollPrioritySuburbDao;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.toll.TollPrioritySuburbFilter;
import com.gms.xms.workflow.core.Task;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetTollPriorityZoneTask
 * <p>
 * Author HungNT Date Aug 25, 2015
 */
public class GetTollPriorityZoneTask implements Task {
    private static final Log log = LogFactory.getLog(GetTollPriorityZoneTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            String senderPostalCode = shipmentInfoVo.getSenderAddress().getPostalCode();
            String senderCity = shipmentInfoVo.getSenderAddress().getCity();
            String receiverPostalCode = shipmentInfoVo.getReceiverAddress().getPostalCode();
            String receiverCity = shipmentInfoVo.getReceiverAddress().getCity();
            if (!ShipmentUtils.isCarrierSuburb(senderCity, senderPostalCode)) {
                context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                context.put(Attributes.ERROR_MESSAGE, "Please enter valid city name and postal code for sender's address.");
                return false;
            }
            if (!ShipmentUtils.isCarrierSuburb(receiverCity, receiverPostalCode)) {
                context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                context.put(Attributes.ERROR_MESSAGE, "Please enter valid city name and postal code for receiver's address.");
                return false;
            }


            TollPrioritySuburbDao tollPrioritySuburbDao = new TollPrioritySuburbDao();
            TollPrioritySuburbFilter filter = new TollPrioritySuburbFilter();

            // Get sender zone
            filter.setPostCode(senderPostalCode);
            filter.setSuburbName(senderCity);
            String senderZone = "0";
            if (tollPrioritySuburbDao.selectTollPriorityZone(filter) != null) {
                senderZone = tollPrioritySuburbDao.selectTollPriorityZone(filter);
            }
            // Get receiver zone
            filter.setPostCode(receiverPostalCode);
            filter.setSuburbName(receiverCity);
            String receiverZone = "0";
            if (tollPrioritySuburbDao.selectTollPriorityZone(filter) != null) {
                receiverZone = tollPrioritySuburbDao.selectTollPriorityZone(filter);
            }

            context.put(Attributes.SENDER_ZONE_CODE, senderZone);
            context.put(Attributes.RECEIVER_ZONE_CODE, receiverZone);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, "The selected service is not available for the given postal codes. Please kindly select other service type.");
            return false;
        }
        return true;
    }

}
