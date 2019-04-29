package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from CreditNoteVo
 * <p>
 * Author DatTV Date May 20, 2015 3:26:12 PM
 */
public class CreditNoteVo extends BaseVo {
    private static final long serialVersionUID = -6633758823065961046L;

    private Long creditNoteId;

    private String creditCode;

    private Date createDate;

    private String invoiceCode;

    private Integer status;

    private InvoiceVo invoice;

    private CustomerVo customer;

    private CreditNoteDetailVo creditNoteDetail;

    private AirbillAdjustmentVo airbillAdjustment;

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

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public InvoiceVo getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceVo invoice) {
        this.invoice = invoice;
    }

    public CustomerVo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVo customer) {
        this.customer = customer;
    }

    public CreditNoteDetailVo getCreditNoteDetail() {
        return creditNoteDetail;
    }

    public void setCreditNoteDetail(CreditNoteDetailVo creditNoteDetail) {
        this.creditNoteDetail = creditNoteDetail;
    }

    public AirbillAdjustmentVo getAirbillAdjustment() {
        return airbillAdjustment;
    }

    public void setAirbillAdjustment(AirbillAdjustmentVo airbillAdjustment) {
        this.airbillAdjustment = airbillAdjustment;
    }

    @Override
    public String toString() {
        return "CreditNoteVo [creditNoteId=" + creditNoteId + ", creditCode=" + creditCode + ", createDate=" + createDate + ", invoiceCode=" + invoiceCode + ", status=" + status + ", invoice=" + invoice + ", customer=" + customer + ", creditNoteDetail=" + creditNoteDetail + ", airbillAdjustment=" + airbillAdjustment + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airbillAdjustment == null) ? 0 : airbillAdjustment.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((creditCode == null) ? 0 : creditCode.hashCode());
        result = prime * result + ((creditNoteDetail == null) ? 0 : creditNoteDetail.hashCode());
        result = prime * result + ((creditNoteId == null) ? 0 : creditNoteId.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
        result = prime * result + ((invoiceCode == null) ? 0 : invoiceCode.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CreditNoteVo other = (CreditNoteVo) obj;
        if (airbillAdjustment == null) {
            if (other.airbillAdjustment != null)
                return false;
        } else if (!airbillAdjustment.equals(other.airbillAdjustment))
            return false;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (creditCode == null) {
            if (other.creditCode != null)
                return false;
        } else if (!creditCode.equals(other.creditCode))
            return false;
        if (creditNoteDetail == null) {
            if (other.creditNoteDetail != null)
                return false;
        } else if (!creditNoteDetail.equals(other.creditNoteDetail))
            return false;
        if (creditNoteId == null) {
            if (other.creditNoteId != null)
                return false;
        } else if (!creditNoteId.equals(other.creditNoteId))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (invoice == null) {
            if (other.invoice != null)
                return false;
        } else if (!invoice.equals(other.invoice))
            return false;
        if (invoiceCode == null) {
            if (other.invoiceCode != null)
                return false;
        } else if (!invoiceCode.equals(other.invoiceCode))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

}