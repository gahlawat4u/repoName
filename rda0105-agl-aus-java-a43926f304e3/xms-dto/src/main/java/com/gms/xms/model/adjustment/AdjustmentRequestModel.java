package com.gms.xms.model.adjustment;

import com.gms.xms.model.AirbillAdjustmentModel;

/**
 * Posted from AdjustmentRequestVo
 * <p>
 * Author DatTV Date May 18, 2015 9:13:06 AM
 */
public class AdjustmentRequestModel extends AirbillAdjustmentModel {
    private static final long serialVersionUID = 8167241493738808178L;
    private String adjustType;
    private String invoiceCode;

    public String getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }
}
