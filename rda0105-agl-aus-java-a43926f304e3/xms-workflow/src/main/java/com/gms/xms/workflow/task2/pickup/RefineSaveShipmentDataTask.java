package com.gms.xms.workflow.task2.pickup;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Oct 10, 2016 5:19:53 PM
 * <p>
 * Author dattrinh
 */
public class RefineSaveShipmentDataTask implements Task2 {
    private static final Log log = LogFactory.getLog(RefineSaveShipmentDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentRequestVo shipmentRequestPage1 = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE1);
            if (shipmentRequestPage1.getShipmentInfo().getIsSaveSenderAddressBook() == null) {
                shipmentRequestPage1.getShipmentInfo().setIsSaveSenderAddressBook(0);
            }
            if (shipmentRequestPage1.getShipmentInfo().getIsSaveRecipientAddressBook() == null) {
                shipmentRequestPage1.getShipmentInfo().setIsSaveRecipientAddressBook(0);
            }
            BookingDataVo bookingDataVo = context.get(Attributes.BOOKING_DATA_VO);
            bookingDataVo.setShipmentInfoVo(shipmentRequestPage1.getShipmentInfo());
            context.put(Attributes.BOOKING_DATA_VO, bookingDataVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
