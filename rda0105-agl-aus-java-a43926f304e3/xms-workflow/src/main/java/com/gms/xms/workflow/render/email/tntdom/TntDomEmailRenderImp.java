package com.gms.xms.workflow.render.email.tntdom;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.email.tntdom.TntDomBookingEmailModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.email.tntdom.TntDomBookingEmailVo;

import java.util.HashMap;
import java.util.Map;

public class TntDomEmailRenderImp implements ITntDomEmailRender {
    @Override
    public void genrateBookingEmailMessage(TntDomBookingEmailVo bookingEmailVo, String outputFilePath) throws Exception {
        TntDomBookingEmailModel bookingEmailModel = ModelUtils.createModelFromVo(bookingEmailVo, TntDomBookingEmailModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", bookingEmailModel);
        AppUtils.template2File(outputFilePath, false, "templates/email/tnt_dom_booking/tnt_dom_booking.ftl", data);
    }
}
