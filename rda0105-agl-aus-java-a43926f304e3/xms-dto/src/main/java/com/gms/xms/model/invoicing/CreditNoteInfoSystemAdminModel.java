package com.gms.xms.model.invoicing;

import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.BaseModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from CreditNoteInfoSystemAdminModel
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class CreditNoteInfoSystemAdminModel extends BaseModel {
    private static final long serialVersionUID = 6832824278180120620L;
    private String systemAddress;
    private String mailPaymentToAddress;
    private String siteAddress;
    private String detaulTaxPercent;

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getSystemAddress() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("\n", "<br>");
        String systemAddress = AppUtils.replaceStringByMap(map, this.systemAddress);
        return systemAddress;
    }

    public void setSystemAddress(String systemAddress) {
        this.systemAddress = systemAddress;
    }

    public String getMailPaymentToAddress() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("\n", "<br>");
        String mailPaymentToAddress = AppUtils.replaceStringByMap(map, this.mailPaymentToAddress);
        return mailPaymentToAddress;
    }

    public void setMailPaymentToAddress(String mailPaymentToAddress) {
        this.mailPaymentToAddress = mailPaymentToAddress;
    }

    public String getDetaulTaxPercent() {
        return detaulTaxPercent;
    }

    public void setDetaulTaxPercent(String detaulTaxPercent) {
        this.detaulTaxPercent = detaulTaxPercent;
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
        CreditNoteInfoSystemAdminModel other = (CreditNoteInfoSystemAdminModel) obj;
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

    @Override
    public String toString() {
        return "CreditNoteInfoSystemAdminModel [systemAddress=" + systemAddress + ", mailPaymentToAddress=" + mailPaymentToAddress + ", siteAddress=" + siteAddress + ", detaulTaxPercent=" + detaulTaxPercent + "]";
    }

}
