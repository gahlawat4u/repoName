package com.gms.xms.workflow.render.email.othercarrier;

import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.email.othercarrier.OtherCarrierBookingEmailModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.email.othercarrier.OtherCarrierBookingEmailVo;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from OtherCarrierEmailRenderImp
 * <p>
 * Author @author HungNT Feb 19, 2016
 */
public class OtherCarrierEmailRenderImp implements IOtherCarrierEmailRender {
    @Override
    public void genrateBookingEmailMessage(OtherCarrierBookingEmailVo otherCarrierBookingEmailVo, String outputFilePath) throws Exception {
        OtherCarrierBookingEmailModel bookingEmailModel = ModelUtils.createModelFromVo(otherCarrierBookingEmailVo, OtherCarrierBookingEmailModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", bookingEmailModel);
        AppUtils.template2File(outputFilePath, false, "templates/email/other_carrier/email_other_carrier_booking.ftl", data);
    }
}
