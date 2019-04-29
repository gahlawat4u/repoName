package com.gms.xms.workflow.task.toll.ipec;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.TollIpecZoneDao;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.TollIpecZoneFilter;
import com.gms.xms.txndb.vo.webship.TollIpecZoneVo;
import com.gms.xms.workflow.core.Task;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author tkvcl
 */
public class GetTollIpecZoneTask implements Task {
    private static final Log log = LogFactory.getLog(GetTollIpecZoneTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        TollIpecZoneDao dao = new TollIpecZoneDao();
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


            if (senderPostalCode.startsWith("0")) {
                senderPostalCode = senderPostalCode.substring(1, senderPostalCode.length());
            }

            if (receiverPostalCode.startsWith("0")) {
                receiverPostalCode = receiverPostalCode.substring(1, receiverPostalCode.length());
            }
            // Check Toll Ipec Zone ( TO )
            TollIpecZoneVo ipecZoneToVo = new TollIpecZoneVo();
            TollIpecZoneFilter filterTo = new TollIpecZoneFilter();
            filterTo.setCityName(senderCity);
            filterTo.setPostalCode(senderPostalCode);
            ipecZoneToVo = dao.selectTollIpecZone(filterTo);

            // Check Toll Ipec Zone ( FROM )
            TollIpecZoneVo ipecZoneFromVo = new TollIpecZoneVo();
            TollIpecZoneFilter filterFrom = new TollIpecZoneFilter();
            filterFrom.setCityName(receiverCity);
            filterFrom.setPostalCode(receiverPostalCode);
            ipecZoneFromVo = dao.selectTollIpecZone(filterFrom);
            String senderZoneCode = "0";
            String receiverZoneCode = "0";
            if (ipecZoneToVo != null) {
                senderZoneCode = ipecZoneToVo.getZone();
            }

            if (ipecZoneFromVo != null) {
                receiverZoneCode = ipecZoneFromVo.getZone();
            }

            context.put(Attributes.SENDER_ZONE_CODE, senderZoneCode);
            context.put(Attributes.RECEIVER_ZONE_CODE, receiverZoneCode);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, "The selected service is not available for the given postal codes. Please kindly select other service type.");
            return false;
        }
        return true;
    }

}
