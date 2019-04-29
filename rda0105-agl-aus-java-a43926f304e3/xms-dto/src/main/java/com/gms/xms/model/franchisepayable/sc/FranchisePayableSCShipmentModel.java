package com.gms.xms.model.franchisepayable.sc;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayableSCShipmentModel
 * <p>
 * Author DatTV Dec 9, 2015
 */
public class FranchisePayableSCShipmentModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String rptTxnId;
    private String importDate;
    private String customerCode;
    private String customerName;
    private String invoiceCode;
    private String airbillNumber;
    private String isDomestic;
    private String isTaxableShipment;
    private String custCost;
    private String custTax;
    private String custMarginable;
    private String custMarginableTax;
    private String franCost;
    private String franTax;
    private String grossMargin;
    private String grossMarginTax;
    private String franCredit;
    private String custCredit;
    private String managementFee;
    private String marketingFee;
    private String profitShare;

    @Override
    public String toString() {
        return "FranchisePayableSCShipmentModel [rptTxnId=" + rptTxnId + ", importDate=" + importDate + ", customerCode=" + customerCode + ", customerName=" + customerName + ", invoiceCode=" + invoiceCode + ", airbillNumber=" + airbillNumber + ", isDomestic=" + isDomestic + ", isTaxableShipment=" + isTaxableShipment + ", custCost=" + custCost + ", custTax=" + custTax + ", custMarginable=" + custMarginable + ", custMarginableTax=" + custMarginableTax + ", franCost=" + franCost + ", franTax="
                + franTax + ", grossMargin=" + grossMargin + ", grossMarginTax=" + grossMarginTax + ", franCredit=" + franCredit + ", custCredit=" + custCredit + ", managementFee=" + managementFee + ", marketingFee=" + marketingFee + ", profitShare=" + profitShare + "]";
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
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

    public String getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(String isDomestic) {
        this.isDomestic = isDomestic;
    }

    public String getCustCost() {
        return custCost;
    }

    public void setCustCost(String custCost) {
        this.custCost = custCost;
    }

    public String getCustTax() {
        return custTax;
    }

    public void setCustTax(String custTax) {
        this.custTax = custTax;
    }

    public String getFranCost() {
        return franCost;
    }

    public void setFranCost(String franCost) {
        this.franCost = franCost;
    }

    public String getFranTax() {
        return franTax;
    }

    public void setFranTax(String franTax) {
        this.franTax = franTax;
    }

    public String getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(String grossMargin) {
        this.grossMargin = grossMargin;
    }

    public String getGrossMarginTax() {
        return grossMarginTax;
    }

    public void setGrossMarginTax(String grossMarginTax) {
        this.grossMarginTax = grossMarginTax;
    }

    public String getFranCredit() {
        return franCredit;
    }

    public void setFranCredit(String franCredit) {
        this.franCredit = franCredit;
    }

    public String getCustCredit() {
        return custCredit;
    }

    public void setCustCredit(String custCredit) {
        this.custCredit = custCredit;
    }

    public String getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(String managementFee) {
        this.managementFee = managementFee;
    }

    public String getMarketingFee() {
        return marketingFee;
    }

    public void setMarketingFee(String marketingFee) {
        this.marketingFee = marketingFee;
    }

    public String getProfitShare() {
        return profitShare;
    }

    public void setProfitShare(String profitShare) {
        this.profitShare = profitShare;
    }

    public String getIsTaxableShipment() {
        return isTaxableShipment;
    }

    public void setIsTaxableShipment(String isTaxableShipment) {
        this.isTaxableShipment = isTaxableShipment;
    }

    public String getCustMarginable() {
        return custMarginable;
    }

    public void setCustMarginable(String custMarginable) {
        this.custMarginable = custMarginable;
    }

    public String getCustMarginableTax() {
        return custMarginableTax;
    }

    public void setCustMarginableTax(String custMarginableTax) {
        this.custMarginableTax = custMarginableTax;
    }
}
