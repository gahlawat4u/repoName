package com.gms.xms.txndb.vo.payables.salesrep;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from SalesRepOverviewVo
 * <p>
 * Author dattrinh Mar 23, 2016 4:26:44 PM
 */
public class SalesRepOverviewVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long salesRepId;
    private Long userId;
    private String userCode;
    private String userName;
    private String displayName;
    private Long setups;
    private Long activations;
    private Long printedInvoices;
    private Long emailInvoices;

    public Long getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Long getSetups() {
        return setups;
    }

    public void setSetups(Long setups) {
        this.setups = setups;
    }

    public Long getActivations() {
        return activations;
    }

    public void setActivations(Long activations) {
        this.activations = activations;
    }

    public Long getPrintedInvoices() {
        return printedInvoices;
    }

    public void setPrintedInvoices(Long printedInvoices) {
        this.printedInvoices = printedInvoices;
    }

    public Long getEmailInvoices() {
        return emailInvoices;
    }

    public void setEmailInvoices(Long emailInvoices) {
        this.emailInvoices = emailInvoices;
    }

    @Override
    public String toString() {
        return "SalesRepOverviewVo [salesRepId=" + salesRepId + ", userId=" + userId + ", userCode=" + userCode + ", userName=" + userName + ", displayName=" + displayName + ", setups=" + setups + ", activations=" + activations + ", printedInvoices=" + printedInvoices + ", emailInvoices=" + emailInvoices + "]";
    }
}
