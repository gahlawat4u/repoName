
package com.gms.delivery.ups.service.rest.shipment.pojo.response;


public class TotalCharge {

    private String currencyCode;
    private String monetaryValue;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetaryValue(String monetaryValue) {
        this.monetaryValue = monetaryValue;
    }

}
