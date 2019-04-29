package com.gms.xms.txndb.vo.adjustmentrequest;

/**
 * Posted from AdjustmentClaimRequestVo
 * </p>
 *
 * @author hungnt - Nov 5, 2015
 */
public class AdjustmentClaimRequestVo extends AirbillAdjustmentRequestVo {
    private static final long serialVersionUID = -4924963783597501375L;
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

    @Override
    public String toString() {
        return "AdjustmentClaimRequestVo [adjustType=" + adjustType + ", invoiceCode=" + invoiceCode + "]";
    }
}
