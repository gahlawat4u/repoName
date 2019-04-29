package com.gms.xms.model.franchisepayable;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayableMSDeductModel.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:16:54 PM
 */
public class FranchisePayableMSDeductModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    private String customerName;
    private String invoiceNumber;
    private String airbillNumber;
    private String customerPayment;
    private String customerCost;
    private String customerTax;
    private String franchiseCost;
    private String franchiseTax;
    private String franchiseCharge;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getCustomerPayment() {
        return customerPayment;
    }

    public void setCustomerPayment(String customerPayment) {
        this.customerPayment = customerPayment;
    }

    public String getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(String customerCost) {
        this.customerCost = customerCost;
    }

    public String getCustomerTax() {
        return customerTax;
    }

    public void setCustomerTax(String customerTax) {
        this.customerTax = customerTax;
    }

    public String getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(String franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    public String getFranchiseTax() {
        return franchiseTax;
    }

    public void setFranchiseTax(String franchiseTax) {
        this.franchiseTax = franchiseTax;
    }

    public String getFranchiseCharge() {
        return franchiseCharge;
    }

    public void setFranchiseCharge(String franchiseCharge) {
        this.franchiseCharge = franchiseCharge;
    }

}
