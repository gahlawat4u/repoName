package com.gms.xms.workflow.render.email.tntdom;

import com.gms.xms.txndb.vo.email.tntdom.TntDomBookingEmailVo;

public interface ITntDomEmailRender {
    public void genrateBookingEmailMessage(TntDomBookingEmailVo bookingEmailVo, String outputFilePath) throws Exception;
}
