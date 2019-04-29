package com.gms.xms.workflow.task2.ups.international.booking;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.workflow.core.Task;

public class SaveAddressTask implements Task {

	private static final Log log = LogFactory.getLog(SaveAddressTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
	try {
	    context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
	    ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
	    AddressVo sAddress = shipmentInfoVo.getSenderAddress();
	    AddressVo rAddress = shipmentInfoVo.getReceiverAddress();
	    BookingDataVo bookingDataVo = GsonUtils.fromGson(context.get(Attributes.BOOKING_DATA), BookingDataVo.class);

	    bookingDataVo.setrAddress(rAddress);
	    bookingDataVo.setsAddress(sAddress);

	    context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
	    context.put(Attributes.BOOKING_DATA, GsonUtils.toGson(bookingDataVo));
	} catch (Exception e) {
	    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
	    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
	    log.error(e);
	    return false;
	}
	return true;
    }


}
