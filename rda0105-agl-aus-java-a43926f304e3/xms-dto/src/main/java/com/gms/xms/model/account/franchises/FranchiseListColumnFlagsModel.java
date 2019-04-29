package com.gms.xms.model.account.franchises;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchiseListColumnFlagsModel
 * </p>
 *
 * @author hungnt - Nov 11, 2015
 */
public class FranchiseListColumnFlagsModel extends BaseModel {
    private static final long serialVersionUID = 7461937376174556803L;

    private String hasAddress;
    private String hasBilling;
    private String hasDhl;
    private String hasDates;
    private String hasInvoicingOptions;

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

    public String getHasDhl() {
        return hasDhl;
    }

    public void setHasDhl(String hasDhl) {
        this.hasDhl = hasDhl;
    }

    public String getHasDates() {
        return hasDates;
    }

    public void setHasDates(String hasDates) {
        this.hasDates = hasDates;
    }

    public String getHasInvoicingOptions() {
        return hasInvoicingOptions;
    }

    public void setHasInvoicingOptions(String hasInvoicingOptions) {
        this.hasInvoicingOptions = hasInvoicingOptions;
    }

    @Override
    public String toString() {
        return "FranchiseListColumnFlagsModel [hasAddress=" + hasAddress + ", hasBilling=" + hasBilling + ", hasDhl=" + hasDhl + ", hasDates=" + hasDates + ", hasInvoicingOptions=" + hasInvoicingOptions + "]";
    }
}
