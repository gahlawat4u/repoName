package com.gms.xms.model.adjustmentrequest;

/**
 * Posted from AdjustmentClaimRequestModel
 * </p>
 *
 * @author hungnt - Nov 5, 2015
 */
public class AdjustmentClaimRequestModel extends AirbillAdjustmentRequestModel {
    private static final long serialVersionUID = -8711852292453327678L;
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
