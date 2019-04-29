package com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * File Name: InvoiceExistingFilter.java <br/>
 * Author: TANDT <br/>
 * Create Date: 15-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill <br/>
 */
public class InvoiceExistingFilter extends BaseVo {

    private static final long serialVersionUID = 622603942522414833L;

    private String franchiseList;
    private Date invoiceDate;
    private String invoiceDateList;
    private String customerCode;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceDateList() {
        return invoiceDateList;
    }

    public void setInvoiceDateList(String invoiceDateList) {
        this.invoiceDateList = invoiceDateList;
    }

    @Override
    public String toString() {
        return "InvoiceExistingFilter [franchiseList=" + franchiseList + ", invoiceDate=" + invoiceDate + ", invoiceDateList=" + invoiceDateList + ", customerCode=" + customerCode + "]";
    }

}
