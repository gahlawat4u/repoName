package com.gms.xms.workflow.render.email.ups;

import com.gms.xms.txndb.vo.email.ups.UpsIntlBookingEmailVo;

public interface IUpsEmailRender {
    public void genrateBookingEmailMessage(UpsIntlBookingEmailVo upsIntlBookingEmailVo, String outputFilePath) throws Exception;
}
