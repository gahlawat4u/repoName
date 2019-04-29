package com.gms.xms.model.reports.customer.invoicedetail;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustomerCreditNoteDetailModel.java
 * <p>
 * Author dattrinh 2:14:40 PM
 */
public class CustomerCreditNoteDetailModel extends BaseModel {

    private static final long serialVersionUID = -7257867503234921426L;

    private String creditNoteId;
    private String creditCode;
    private String customerName;
    private String createDate;
    private String amount;
    private String gst;
    private String total;

    @Override
    public String toString() {
        return "CustomerCreditNoteDetailModel [creditNoteId=" + creditNoteId + ", creditCode=" + creditCode + ", customerName=" + customerName + ", createDate=" + createDate + ", amount=" + amount + ", gst=" + gst + ", total=" + total + "]";
    }

    public String getCreditNoteId() {
        return creditNoteId;
    }

    public void setCreditNoteId(String creditNoteId) {
        this.creditNoteId = creditNoteId;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
