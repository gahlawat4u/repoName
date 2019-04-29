package com.gms.xms.workflow.task2.startrack.booking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jun 15, 2016 10:54:28 AM
 * <p>
 * Author huynd
 */
public class ValidStartrackShipmentRequestTask implements Task2 {
    private static final Log log = LogFactory.getLog(ValidStartrackShipmentRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        ShipmentRequestVo shipmentRequestPage2 = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE2);
        Integer scheduleCollectionSelect = context.getInt(Attributes.SCHEDULE_LIST_SELECT);
        try {
            if (StringUtils.isEmpty(shipmentRequestPage2.getContentDetail().getDescription())) {
                throw new Exception("Content Description is not empty.");
            }
            if (StringUtils.isEmpty(shipmentRequestPage2.getShipmentReference())) {
                throw new Exception("Shipment Reference is not empty.");
            }

            if (scheduleCollectionSelect != null && scheduleCollectionSelect.equals("1")) {
                if (StringUtils.isBlank(String.valueOf(shipmentRequestPage2.getScheduleCollection().getPickupDate()))) {
                    throw new Exception("Pickup date cannot leave blank.");
                }
                if (StringUtils.isBlank(shipmentRequestPage2.getScheduleCollection().getPickupTime())) {
                    throw new Exception("Pickup time cannot leave blank.");
                }
                if (StringUtils.isBlank(shipmentRequestPage2.getScheduleCollection().getPickupTimeNoLater())) {
                    throw new Exception("Pickup time no later cannot leave blank.");
                }

                if (StringUtils.isBlank(String.valueOf(shipmentRequestPage2.getScheduleCollection().getLocationCodeId()))) {
                    throw new Exception("Location Code cannot leave blank.");
                }
                // Valid pickup address model.
                if (StringUtils.isBlank(shipmentRequestPage2.getScheduleCollection().getSaddress().getContactName())) {
                    throw new Exception("Contact name cannot leave blank.");
                }
                if (StringUtils.isBlank(shipmentRequestPage2.getScheduleCollection().getSaddress().getCompanyName())) {
                    throw new Exception("Company name cannot leave blank.");
                }
                if (StringUtils.isBlank(shipmentRequestPage2.getScheduleCollection().getSaddress().getPhone())) {
                    throw new Exception("Phone cannot leave blank.");
                }
                if (shipmentRequestPage2.getScheduleCollection().getSaddress().getPhone().replaceAll("[^a-zA-Z0-9]", "").length() != 10) {
                    throw new Exception("The phone number have to include 10 numbers.");
                }
                if (StringUtils.isBlank(shipmentRequestPage2.getScheduleCollection().getSaddress().getAddress())) {
                    throw new Exception("Address cannot leave blank.");
                }
                if (StringUtils.isBlank(shipmentRequestPage2.getScheduleCollection().getSaddress().getCity())) {
                    throw new Exception("City cannot leave blank.");
                }
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
