package com.gms.xms.txndb.model.reports.selfinsurance;

import com.gms.xms.model.BaseModel;

/**
 * Posted from May 23, 2016 11:26:17 AM
 * <p>
 * Author dattrinh
 */
public class SummaryInvoicedAirbillModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String intlShipmentCount;
    private String domShipmentCount;
    private String intlTotalRevenue;
    private String domTotalRevenue;
    private String domGst;
    private String totalRevenueIncGst;

    public String getIntlShipmentCount() {
        return intlShipmentCount;
    }

    public void setIntlShipmentCount(String intlShipmentCount) {
        this.intlShipmentCount = intlShipmentCount;
    }

    public String getDomShipmentCount() {
        return domShipmentCount;
    }

    public void setDomShipmentCount(String domShipmentCount) {
        this.domShipmentCount = domShipmentCount;
    }

    public String getIntlTotalRevenue() {
        return intlTotalRevenue;
    }

    public void setIntlTotalRevenue(String intlTotalRevenue) {
        this.intlTotalRevenue = intlTotalRevenue;
    }

    public String getDomTotalRevenue() {
        return domTotalRevenue;
    }

    public void setDomTotalRevenue(String domTotalRevenue) {
        this.domTotalRevenue = domTotalRevenue;
    }

    public String getDomGst() {
        return domGst;
    }

    public void setDomGst(String domGst) {
        this.domGst = domGst;
    }

    public String getTotalRevenueIncGst() {
        return totalRevenueIncGst;
    }

    public void setTotalRevenueIncGst(String totalRevenueIncGst) {
        this.totalRevenueIncGst = totalRevenueIncGst;
    }

    @Override
    public String toString() {
        return "SummaryInvoicedAirbillModel [intlShipmentCount=" + intlShipmentCount + ", domShipmentCount=" + domShipmentCount + ", intlTotalRevenue=" + intlTotalRevenue + ", domTotalRevenue=" + domTotalRevenue + ", domGst=" + domGst + ", totalRevenueIncGst=" + totalRevenueIncGst + "]";
    }
}
