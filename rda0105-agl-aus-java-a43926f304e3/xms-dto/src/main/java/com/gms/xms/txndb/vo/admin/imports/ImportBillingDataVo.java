package com.gms.xms.txndb.vo.admin.imports;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Date;

/**
 * Posted from May 24, 2016 8:50:37 AM
 * <p>
 * Author huynd
 */
public class ImportBillingDataVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long carrierId;
    private Date invoiceDate;
    private Boolean hasHeader;

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Boolean getHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(Boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    @Override
    public String toString() {
        return "ImportBillingDataVo [carrierId=" + carrierId + ", invoiceDate=" + invoiceDate + ", hasHeader=" + hasHeader + "]";
    }

}
