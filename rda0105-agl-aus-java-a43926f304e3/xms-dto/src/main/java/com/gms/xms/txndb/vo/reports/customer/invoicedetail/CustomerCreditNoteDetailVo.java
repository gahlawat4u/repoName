package com.gms.xms.txndb.vo.reports.customer.invoicedetail;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from CustomerCreditNoteDetailVo.java
 * <p>
 * Author dattrinh 10:31:37 AM
 */
public class CustomerCreditNoteDetailVo extends BaseVo {

    private static final long serialVersionUID = -7257867503234921426L;

    private Long creditNoteId;
    private String creditCode;
    private String customerName;
    private Date createDate;
    private Double amount;
    private Double gst;
    private Double total;

    @Override
    public String toString() {
        return "CustomerCreditNoteDetailVo [creditNoteId=" + creditNoteId + ", creditCode=" + creditCode + ", customerName=" + customerName + ", createDate=" + createDate + ", amount=" + amount + ", gst=" + gst + ", total=" + total + "]";
    }

    public Long getCreditNoteId() {
        return creditNoteId;
    }

    public void setCreditNoteId(Long creditNoteId) {
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

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGst() {
        return gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
