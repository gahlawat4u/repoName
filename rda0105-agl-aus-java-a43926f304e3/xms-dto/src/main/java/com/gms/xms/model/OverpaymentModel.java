package com.gms.xms.model;

/**
 * Posted from OverpaymentModel
 * <p>
 * Author DatTV Date Apr 23, 2015 11:23:25 AM
 */
public class OverpaymentModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String cusPaymentId;

    private String overAmount;

    private String countOverPay;

    public String getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(String cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public String getOverAmount() {
        return overAmount;
    }

    public void setOverAmount(String overAmount) {
        this.overAmount = overAmount;
    }

    public String getCountOverPay() {
        return countOverPay;
    }

    public void setCountOverPay(String countOverPay) {
        this.countOverPay = countOverPay;
    }

    @Override
    public String toString() {
        return "OverpaymentModel [cusPaymentId=" + cusPaymentId + ", overAmount=" + overAmount + ", countOverPay=" + countOverPay + "]";
    }

}