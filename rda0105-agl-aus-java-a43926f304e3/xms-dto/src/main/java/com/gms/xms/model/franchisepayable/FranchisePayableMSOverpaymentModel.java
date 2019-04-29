package com.gms.xms.model.franchisepayable;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayableMSOverpaymentModel.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:17:08 PM
 */
public class FranchisePayableMSOverpaymentModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    private String originPaymentDate;
    private String customerNumber;
    private String customerName;
    private String overpaymentType;
    private String amount;

    public String getOriginPaymentDate() {
        return originPaymentDate;
    }

    public void setOriginPaymentDate(String originPaymentDate) {
        this.originPaymentDate = originPaymentDate;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOverpaymentType() {
        return overpaymentType;
    }

    public void setOverpaymentType(String overpaymentType) {
        this.overpaymentType = overpaymentType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
