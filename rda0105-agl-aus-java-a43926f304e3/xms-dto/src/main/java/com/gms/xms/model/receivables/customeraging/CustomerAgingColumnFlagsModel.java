package com.gms.xms.model.receivables.customeraging;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustomerAgingColumnFlagsModel
 * <p>
 * Author HungNT Date Aug 18, 2015
 */
public class CustomerAgingColumnFlagsModel extends BaseModel {
    private static final long serialVersionUID = 22583828988848917L;

    private String hasAgingBuckets;
    private String hasInvoiceAge;
    private String hasDaysOverdue;
    private String hasInvoice;
    private String hasSalesRep;
    private String hasTotalOverpayments;
    private String hasTerms;
    private String hasAvgDaysToPay;

    public String getHasAgingBuckets() {
        return hasAgingBuckets;
    }

    public void setHasAgingBuckets(String hasAgingBuckets) {
        this.hasAgingBuckets = hasAgingBuckets;
    }

    public String getHasInvoiceAge() {
        return hasInvoiceAge;
    }

    public void setHasInvoiceAge(String hasInvoiceAge) {
        this.hasInvoiceAge = hasInvoiceAge;
    }

    public String getHasDaysOverdue() {
        return hasDaysOverdue;
    }

    public void setHasDaysOverdue(String hasDaysOverdue) {
        this.hasDaysOverdue = hasDaysOverdue;
    }

    public String getHasInvoice() {
        return hasInvoice;
    }

    public void setHasInvoice(String hasInvoice) {
        this.hasInvoice = hasInvoice;
    }

    public String getHasSalesRep() {
        return hasSalesRep;
    }

    public void setHasSalesRep(String hasSalesRep) {
        this.hasSalesRep = hasSalesRep;
    }

    public String getHasTotalOverpayments() {
        return hasTotalOverpayments;
    }

    public void setHasTotalOverpayments(String hasTotalOverpayments) {
        this.hasTotalOverpayments = hasTotalOverpayments;
    }

    public String getHasTerms() {
        return hasTerms;
    }

    public void setHasTerms(String hasTerms) {
        this.hasTerms = hasTerms;
    }

    public String getHasAvgDaysToPay() {
        return hasAvgDaysToPay;
    }

    public void setHasAvgDaysToPay(String hasAvgDaysToPay) {
        this.hasAvgDaysToPay = hasAvgDaysToPay;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hasAgingBuckets == null) ? 0 : hasAgingBuckets.hashCode());
        result = prime * result + ((hasAvgDaysToPay == null) ? 0 : hasAvgDaysToPay.hashCode());
        result = prime * result + ((hasDaysOverdue == null) ? 0 : hasDaysOverdue.hashCode());
        result = prime * result + ((hasInvoice == null) ? 0 : hasInvoice.hashCode());
        result = prime * result + ((hasInvoiceAge == null) ? 0 : hasInvoiceAge.hashCode());
        result = prime * result + ((hasSalesRep == null) ? 0 : hasSalesRep.hashCode());
        result = prime * result + ((hasTerms == null) ? 0 : hasTerms.hashCode());
        result = prime * result + ((hasTotalOverpayments == null) ? 0 : hasTotalOverpayments.hashCode());
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
        CustomerAgingColumnFlagsModel other = (CustomerAgingColumnFlagsModel) obj;
        if (hasAgingBuckets == null) {
            if (other.hasAgingBuckets != null)
                return false;
        } else if (!hasAgingBuckets.equals(other.hasAgingBuckets))
            return false;
        if (hasAvgDaysToPay == null) {
            if (other.hasAvgDaysToPay != null)
                return false;
        } else if (!hasAvgDaysToPay.equals(other.hasAvgDaysToPay))
            return false;
        if (hasDaysOverdue == null) {
            if (other.hasDaysOverdue != null)
                return false;
        } else if (!hasDaysOverdue.equals(other.hasDaysOverdue))
            return false;
        if (hasInvoice == null) {
            if (other.hasInvoice != null)
                return false;
        } else if (!hasInvoice.equals(other.hasInvoice))
            return false;
        if (hasInvoiceAge == null) {
            if (other.hasInvoiceAge != null)
                return false;
        } else if (!hasInvoiceAge.equals(other.hasInvoiceAge))
            return false;
        if (hasSalesRep == null) {
            if (other.hasSalesRep != null)
                return false;
        } else if (!hasSalesRep.equals(other.hasSalesRep))
            return false;
        if (hasTerms == null) {
            if (other.hasTerms != null)
                return false;
        } else if (!hasTerms.equals(other.hasTerms))
            return false;
        if (hasTotalOverpayments == null) {
            if (other.hasTotalOverpayments != null)
                return false;
        } else if (!hasTotalOverpayments.equals(other.hasTotalOverpayments))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CustomerAgingColumnFlagsModel [hasAgingBuckets=" + hasAgingBuckets + ", hasInvoiceAge=" + hasInvoiceAge + ", hasDaysOverdue=" + hasDaysOverdue + ", hasInvoice=" + hasInvoice + ", hasSalesRep=" + hasSalesRep + ", hasTotalOverpayments=" + hasTotalOverpayments + ", hasTerms=" + hasTerms + ", hasAvgDaysToPay=" + hasAvgDaysToPay + "]";
    }
}
