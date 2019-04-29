package com.gms.xms.workflow.task2.invoice.toll.priority.importbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.webship.TollPrioritySuburbDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.webship.toll.TollPrioritySuburbFilter;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from May 20, 2016 10:29:33 AM
 * <p>
 * Author huynd
 */
public class GetTollPriorityImportBillingZoneBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollPriorityImportBillingZoneBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {

            SaveImportBillingVo saveImportBillingVo = context.get(Attributes.IMPORT_BILLING_VO);
            ShipmentBillingVo shipmentBilling = saveImportBillingVo.getBillingBaseCharge();
            TollPrioritySuburbDao tollPrioritySuburbDao = new TollPrioritySuburbDao();
            TollPrioritySuburbFilter filter = new TollPrioritySuburbFilter();

            String senderCity = "";
            String senderPostalCode = "";
            AddressVo senderAddress = saveImportBillingVo.getSenderAddress();
            if (senderAddress != null) {
                senderCity = (senderAddress.getCity() == null) ? "" : senderAddress.getCity();
                senderPostalCode = (senderAddress.getPostalCode() == null) ? "" : senderAddress.getPostalCode();
            }
            // Get sender zone
            filter.setPostCode(senderPostalCode);
            filter.setSuburbName(senderCity);
            String senderZone = (shipmentBilling.getServiceAreaCodeOrigin() == null) ? "0" : shipmentBilling.getServiceAreaCodeOrigin();
            if (tollPrioritySuburbDao.selectTollPriorityZone(filter) != null) {
                senderZone = tollPrioritySuburbDao.selectTollPriorityZone(filter);
            }

            String receiverCity = "";
            String receiverPostalCode = "";
            AddressVo receiverAddress = saveImportBillingVo.getReceiverAddress();
            if (receiverAddress != null) {
                receiverCity = (receiverAddress.getCity() == null) ? "" : receiverAddress.getCity();
                receiverPostalCode = (receiverAddress.getPostalCode() == null) ? "" : receiverAddress.getPostalCode();
            }
            // Get receiver zone
            filter.setPostCode(receiverPostalCode);
            filter.setSuburbName(receiverCity);
            String receiverZone = (shipmentBilling.getServiceAreaCodeDestination() == null) ? "0" : shipmentBilling.getServiceAreaCodeDestination();
            if (tollPrioritySuburbDao.selectTollPriorityZone(filter) != null) {
                receiverZone = tollPrioritySuburbDao.selectTollPriorityZone(filter);
            }

            String zone = senderZone + "-" + receiverZone;
            context.put(Attributes.ZONE, zone);
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
