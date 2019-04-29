package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from CustomerDefaultSettingVo
 * <p>
 * Author DatTV Date Mar 26, 2015
 */
public class CustomerDefaultSettingVo extends BaseVo {
    private static final long serialVersionUID = 5296114265869864680L;

    private Long customerCode;

    private Integer billingParty;

    private String billingAccount;

    private Integer dutiesBillTo;

    private String dutiesAccount;

    private String defaultServiceType;

    private Integer defaultPackageType;

    private String notificationEmail;

    private String notificationMessage;

    private Long defaultFromAddressId;

    private Long defaultToAddressId;

    private Integer defaultCollection;

    private Boolean disableQuote;

    private Boolean globalAddressBook;

    private Boolean individualUserHistory;

    private String defaultTermsOfTrade;

    private Boolean batchProcessing;

    private String defaultShipperReference;

    public String getDefaultShipperReference() {
        return defaultShipperReference;
    }

    public void setDefaultShipperReference(String defaultShipperReference) {
        this.defaultShipperReference = defaultShipperReference;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getBillingParty() {
        return billingParty;
    }

    public void setBillingParty(Integer billingParty) {
        this.billingParty = billingParty;
    }

    public String getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(String billingAccount) {
        this.billingAccount = billingAccount == null ? null : billingAccount.trim();
    }

    public Integer getDutiesBillTo() {
        return dutiesBillTo;
    }

    public void setDutiesBillTo(Integer dutiesBillTo) {
        this.dutiesBillTo = dutiesBillTo;
    }

    public String getDutiesAccount() {
        return dutiesAccount;
    }

    public void setDutiesAccount(String dutiesAccount) {
        this.dutiesAccount = dutiesAccount == null ? null : dutiesAccount.trim();
    }

    public String getDefaultServiceType() {
        return defaultServiceType;
    }

    public void setDefaultServiceType(String defaultServiceType) {
        this.defaultServiceType = defaultServiceType == null ? null : defaultServiceType.trim();
    }

    public Integer getDefaultPackageType() {
        return defaultPackageType;
    }

    public void setDefaultPackageType(Integer defaultPackageType) {
        this.defaultPackageType = defaultPackageType;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail == null ? null : notificationEmail.trim();
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage == null ? null : notificationMessage.trim();
    }

    public Long getDefaultFromAddressId() {
        return defaultFromAddressId;
    }

    public void setDefaultFromAddressId(Long defaultFromAddressId) {
        this.defaultFromAddressId = defaultFromAddressId;
    }

    public Long getDefaultToAddressId() {
        return defaultToAddressId;
    }

    public void setDefaultToAddressId(Long defaultToAddressId) {
        this.defaultToAddressId = defaultToAddressId;
    }

    public Integer getDefaultCollection() {
        return defaultCollection;
    }

    public void setDefaultCollection(Integer defaultCollection) {
        this.defaultCollection = defaultCollection;
    }

    public Boolean getDisableQuote() {
        return disableQuote;
    }

    public void setDisableQuote(Boolean disableQuote) {
        this.disableQuote = disableQuote;
    }

    public Boolean getGlobalAddressBook() {
        return globalAddressBook;
    }

    public void setGlobalAddressBook(Boolean globalAddressBook) {
        this.globalAddressBook = globalAddressBook;
    }

    public Boolean getIndividualUserHistory() {
        return individualUserHistory;
    }

    public void setIndividualUserHistory(Boolean individualUserHistory) {
        this.individualUserHistory = individualUserHistory;
    }

    public String getDefaultTermsOfTrade() {
        return defaultTermsOfTrade;
    }

    public void setDefaultTermsOfTrade(String defaultTermsOfTrade) {
        this.defaultTermsOfTrade = defaultTermsOfTrade == null ? null : defaultTermsOfTrade.trim();
    }

    public Boolean getBatchProcessing() {
        return batchProcessing;
    }

    public void setBatchProcessing(Boolean batchProcessing) {
        this.batchProcessing = batchProcessing;
    }

    @Override
    public String toString() {
        return "CustomerDefaultSettingVo [customerCode=" + customerCode + ", billingParty=" + billingParty + ", billingAccount=" + billingAccount + ", dutiesBillTo=" + dutiesBillTo + ", dutiesAccount=" + dutiesAccount + ", defaultServiceType=" + defaultServiceType + ", defaultPackageType=" + defaultPackageType + ", notificationEmail=" + notificationEmail + ", notificationMessage=" + notificationMessage + ", defaultFromAddressId=" + defaultFromAddressId + ", defaultToAddressId="
                + defaultToAddressId + ", defaultCollection=" + defaultCollection + ", disableQuote=" + disableQuote + ", globalAddressBook=" + globalAddressBook + ", individualUserHistory=" + individualUserHistory + ", defaultTermsOfTrade=" + defaultTermsOfTrade + ", batchProcessing=" + batchProcessing + ", defaultShipperReference=" + defaultShipperReference + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((batchProcessing == null) ? 0 : batchProcessing.hashCode());
        result = prime * result + ((billingAccount == null) ? 0 : billingAccount.hashCode());
        result = prime * result + ((billingParty == null) ? 0 : billingParty.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((defaultCollection == null) ? 0 : defaultCollection.hashCode());
        result = prime * result + ((defaultFromAddressId == null) ? 0 : defaultFromAddressId.hashCode());
        result = prime * result + ((defaultPackageType == null) ? 0 : defaultPackageType.hashCode());
        result = prime * result + ((defaultServiceType == null) ? 0 : defaultServiceType.hashCode());
        result = prime * result + ((defaultShipperReference == null) ? 0 : defaultShipperReference.hashCode());
        result = prime * result + ((defaultTermsOfTrade == null) ? 0 : defaultTermsOfTrade.hashCode());
        result = prime * result + ((defaultToAddressId == null) ? 0 : defaultToAddressId.hashCode());
        result = prime * result + ((disableQuote == null) ? 0 : disableQuote.hashCode());
        result = prime * result + ((dutiesAccount == null) ? 0 : dutiesAccount.hashCode());
        result = prime * result + ((dutiesBillTo == null) ? 0 : dutiesBillTo.hashCode());
        result = prime * result + ((globalAddressBook == null) ? 0 : globalAddressBook.hashCode());
        result = prime * result + ((individualUserHistory == null) ? 0 : individualUserHistory.hashCode());
        result = prime * result + ((notificationEmail == null) ? 0 : notificationEmail.hashCode());
        result = prime * result + ((notificationMessage == null) ? 0 : notificationMessage.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerDefaultSettingVo other = (CustomerDefaultSettingVo) obj;
        if (batchProcessing == null) {
            if (other.batchProcessing != null)
                return false;
        } else if (!batchProcessing.equals(other.batchProcessing))
            return false;
        if (billingAccount == null) {
            if (other.billingAccount != null)
                return false;
        } else if (!billingAccount.equals(other.billingAccount))
            return false;
        if (billingParty == null) {
            if (other.billingParty != null)
                return false;
        } else if (!billingParty.equals(other.billingParty))
            return false;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (defaultCollection == null) {
            if (other.defaultCollection != null)
                return false;
        } else if (!defaultCollection.equals(other.defaultCollection))
            return false;
        if (defaultFromAddressId == null) {
            if (other.defaultFromAddressId != null)
                return false;
        } else if (!defaultFromAddressId.equals(other.defaultFromAddressId))
            return false;
        if (defaultPackageType == null) {
            if (other.defaultPackageType != null)
                return false;
        } else if (!defaultPackageType.equals(other.defaultPackageType))
            return false;
        if (defaultServiceType == null) {
            if (other.defaultServiceType != null)
                return false;
        } else if (!defaultServiceType.equals(other.defaultServiceType))
            return false;
        if (defaultShipperReference == null) {
            if (other.defaultShipperReference != null)
                return false;
        } else if (!defaultShipperReference.equals(other.defaultShipperReference))
            return false;
        if (defaultTermsOfTrade == null) {
            if (other.defaultTermsOfTrade != null)
                return false;
        } else if (!defaultTermsOfTrade.equals(other.defaultTermsOfTrade))
            return false;
        if (defaultToAddressId == null) {
            if (other.defaultToAddressId != null)
                return false;
        } else if (!defaultToAddressId.equals(other.defaultToAddressId))
            return false;
        if (disableQuote == null) {
            if (other.disableQuote != null)
                return false;
        } else if (!disableQuote.equals(other.disableQuote))
            return false;
        if (dutiesAccount == null) {
            if (other.dutiesAccount != null)
                return false;
        } else if (!dutiesAccount.equals(other.dutiesAccount))
            return false;
        if (dutiesBillTo == null) {
            if (other.dutiesBillTo != null)
                return false;
        } else if (!dutiesBillTo.equals(other.dutiesBillTo))
            return false;
        if (globalAddressBook == null) {
            if (other.globalAddressBook != null)
                return false;
        } else if (!globalAddressBook.equals(other.globalAddressBook))
            return false;
        if (individualUserHistory == null) {
            if (other.individualUserHistory != null)
                return false;
        } else if (!individualUserHistory.equals(other.individualUserHistory))
            return false;
        if (notificationEmail == null) {
            if (other.notificationEmail != null)
                return false;
        } else if (!notificationEmail.equals(other.notificationEmail))
            return false;
        if (notificationMessage == null) {
            if (other.notificationMessage != null)
                return false;
        } else if (!notificationMessage.equals(other.notificationMessage))
            return false;
        return true;
    }
}