package com.gms.xms.model.payables.salesrep;

import com.gms.xms.model.BaseModel;

/**
 * Posted from SalesRepOverviewModel
 * <p>
 * Author dattrinh Mar 23, 2016 4:28:53 PM
 */
public class SalesRepOverviewModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String salesRepId;
    private String userId;
    private String userCode;
    private String userName;
    private String displayName;
    private String setups;
    private String activations;
    private String printedInvoices;
    private String emailInvoices;

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSetups() {
        return setups;
    }

    public void setSetups(String setups) {
        this.setups = setups;
    }

    public String getActivations() {
        return activations;
    }

    public void setActivations(String activations) {
        this.activations = activations;
    }

    public String getPrintedInvoices() {
        return printedInvoices;
    }

    public void setPrintedInvoices(String printedInvoices) {
        this.printedInvoices = printedInvoices;
    }

    public String getEmailInvoices() {
        return emailInvoices;
    }

    public void setEmailInvoices(String emailInvoices) {
        this.emailInvoices = emailInvoices;
    }

    @Override
    public String toString() {
        return "SalesRepOverviewModel [salesRepId=" + salesRepId + ", userId=" + userId + ", userCode=" + userCode + ", userName=" + userName + ", displayName=" + displayName + ", setups=" + setups + ", activations=" + activations + ", printedInvoices=" + printedInvoices + ", emailInvoices=" + emailInvoices + "]";
    }
}
