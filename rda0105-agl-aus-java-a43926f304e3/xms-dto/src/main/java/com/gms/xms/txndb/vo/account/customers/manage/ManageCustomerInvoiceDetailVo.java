package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.common.json.JsonDoubleSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from ManageCustomerInvoiceDetailVo
 * <p>
 * Author DatTV Sep 25, 2015
 */
public class ManageCustomerInvoiceDetailVo extends ManageCustomerInvoiceVo {

    private static final long serialVersionUID = 7068327427668783649L;

    private Double customerPaid;
    private Double creditTotal;
    private Double creditNote;
    private Double badDebt;
    private Double satisfactionCredit;
    private Double marginExcTax;
    private Double paidLateFee;
    private Double totalPaidCredit;

    @Override
    public String toString() {
        return "ManageCustomerInvoiceDetailVo [customerPaid=" + customerPaid + ", creditTotal=" + creditTotal + ", creditNote=" + creditNote + ", badDebt=" + badDebt + ", satisfactionCredit=" + satisfactionCredit + ", marginExcTax=" + marginExcTax + ", paidLateFee=" + paidLateFee + ", totalPaidCredit=" + totalPaidCredit + "]";
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(Double creditTotal) {
        this.creditTotal = creditTotal;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(Double creditNote) {
        this.creditNote = creditNote;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getBadDebt() {
        return badDebt;
    }

    public void setBadDebt(Double badDebt) {
        this.badDebt = badDebt;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getSatisfactionCredit() {
        return satisfactionCredit;
    }

    public void setSatisfactionCredit(Double satisfactionCredit) {
        this.satisfactionCredit = satisfactionCredit;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getMarginExcTax() {
        return marginExcTax;
    }

    public void setMarginExcTax(Double marginExcTax) {
        this.marginExcTax = marginExcTax;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getPaidLateFee() {
        return paidLateFee;
    }

    public void setPaidLateFee(Double paidLateFee) {
        this.paidLateFee = paidLateFee;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getCustomerPaid() {
        return customerPaid;
    }

    public void setCustomerPaid(Double customerPaid) {
        this.customerPaid = customerPaid;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getTotalPaidCredit() {
        return totalPaidCredit;
    }

    public void setTotalPaidCredit(Double totalPaidCredit) {
        this.totalPaidCredit = totalPaidCredit;
    }
}
