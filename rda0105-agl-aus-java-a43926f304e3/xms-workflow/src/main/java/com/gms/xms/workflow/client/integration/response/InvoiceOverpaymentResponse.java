package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Posted from InvoiceOverpaymentResponse
 * <p>
 * Author DatTV Date Apr 27, 2015 10:51:26 AM
 */
public class InvoiceOverpaymentResponse extends BaseResponse {
    private List<OverpaymentInfoVo> overpaymentList;
    private BigDecimal totalOverpayment;

    public List<OverpaymentInfoVo> getOverpaymentList() {
        return overpaymentList;
    }

    public void setOverpaymentList(List<OverpaymentInfoVo> overpaymentList) {
        this.overpaymentList = overpaymentList;
    }

    public BigDecimal getTotalOverpayment() {
        return totalOverpayment;
    }

    public void setTotalOverpayment(BigDecimal totalOverpayment) {
        this.totalOverpayment = totalOverpayment;
    }
}
