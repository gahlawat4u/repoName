package com.gms.xms.workflow.task2.invoice.toll.ipec;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.TollIpecZoneDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.webship.TollIpecZoneFilter;
import com.gms.xms.txndb.vo.webship.TollIpecZoneVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from May 20, 2016 9:19:23 AM
 * <p>
 * Author huynd
 */
public class GetTollIpecZoneBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollIpecZoneBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentBillingVo shipmentBilling = context.get(Attributes.SHIPMENT_BILLING_VO);
            TollIpecZoneDao tollIpecZoneDao = new TollIpecZoneDao();

            AddressDao addressDao = new AddressDao();
            String senderCity = "";
            String senderPostalCode = "";
            Integer senderAddressId = shipmentBilling.getSenderAddressId();
            AddressVo senderAddress = addressDao.selectById(senderAddressId);
            if (senderAddress != null) {
                senderCity = (senderAddress.getCity() == null) ? "" : senderAddress.getCity();
                senderPostalCode = (senderAddress.getPostalCode() == null) ? "" : senderAddress.getPostalCode();
            }

            if (senderPostalCode.startsWith("0")) {
                senderPostalCode = senderPostalCode.substring(1, senderPostalCode.length());
            }

            Integer receiverAddressId = shipmentBilling.getReceiverAddressId();
            String receiverCity = "";
            String receiverPostalCode = "";
            AddressVo receiverAddress = addressDao.selectById(receiverAddressId);
            if (receiverAddress != null) {
                receiverCity = (receiverAddress.getCity() == null) ? "" : receiverAddress.getCity();
                receiverPostalCode = (receiverAddress.getPostalCode() == null) ? "" : receiverAddress.getPostalCode();
            }

            if (receiverPostalCode.startsWith("0")) {
                receiverPostalCode = receiverPostalCode.substring(1, receiverPostalCode.length());
            }

            // Check Toll Ipec Zone ( TO )
            TollIpecZoneVo ipecZoneToVo = new TollIpecZoneVo();
            TollIpecZoneFilter filterTo = new TollIpecZoneFilter();
            filterTo.setCityName(senderCity);
            filterTo.setPostalCode(senderPostalCode);
            ipecZoneToVo = tollIpecZoneDao.selectTollIpecZone(filterTo);
            String senderZoneCode = "0";
            if (ipecZoneToVo != null) {
                senderZoneCode = ipecZoneToVo.getZone();
            }

            // Check Toll Ipec Zone ( FROM )
            TollIpecZoneVo ipecZoneFromVo = new TollIpecZoneVo();
            TollIpecZoneFilter filterFrom = new TollIpecZoneFilter();
            filterFrom.setCityName(receiverCity);
            filterFrom.setPostalCode(receiverPostalCode);
            ipecZoneFromVo = tollIpecZoneDao.selectTollIpecZone(filterFrom);
            String receiverZoneCode = "0";
            if (ipecZoneFromVo != null) {
                receiverZoneCode = ipecZoneFromVo.getZone();
            }

            String zone = senderZoneCode + "-" + receiverZoneCode;
            context.put(Attributes.ZONE, zone);
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
