package com.gms.xms.txndb.vo;

/**
 * Posted from CustomerBaseRateFilter
 * <p>
 * Author HungNT Date Apr 22, 2015
 */
public class CustomerBaseRateFilter extends CustomerBaseRateVo {
    private static final long serialVersionUID = -5611783543909569765L;

    private String zone;

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "CustomerBaseRateFilter [zone=" + zone + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((zone == null) ? 0 : zone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerBaseRateFilter other = (CustomerBaseRateFilter) obj;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        return true;
    }
}
