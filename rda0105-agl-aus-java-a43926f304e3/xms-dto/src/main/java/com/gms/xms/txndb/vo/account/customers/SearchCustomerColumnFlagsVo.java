package com.gms.xms.txndb.vo.account.customers;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from SearchCustomerColumnFlagsVo
 * </p>
 *
 * @author hungnt - Nov 9, 2015
 */
public class SearchCustomerColumnFlagsVo extends BaseVo {
    private static final long serialVersionUID = -2999502126860271413L;

    private Boolean hasAddress;
    private Boolean hasBilling;
    private Boolean hasDhlAccounts;
    private Boolean hasInvoicingOptions;
    private Boolean hasDates;

    public Boolean getHasAddress() {
        return hasAddress;
    }

    public void setHasAddress(Boolean hasAddress) {
        this.hasAddress = hasAddress;
    }

    public Boolean getHasBilling() {
        return hasBilling;
    }

    public void setHasBilling(Boolean hasBilling) {
        this.hasBilling = hasBilling;
    }

    public Boolean getHasDhlAccounts() {
        return hasDhlAccounts;
    }

    public void setHasDhlAccounts(Boolean hasDhlAccounts) {
        this.hasDhlAccounts = hasDhlAccounts;
    }

    public Boolean getHasInvoicingOptions() {
        return hasInvoicingOptions;
    }

    public void setHasInvoicingOptions(Boolean hasInvoicingOptions) {
        this.hasInvoicingOptions = hasInvoicingOptions;
    }

    public Boolean getHasDates() {
        return hasDates;
    }

    public void setHasDates(Boolean hasDates) {
        this.hasDates = hasDates;
    }

    @Override
    public String toString() {
        return "SearchCustomerColumnFlagsVo [hasAddress=" + hasAddress + ", hasBilling=" + hasBilling + ", hasDhlAccounts=" + hasDhlAccounts + ", hasInvoicingOptions=" + hasInvoicingOptions + ", hasDates=" + hasDates + "]";
    }
}
