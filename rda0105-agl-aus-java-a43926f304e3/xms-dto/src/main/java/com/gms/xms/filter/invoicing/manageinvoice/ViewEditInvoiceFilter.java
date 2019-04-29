package com.gms.xms.filter.invoicing.manageinvoice;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.filter.BaseFilter;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from ViewEditInvoiceFilter
 * <p>
 * Author TANDT
 */
public class ViewEditInvoiceFilter extends BaseFilter {

    private String franchiseList;
    private Date fromDate;
    private Date toDate;
    private Date invoiceDate;
    private String invoiceCode;
    private String franchiseSearchTypeValue;
    private String franchiseSearchType;
    private Integer minAirbills;
    private Integer maxAirbills;
    private Integer emailInvoice;
    private Integer status;
    private String customerCode;
    private Integer searchType;

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Integer getMinAirbills() {
        return minAirbills;
    }

    public void setMinAirbills(Integer minAirbills) {
        this.minAirbills = minAirbills;
    }

    public Integer getMaxAirbills() {
        return maxAirbills;
    }

    public void setMaxAirbills(Integer maxAirbills) {
        this.maxAirbills = maxAirbills;
    }

    public String getFranchiseSearchTypeValue() {
        return franchiseSearchTypeValue;
    }

    public void setFranchiseSearchTypeValue(String franchiseSearchTypeValue) {
        this.franchiseSearchTypeValue = franchiseSearchTypeValue;
    }

    public String getFranchiseSearchType() {
        return franchiseSearchType;
    }

    public void setFranchiseSearchType(String franchiseSearchType) {
        this.franchiseSearchType = franchiseSearchType;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getFromDate() {
        return fromDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getToDate() {
        return toDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer isEmailInvoice() {
        return emailInvoice;
    }

    public void setEmailInvoice(Integer emailInvoice) {
        this.emailInvoice = emailInvoice;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }
}