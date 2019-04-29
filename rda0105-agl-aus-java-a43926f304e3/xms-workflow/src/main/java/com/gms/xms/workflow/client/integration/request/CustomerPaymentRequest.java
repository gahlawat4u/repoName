package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.CustomerPaymentVo;
import com.gms.xms.txndb.vo.NoteVo;
import com.gms.xms.txndb.vo.OverpaymentVo;

/**
 * Posted from CustomerPaymentRequest
 * <p>
 * Author DatTV Date Apr 17, 2015 10:01:09 PM
 */
public class CustomerPaymentRequest extends BaseRequest {
    private CustomerPaymentVo customerPaymentVo;
    private OverpaymentVo overpaymentVo;
    private NoteVo noteVo;

    public CustomerPaymentVo getCustomerPaymentVo() {
        return customerPaymentVo;
    }

    public void setCustomerPaymentVo(CustomerPaymentVo customerPaymentVo) {
        this.customerPaymentVo = customerPaymentVo;
    }

    public OverpaymentVo getOverpaymentVo() {
        return overpaymentVo;
    }

    public void setOverpaymentVo(OverpaymentVo overpaymentVo) {
        this.overpaymentVo = overpaymentVo;
    }

    public NoteVo getNoteVo() {
        return noteVo;
    }

    public void setNoteVo(NoteVo noteVo) {
        this.noteVo = noteVo;
    }
}
