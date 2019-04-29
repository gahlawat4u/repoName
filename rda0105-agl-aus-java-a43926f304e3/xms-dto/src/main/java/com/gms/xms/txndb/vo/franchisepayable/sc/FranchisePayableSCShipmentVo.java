package com.gms.xms.txndb.vo.franchisepayable.sc;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from FranchisePayableSCShipmentVo
 * <p>
 * Author DatTV Dec 9, 2015
 */
public class FranchisePayableSCShipmentVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String rptTxnId;
    private Date importDate;
    private Long customerCode;
    private String customerName;
    private String invoiceCode;
    private String airbillNumber;
    private Boolean isDomestic;
    private Boolean isTaxableShipment;
    private Double custCost;
    private Double custTax;
    private Double custMarginable;
    private Double custMarginableTax;
    private Double franCost;
    private Double franTax;
    private Double grossMargin;
    private Double grossMarginTax;
    private Double franCredit;
    private Double custCredit;
    private Double managementFee;
    private Double marketingFee;
    private Double profitShare;

    @Override
    public String toString() {
        return "FranchisePayableSCShipmentVo [rptTxnId=" + rptTxnId + ", importDate=" + importDate + ", customerCode=" + customerCode + ", customerName=" + customerName + ", invoiceCode=" + invoiceCode + ", airbillNumber=" + airbillNumber + ", isDomestic=" + isDomestic + ", isTaxableShipment=" + isTaxableShipment + ", custCost=" + custCost + ", custTax=" + custTax + ", custMarginable=" + custMarginable + ", custMarginableTax=" + custMarginableTax + ", franCost=" + franCost + ", franTax="
                + franTax + ", grossMargin=" + grossMargin + ", grossMarginTax=" + grossMarginTax + ", franCredit=" + franCredit + ", custCredit=" + custCredit + ", managementFee=" + managementFee + ", marketingFee=" + marketingFee + ", profitShare=" + profitShare + "]";
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public Boolean getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(Boolean isDomestic) {
        this.isDomestic = isDomestic;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustCost() {
        return custCost;
    }

    public void setCustCost(Double custCost) {
        this.custCost = custCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustTax() {
        return custTax;
    }

    public void setCustTax(Double custTax) {
        this.custTax = custTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranCost() {
        return franCost;
    }

    public void setFranCost(Double franCost) {
        this.franCost = franCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranTax() {
        return franTax;
    }

    public void setFranTax(Double franTax) {
        this.franTax = franTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(Double grossMargin) {
        this.grossMargin = grossMargin;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGrossMarginTax() {
        return grossMarginTax;
    }

    public void setGrossMarginTax(Double grossMarginTax) {
        this.grossMarginTax = grossMarginTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranCredit() {
        return franCredit;
    }

    public void setFranCredit(Double franCredit) {
        this.franCredit = franCredit;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustCredit() {
        return custCredit;
    }

    public void setCustCredit(Double custCredit) {
        this.custCredit = custCredit;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarketingFee() {
        return marketingFee;
    }

    public void setMarketingFee(Double marketingFee) {
        this.marketingFee = marketingFee;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getProfitShare() {
        return profitShare;
    }

    public void setProfitShare(Double profitShare) {
        this.profitShare = profitShare;
    }

    public Boolean getIsTaxableShipment() {
        return isTaxableShipment;
    }

    public void setIsTaxableShipment(Boolean isTaxableShipment) {
        this.isTaxableShipment = isTaxableShipment;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustMarginable() {
        return custMarginable;
    }

    public void setCustMarginable(Double custMarginable) {
        this.custMarginable = custMarginable;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustMarginableTax() {
        return custMarginableTax;
    }

    public void setCustMarginableTax(Double custMarginableTax) {
        this.custMarginableTax = custMarginableTax;
    }
}
