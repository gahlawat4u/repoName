package com.gms.xms.workflow.client;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.Map;

public class WebshipScheduleClient extends WorkflowBaseClient {

    public WebshipScheduleClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    public BookingDataVo doScheduleCollection(BookingDataVo bookingDataVo) throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfo());
        context.put(Attributes.BOOKING_DATA_VO, bookingDataVo);

        context.put(Attributes.WFP_NAME, "Wfl-GetScheduleCollections");
        context = WorkFlowManager2.getInstance().process(context);
        BookingDataVo bookingDataVoResult = context.get(Attributes.BOOKING_DATA_VO);

        if (context.getString(Attributes.ERROR_CODE).equals(ErrorCode.SUCCESS)) {
            bookingDataVoResult.setErrorMessage(context.getString(Attributes.ERROR_CODE));
            return bookingDataVoResult;
        } else {
            bookingDataVoResult.setErrorMessage(context.getString(Attributes.ERROR_MESSAGE));
            return bookingDataVoResult;
        }

    }
}
