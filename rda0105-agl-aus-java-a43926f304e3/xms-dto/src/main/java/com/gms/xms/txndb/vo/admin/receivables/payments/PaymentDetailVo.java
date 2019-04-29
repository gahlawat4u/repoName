package com.gms.xms.txndb.vo.admin.receivables.payments;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from PaymentDetailVo
 * <p>
 * Author dattrinh Mar 17, 2016 2:28:09 PM
 */
public class PaymentDetailVo extends BaseVo {

    private static final long serialVersionUID = -7508734585748118257L;

    private Long cusPaymentId;
    private String customerCode;
    private String customerName;
    private Date paymentDate;
    private Double amount;
    private String cheque;
    private String note;
    private Double overAmount;
    private String creditType;
    private String overPaymentType;
    private Boolean isCredit;
    private String invoiceList;

    public Long getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(Long cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getOverAmount() {
        return overAmount;
    }

    public void setOverAmount(Double overAmount) {
        this.overAmount = overAmount;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getOverPaymentType() {
        return overPaymentType;
    }

    public void setOverPaymentType(String overPaymentType) {
        this.overPaymentType = overPaymentType;
    }

    public Boolean getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(Boolean isCredit) {
        this.isCredit = isCredit;
    }

    public String getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(String invoiceList) {
        this.invoiceList = invoiceList;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentDetailVo [cusPaymentId=" + cusPaymentId + ", customerCode=" + customerCode + ", customerName=" + customerName + ", paymentDate=" + paymentDate + ", amount=" + amount + ", cheque=" + cheque + ", note=" + note + ", overAmount=" + overAmount + ", creditType=" + creditType + ", overPaymentType=" + overPaymentType + ", isCredit=" + isCredit + ", invoiceList=" + invoiceList + "]";
    }
}
