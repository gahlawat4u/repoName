package com.gms.xms.workflow.render.email.othercarrier;

import com.gms.xms.txndb.vo.email.othercarrier.OtherCarrierBookingEmailVo;

public interface IOtherCarrierEmailRender {
    public void genrateBookingEmailMessage(OtherCarrierBookingEmailVo otherCarrierBookingEmailVo, String outputFilePath) throws Exception;
}
