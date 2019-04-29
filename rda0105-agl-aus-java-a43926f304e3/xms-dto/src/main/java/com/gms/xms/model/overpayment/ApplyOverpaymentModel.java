package com.gms.xms.model.overpayment;

import com.gms.xms.model.BaseModel;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Posted from ApplyOverpaymentModel
 * <p>
 * Author DatTV Date Apr 27, 2015 3:17:37 PM
 */
public class ApplyOverpaymentModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String customerCode;
    private String customerName;
    private String source;
    private String cusPaymentId;
    private String amountToApply;
    private String unappliedAmount;
    private String appliedAmount;

    @JsonIgnore
    private String submitType;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(String cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public String getAmountToApply() {
        return amountToApply;
    }

    public void setAmountToApply(String amountToApply) {
        this.amountToApply = amountToApply;
    }

    public String getUnappliedAmount() {
        return unappliedAmount;
    }

    public void setUnappliedAmount(String unappliedAmount) {
        this.unappliedAmount = unappliedAmount;
    }

    public String getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(String appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    @Override
    public String toString() {
        return "ApplyOverpaymentModel [customerCode=" + customerCode + ", customerName=" + customerName + ", cusPaymentId=" + cusPaymentId + ", amountToApply=" + amountToApply + ", unappliedAmount=" + unappliedAmount + ", appliedAmount=" + appliedAmount + "]";
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }
}