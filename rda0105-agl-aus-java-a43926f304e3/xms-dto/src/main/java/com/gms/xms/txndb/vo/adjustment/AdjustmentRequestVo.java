package com.gms.xms.txndb.vo.adjustment;

import com.gms.xms.txndb.vo.AirbillAdjustmentVo;

/**
 * Posted from AdjustmentRequestVo
 * <p>
 * Author DatTV Date May 18, 2015 9:13:06 AM
 */
public class AdjustmentRequestVo extends AirbillAdjustmentVo {
    private static final long serialVersionUID = 8167273311606692985L;
    private Integer adjustType;
    private String invoiceCode;

    public Integer getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(Integer adjustType) {
        this.adjustType = adjustType;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }
}
