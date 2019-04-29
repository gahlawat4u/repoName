package com.gms.xms.txndb.vo.admin.administration;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from CustomerProfileBaseRateDetailModel
 * <p>
 * Author TANDT 29-10-2015
 */
public class CustomerProfileBaseRateDetailVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private Long customerProfileBaseRateId;
    private String zone;
    private Double rate;

    public Long getCustomerProfileBaseRateId() {
        return customerProfileBaseRateId;
    }

    public void setCustomerProfileBaseRateId(Long customerProfileBaseRateId) {
        this.customerProfileBaseRateId = customerProfileBaseRateId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CustomerProfileBaseRateDetailVo [customerProfileBaseRateId=" + customerProfileBaseRateId + ", zone=" + zone + ", rate=" + rate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerProfileBaseRateId == null) ? 0 : customerProfileBaseRateId.hashCode());
        result = prime * result + ((rate == null) ? 0 : rate.hashCode());
        result = prime * result + ((zone == null) ? 0 : zone.hashCode());
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
        CustomerProfileBaseRateDetailVo other = (CustomerProfileBaseRateDetailVo) obj;
        if (customerProfileBaseRateId == null) {
            if (other.customerProfileBaseRateId != null)
                return false;
        } else if (!customerProfileBaseRateId.equals(other.customerProfileBaseRateId))
            return false;
        if (rate == null) {
            if (other.rate != null)
                return false;
        } else if (!rate.equals(other.rate))
            return false;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        return true;
    }

}
