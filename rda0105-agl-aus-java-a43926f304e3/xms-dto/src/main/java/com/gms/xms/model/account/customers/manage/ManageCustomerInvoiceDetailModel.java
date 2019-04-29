package com.gms.xms.model.account.customers.manage;

/**
 * Posted from ManageCustomerInvoiceDetailModel
 * <p>
 * Author DatTV Sep 25, 2015
 */
public class ManageCustomerInvoiceDetailModel extends ManageCustomerInvoiceModel {

    private static final long serialVersionUID = 7068327427668783649L;

    private String customerPaid;
    private String creditTotal;
    private String creditNote;
    private String badDebt;
    private String satisfactionCredit;
    private String marginExcTax;
    private String paidLateFee;
    private String totalPaidCredit;

    @Override
    public String toString() {
        return "ManageCustomerInvoiceDetailModel [customerPaid=" + customerPaid + ", creditTotal=" + creditTotal + ", creditNote=" + creditNote + ", badDebt=" + badDebt + ", satisfactionCredit=" + satisfactionCredit + ", marginExcTax=" + marginExcTax + ", paidLateFee=" + paidLateFee + ", totalPaidCredit=" + totalPaidCredit + "]";
    }

    public String getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(String creditTotal) {
        this.creditTotal = creditTotal;
    }

    public String getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(String creditNote) {
        this.creditNote = creditNote;
    }

    public String getBadDebt() {
        return badDebt;
    }

    public void setBadDebt(String badDebt) {
        this.badDebt = badDebt;
    }

    public String getSatisfactionCredit() {
        return satisfactionCredit;
    }

    public void setSatisfactionCredit(String satisfactionCredit) {
        this.satisfactionCredit = satisfactionCredit;
    }

    public String getMarginExcTax() {
        return marginExcTax;
    }

    public void setMarginExcTax(String marginExcTax) {
        this.marginExcTax = marginExcTax;
    }

    public String getPaidLateFee() {
        return paidLateFee;
    }

    public void setPaidLateFee(String paidLateFee) {
        this.paidLateFee = paidLateFee;
    }

    public String getCustomerPaid() {
        return customerPaid;
    }

    public void setCustomerPaid(String customerPaid) {
        this.customerPaid = customerPaid;
    }

    public String getTotalPaidCredit() {
        return totalPaidCredit;
    }

    public void setTotalPaidCredit(String totalPaidCredit) {
        this.totalPaidCredit = totalPaidCredit;
    }
}
