package com.gms.xms.model.admin.receivables.payments;

import com.gms.xms.model.BaseModel;

public class SearchPaymentColumnFlagsModel extends BaseModel {
    private static final long serialVersionUID = -802246140119153034L;

    private String hasInvoice;
    private String hasCustomerNo;
    private String hasCustomerName;
    private String hasDate;
    private String hasNotes;
    private String hasTypeOfOverpayment;

    public String getHasInvoice() {
        return hasInvoice;
    }

    public void setHasInvoice(String hasInvoice) {
        this.hasInvoice = hasInvoice;
    }

    public String getHasCustomerNo() {
        return hasCustomerNo;
    }

    public void setHasCustomerNo(String hasCustomerNo) {
        this.hasCustomerNo = hasCustomerNo;
    }

    public String getHasCustomerName() {
        return hasCustomerName;
    }

    public void setHasCustomerName(String hasCustomerName) {
        this.hasCustomerName = hasCustomerName;
    }

    public String getHasDate() {
        return hasDate;
    }

    public void setHasDate(String hasDate) {
        this.hasDate = hasDate;
    }

    public String getHasNotes() {
        return hasNotes;
    }

    public void setHasNotes(String hasNotes) {
        this.hasNotes = hasNotes;
    }

    public String getHasTypeOfOverpayment() {
        return hasTypeOfOverpayment;
    }

    public void setHasTypeOfOverpayment(String hasTypeOfOverpayment) {
        this.hasTypeOfOverpayment = hasTypeOfOverpayment;
    }

    @Override
    public String toString() {
        return "SearchPaymentColumnFlagsModel [hasInvoice=" + hasInvoice + ", hasCustomerNo=" + hasCustomerNo + ", hasCustomerName=" + hasCustomerName + ", hasDate=" + hasDate + ", hasNotes=" + hasNotes + ", hasTypeOfOverpayment=" + hasTypeOfOverpayment + "]";
    }
}
