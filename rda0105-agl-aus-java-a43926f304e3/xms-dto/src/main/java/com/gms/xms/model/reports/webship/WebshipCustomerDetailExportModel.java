package com.gms.xms.model.reports.webship;

public class WebshipCustomerDetailExportModel extends WebshipCustomerDetailModel {
    private static final long serialVersionUID = -1369507862037870859L;
    private String chargesString;
    private String surchargeString;

    public String getChargesString() {
        return chargesString;
    }

    public void setChargesString(String chargesString) {
        this.chargesString = chargesString;
    }

    public String getSurchargeString() {
        return surchargeString;
    }

    public void setSurchargeString(String surchargeString) {
        this.surchargeString = surchargeString;
    }
}