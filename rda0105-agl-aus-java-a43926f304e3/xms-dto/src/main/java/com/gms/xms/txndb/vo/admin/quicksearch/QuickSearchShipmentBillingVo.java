package com.gms.xms.txndb.vo.admin.quicksearch;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from Apr 27, 2016 2:02:01 PM
 * <p>
 * Author huynd
 */
public class QuickSearchShipmentBillingVo extends ShipmentBillingVo {

    private static final long serialVersionUID = 1L;

    private Long invoiceId;

    private String invoiceCode;

    private Date importDateTime;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getImportDateTime() {
        return importDateTime;
    }

    public void setImportDateTime(Date importDateTime) {
        this.importDateTime = importDateTime;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String toString() {
        return "QuickSearchShipmentBillingVo [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", importDateTime=" + importDateTime + "]";
    }
}
