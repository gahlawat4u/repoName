package com.gms.xms.model.account.customers;

import com.gms.xms.model.BaseModel;

/**
 * Posted from SearchCustomerColumnFlagsVo
 * </p>
 *
 * @author hungnt - Nov 9, 2015
 */
public class SearchCustomerColumnFlagsModel extends BaseModel {
    private static final long serialVersionUID = -135891762864239740L;
    private String hasAddress;
    private String hasBilling;
    private String hasDhlAccounts;
    private String hasInvoicingOptions;
    private String hasDates;

    public String getHasAddress() {
        return hasAddress;
    }

    public void setHasAddress(String hasAddress) {
        this.hasAddress = hasAddress;
    }

    public String getHasBilling() {
        return hasBilling;
    }

    public void setHasBilling(String hasBilling) {
        this.hasBilling = hasBilling;
    }

    public String getHasDhlAccounts() {
        return hasDhlAccounts;
    }

    public void setHasDhlAccounts(String hasDhlAccounts) {
        this.hasDhlAccounts = hasDhlAccounts;
    }

    public String getHasInvoicingOptions() {
        return hasInvoicingOptions;
    }

    public void setHasInvoicingOptions(String hasInvoicingOptions) {
        this.hasInvoicingOptions = hasInvoicingOptions;
    }

    public String getHasDates() {
        return hasDates;
    }

    public void setHasDates(String hasDates) {
        this.hasDates = hasDates;
    }

    @Override
    public String toString() {
        return "SearchCustomerColumnFlagsModel [hasAddress=" + hasAddress + ", hasBilling=" + hasBilling + ", hasDhlAccounts=" + hasDhlAccounts + ", hasInvoicingOptions=" + hasInvoicingOptions + ", hasDates=" + hasDates + "]";
    }
}
