package com.gms.xms.txndb.vo.account.franchises;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from FranchiseListColumnFlagsVo
 * </p>
 *
 * @author hungnt - Nov 11, 2015
 */
public class FranchiseListColumnFlagsVo extends BaseVo {
    private static final long serialVersionUID = -8414846052023408677L;

    private Boolean hasAddress;
    private Boolean hasBilling;
    private Boolean hasDhl;
    private Boolean hasDates;
    private Boolean hasInvoicingOptions;

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

    public Boolean getHasDhl() {
        return hasDhl;
    }

    public void setHasDhl(Boolean hasDhl) {
        this.hasDhl = hasDhl;
    }

    public Boolean getHasDates() {
        return hasDates;
    }

    public void setHasDates(Boolean hasDates) {
        this.hasDates = hasDates;
    }

    public Boolean getHasInvoicingOptions() {
        return hasInvoicingOptions;
    }

    public void setHasInvoicingOptions(Boolean hasInvoicingOptions) {
        this.hasInvoicingOptions = hasInvoicingOptions;
    }

    @Override
    public String toString() {
        return "FranchiseListColumnFlagsVo [hasAddress=" + hasAddress + ", hasBilling=" + hasBilling + ", hasDhl=" + hasDhl + ", hasDates=" + hasDates + ", hasInvoicingOptions=" + hasInvoicingOptions + "]";
    }
}
