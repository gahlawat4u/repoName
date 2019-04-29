package com.gms.xms.txndb.vo;

/**
 * Posted from CreditNoteInfoSystemAdminVo
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class CreditNoteInfoSystemAdminVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 8791948734115896437L;
    private String systemAddress;
    private String mailPaymentToAddress;
    private String siteAddress;
    private Float detaulTaxPercent;

    public String getSystemAddress() {
        return systemAddress;
    }

    public void setSystemAddress(String systemAddress) {
        this.systemAddress = systemAddress;
    }

    public String getMailPaymentToAddress() {
        return mailPaymentToAddress;
    }

    public void setMailPaymentToAddress(String mailPaymentToAddress) {
        this.mailPaymentToAddress = mailPaymentToAddress;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public Float getDetaulTaxPercent() {
        return detaulTaxPercent;
    }

    public void setDetaulTaxPercent(Float detaulTaxPercent) {
        this.detaulTaxPercent = detaulTaxPercent;
    }

    @Override
    public String toString() {
        return "CreditNoteInfoSystemAdminVo [systemAddress=" + systemAddress + ", mailPaymentToAddress=" + mailPaymentToAddress + ", siteAddress=" + siteAddress + ", detaulTaxPercent=" + detaulTaxPercent + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((detaulTaxPercent == null) ? 0 : detaulTaxPercent.hashCode());
        result = prime * result + ((mailPaymentToAddress == null) ? 0 : mailPaymentToAddress.hashCode());
        result = prime * result + ((siteAddress == null) ? 0 : siteAddress.hashCode());
        result = prime * result + ((systemAddress == null) ? 0 : systemAddress.hashCode());
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
        CreditNoteInfoSystemAdminVo other = (CreditNoteInfoSystemAdminVo) obj;
        if (detaulTaxPercent == null) {
            if (other.detaulTaxPercent != null)
                return false;
        } else if (!detaulTaxPercent.equals(other.detaulTaxPercent))
            return false;
        if (mailPaymentToAddress == null) {
            if (other.mailPaymentToAddress != null)
                return false;
        } else if (!mailPaymentToAddress.equals(other.mailPaymentToAddress))
            return false;
        if (siteAddress == null) {
            if (other.siteAddress != null)
                return false;
        } else if (!siteAddress.equals(other.siteAddress))
            return false;
        if (systemAddress == null) {
            if (other.systemAddress != null)
                return false;
        } else if (!systemAddress.equals(other.systemAddress))
            return false;
        return true;
    }

}