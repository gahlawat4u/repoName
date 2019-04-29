package com.gms.xms.txndb.vo.webship.toll;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from TollPriorityRateSheetVo
 * <p>
 * Author HungNT Date Aug 26, 2015
 */
public class TollPriorityRateSheetVo extends BaseVo {
    private static final long serialVersionUID = 2873566025425209681L;

    private String customer;

    private String product;

    private String service;

    private String zoneFrom;

    private String zoneTo;

    private String priceType;

    private String minCharge;

    private String conRate;

    private String kgsIncluded;

    private String kgsRate;

    private String gstPct;

    private String cubicConv;

    private String surchargePct;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service == null ? null : service.trim();
    }

    public String getZoneFrom() {
        return zoneFrom;
    }

    public void setZoneFrom(String zoneFrom) {
        this.zoneFrom = zoneFrom == null ? null : zoneFrom.trim();
    }

    public String getZoneTo() {
        return zoneTo;
    }

    public void setZoneTo(String zoneTo) {
        this.zoneTo = zoneTo == null ? null : zoneTo.trim();
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType == null ? null : priceType.trim();
    }

    public String getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(String minCharge) {
        this.minCharge = minCharge == null ? null : minCharge.trim();
    }

    public String getConRate() {
        return conRate;
    }

    public void setConRate(String conRate) {
        this.conRate = conRate == null ? null : conRate.trim();
    }

    public String getKgsIncluded() {
        return kgsIncluded;
    }

    public void setKgsIncluded(String kgsIncluded) {
        this.kgsIncluded = kgsIncluded == null ? null : kgsIncluded.trim();
    }

    public String getKgsRate() {
        return kgsRate;
    }

    public void setKgsRate(String kgsRate) {
        this.kgsRate = kgsRate == null ? null : kgsRate.trim();
    }

    public String getGstPct() {
        return gstPct;
    }

    public void setGstPct(String gstPct) {
        this.gstPct = gstPct == null ? null : gstPct.trim();
    }

    public String getCubicConv() {
        return cubicConv;
    }

    public void setCubicConv(String cubicConv) {
        this.cubicConv = cubicConv == null ? null : cubicConv.trim();
    }

    public String getSurchargePct() {
        return surchargePct;
    }

    public void setSurchargePct(String surchargePct) {
        this.surchargePct = surchargePct == null ? null : surchargePct.trim();
    }

    @Override
    public String toString() {
        return "TollPriorityRateSheetVo [customer=" + customer + ", product=" + product + ", service=" + service + ", zoneFrom=" + zoneFrom + ", zoneTo=" + zoneTo + ", priceType=" + priceType + ", minCharge=" + minCharge + ", conRate=" + conRate + ", kgsIncluded=" + kgsIncluded + ", kgsRate=" + kgsRate + ", gstPct=" + gstPct + ", cubicConv=" + cubicConv + ", surchargePct=" + surchargePct + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((conRate == null) ? 0 : conRate.hashCode());
        result = prime * result + ((cubicConv == null) ? 0 : cubicConv.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((gstPct == null) ? 0 : gstPct.hashCode());
        result = prime * result + ((kgsIncluded == null) ? 0 : kgsIncluded.hashCode());
        result = prime * result + ((kgsRate == null) ? 0 : kgsRate.hashCode());
        result = prime * result + ((minCharge == null) ? 0 : minCharge.hashCode());
        result = prime * result + ((priceType == null) ? 0 : priceType.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((service == null) ? 0 : service.hashCode());
        result = prime * result + ((surchargePct == null) ? 0 : surchargePct.hashCode());
        result = prime * result + ((zoneFrom == null) ? 0 : zoneFrom.hashCode());
        result = prime * result + ((zoneTo == null) ? 0 : zoneTo.hashCode());
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
        TollPriorityRateSheetVo other = (TollPriorityRateSheetVo) obj;
        if (conRate == null) {
            if (other.conRate != null)
                return false;
        } else if (!conRate.equals(other.conRate))
            return false;
        if (cubicConv == null) {
            if (other.cubicConv != null)
                return false;
        } else if (!cubicConv.equals(other.cubicConv))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (gstPct == null) {
            if (other.gstPct != null)
                return false;
        } else if (!gstPct.equals(other.gstPct))
            return false;
        if (kgsIncluded == null) {
            if (other.kgsIncluded != null)
                return false;
        } else if (!kgsIncluded.equals(other.kgsIncluded))
            return false;
        if (kgsRate == null) {
            if (other.kgsRate != null)
                return false;
        } else if (!kgsRate.equals(other.kgsRate))
            return false;
        if (minCharge == null) {
            if (other.minCharge != null)
                return false;
        } else if (!minCharge.equals(other.minCharge))
            return false;
        if (priceType == null) {
            if (other.priceType != null)
                return false;
        } else if (!priceType.equals(other.priceType))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (service == null) {
            if (other.service != null)
                return false;
        } else if (!service.equals(other.service))
            return false;
        if (surchargePct == null) {
            if (other.surchargePct != null)
                return false;
        } else if (!surchargePct.equals(other.surchargePct))
            return false;
        if (zoneFrom == null) {
            if (other.zoneFrom != null)
                return false;
        } else if (!zoneFrom.equals(other.zoneFrom))
            return false;
        if (zoneTo == null) {
            if (other.zoneTo != null)
                return false;
        } else if (!zoneTo.equals(other.zoneTo))
            return false;
        return true;
    }
}