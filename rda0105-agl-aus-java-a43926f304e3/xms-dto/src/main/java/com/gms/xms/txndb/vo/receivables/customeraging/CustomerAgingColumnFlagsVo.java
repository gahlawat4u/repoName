package com.gms.xms.txndb.vo.receivables.customeraging;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from CustomerAgingColumnFlagsVo
 * <p>
 * Author HungNT Date Aug 17, 2015
 */
public class CustomerAgingColumnFlagsVo extends BaseVo {
    private static final long serialVersionUID = -4388719648924424889L;

    private Boolean hasAgingBuckets;
    private Boolean hasInvoiceAge;
    private Boolean hasDaysOverdue;
    private Boolean hasInvoice;
    private Boolean hasSalesRep;
    private Boolean hasTotalOverpayments;
    private Boolean hasTerms;
    private Boolean hasAvgDaysToPay;

    public Boolean getHasAgingBuckets() {
        return hasAgingBuckets;
    }

    public void setHasAgingBuckets(Boolean hasAgingBuckets) {
        this.hasAgingBuckets = hasAgingBuckets;
    }

    public Boolean getHasInvoiceAge() {
        return hasInvoiceAge;
    }

    public void setHasInvoiceAge(Boolean hasInvoiceAge) {
        this.hasInvoiceAge = hasInvoiceAge;
    }

    public Boolean getHasDaysOverdue() {
        return hasDaysOverdue;
    }

    public void setHasDaysOverdue(Boolean hasDaysOverdue) {
        this.hasDaysOverdue = hasDaysOverdue;
    }

    public Boolean getHasInvoice() {
        return hasInvoice;
    }

    public void setHasInvoice(Boolean hasInvoice) {
        this.hasInvoice = hasInvoice;
    }

    public Boolean getHasSalesRep() {
        return hasSalesRep;
    }

    public void setHasSalesRep(Boolean hasSalesRep) {
        this.hasSalesRep = hasSalesRep;
    }

    public Boolean getHasTotalOverpayments() {
        return hasTotalOverpayments;
    }

    public void setHasTotalOverpayments(Boolean hasTotalOverpayments) {
        this.hasTotalOverpayments = hasTotalOverpayments;
    }

    public Boolean getHasTerms() {
        return hasTerms;
    }

    public void setHasTerms(Boolean hasTerms) {
        this.hasTerms = hasTerms;
    }

    public Boolean getHasAvgDaysToPay() {
        return hasAvgDaysToPay;
    }

    public void setHasAvgDaysToPay(Boolean hasAvgDaysToPay) {
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
        CustomerAgingColumnFlagsVo other = (CustomerAgingColumnFlagsVo) obj;
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
        return "CustomerAgingColumnFlagsVo [hasAgingBuckets=" + hasAgingBuckets + ", hasInvoiceAge=" + hasInvoiceAge + ", hasDaysOverdue=" + hasDaysOverdue + ", hasInvoice=" + hasInvoice + ", hasSalesRep=" + hasSalesRep + ", hasTotalOverpayments=" + hasTotalOverpayments + ", hasTerms=" + hasTerms + ", hasAvgDaysToPay=" + hasAvgDaysToPay + "]";
    }
}
