package com.gms.xms.workflow.render.email.ups;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.email.tntdom.TntDomBookingEmailModel;
import com.gms.xms.model.email.ups.UpsIntlBookingEmailModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.email.tntdom.TntDomBookingEmailVo;
import com.gms.xms.txndb.vo.email.ups.UpsIntlBookingEmailVo;
import com.gms.xms.workflow.render.email.tntdom.ITntDomEmailRender;

import java.util.HashMap;
import java.util.Map;

public class UpsEmailRenderImp implements IUpsEmailRender {
    @Override
    public void genrateBookingEmailMessage(UpsIntlBookingEmailVo bookingEmailVo, String outputFilePath) throws Exception {
        UpsIntlBookingEmailModel bookingEmailModel = ModelUtils.createModelFromVo(bookingEmailVo, UpsIntlBookingEmailModel.class);
        if(bookingEmailModel.getScheduleInfo()==null)
        {
            bookingEmailModel.setScheduleInfo(null);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", bookingEmailModel);
        AppUtils.template2File(outputFilePath, false, "templates/email/ups/ups_intl_booking.ftl", data);
    }
}
