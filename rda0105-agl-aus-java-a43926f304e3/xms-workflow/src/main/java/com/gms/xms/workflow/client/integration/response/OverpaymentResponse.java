package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.OverpaymentVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Posted from OverpaymentResponse
 * <p>
 * Author DatTV Date Apr 14, 2015 10:15:08 AM
 */
public class OverpaymentResponse extends BaseResponse {
    private List<OverpaymentVo> overpaymentList;

    public List<OverpaymentVo> getOverpaymentList() {
        return overpaymentList;
    }

    public void setOverpaymentList(List<OverpaymentVo> overpaymentList) {
        this.overpaymentList = overpaymentList;
    }

    /**
     * @return Total of over payment amounts
     */
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OverpaymentVo overpayment : overpaymentList) {
            total = total.add(overpayment.getOverAmount());
        }
        return total;
    }
}
