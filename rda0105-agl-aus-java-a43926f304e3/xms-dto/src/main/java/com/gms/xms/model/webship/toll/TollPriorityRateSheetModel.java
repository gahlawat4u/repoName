package com.gms.xms.model.webship.toll;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Sep 1, 2016 5:00:03 PM
 * <p>
 * Author dattrinh
 */
public class TollPriorityRateSheetModel extends BaseModel {

    private static final long serialVersionUID = 1L;

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
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getZoneFrom() {
        return zoneFrom;
    }

    public void setZoneFrom(String zoneFrom) {
        this.zoneFrom = zoneFrom;
    }

    public String getZoneTo() {
        return zoneTo;
    }

    public void setZoneTo(String zoneTo) {
        this.zoneTo = zoneTo;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(String minCharge) {
        this.minCharge = minCharge;
    }

    public String getConRate() {
        return conRate;
    }

    public void setConRate(String conRate) {
        this.conRate = conRate;
    }

    public String getKgsIncluded() {
        return kgsIncluded;
    }

    public void setKgsIncluded(String kgsIncluded) {
        this.kgsIncluded = kgsIncluded;
    }

    public String getKgsRate() {
        return kgsRate;
    }

    public void setKgsRate(String kgsRate) {
        this.kgsRate = kgsRate;
    }

    public String getGstPct() {
        return gstPct;
    }

    public void setGstPct(String gstPct) {
        this.gstPct = gstPct;
    }

    public String getCubicConv() {
        return cubicConv;
    }

    public void setCubicConv(String cubicConv) {
        this.cubicConv = cubicConv;
    }

    public String getSurchargePct() {
        return surchargePct;
    }

    public void setSurchargePct(String surchargePct) {
        this.surchargePct = surchargePct;
    }

    @Override
    public String toString() {
        return "TollPriorityRateSheetModel [customer=" + customer + ", product=" + product + ", service=" + service + ", zoneFrom=" + zoneFrom + ", zoneTo=" + zoneTo + ", priceType=" + priceType + ", minCharge=" + minCharge + ", conRate=" + conRate + ", kgsIncluded=" + kgsIncluded + ", kgsRate=" + kgsRate + ", gstPct=" + gstPct + ", cubicConv=" + cubicConv + ", surchargePct=" + surchargePct + "]";
    }
}