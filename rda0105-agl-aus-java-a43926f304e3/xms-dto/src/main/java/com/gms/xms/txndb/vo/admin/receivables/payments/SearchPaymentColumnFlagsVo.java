package com.gms.xms.txndb.vo.admin.receivables.payments;

import com.gms.xms.txndb.vo.BaseVo;

public class SearchPaymentColumnFlagsVo extends BaseVo {
    private static final long serialVersionUID = 5099787285675473401L;

    private Boolean hasInvoice;
    private Boolean hasCustomerNo;
    private Boolean hasCustomerName;
    private Boolean hasDate;
    private Boolean hasNotes;
    private Boolean hasTypeOfOverpayment;

    public Boolean getHasInvoice() {
        return hasInvoice;
    }

    public void setHasInvoice(Boolean hasInvoice) {
        this.hasInvoice = hasInvoice;
    }

    public Boolean getHasCustomerNo() {
        return hasCustomerNo;
    }

    public void setHasCustomerNo(Boolean hasCustomerNo) {
        this.hasCustomerNo = hasCustomerNo;
    }

    public Boolean getHasCustomerName() {
        return hasCustomerName;
    }

    public void setHasCustomerName(Boolean hasCustomerName) {
        this.hasCustomerName = hasCustomerName;
    }

    public Boolean getHasDate() {
        return hasDate;
    }

    public void setHasDate(Boolean hasDate) {
        this.hasDate = hasDate;
    }

    public Boolean getHasNotes() {
        return hasNotes;
    }

    public void setHasNotes(Boolean hasNotes) {
        this.hasNotes = hasNotes;
    }

    public Boolean getHasTypeOfOverpayment() {
        return hasTypeOfOverpayment;
    }

    public void setHasTypeOfOverpayment(Boolean hasTypeOfOverpayment) {
        this.hasTypeOfOverpayment = hasTypeOfOverpayment;
    }

    @Override
    public String toString() {
        return "SearchPaymentColumnFlagsVo [hasInvoice=" + hasInvoice + ", hasCustomerNo=" + hasCustomerNo + ", hasCustomerName=" + hasCustomerName + ", hasDate=" + hasDate + ", hasNotes=" + hasNotes + ", hasTypeOfOverpayment=" + hasTypeOfOverpayment + "]";
    }
}
