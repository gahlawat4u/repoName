package com.gms.xms.txndb.vo;

/**
 * Posted from May 26, 2016 12:00:28 PM
 * <p>
 * Author huynd
 */
public class CheckShipmentBillingVo extends ShipmentBillingVo {

    private static final long serialVersionUID = 1L;

    private String lastChar;

    public String getLastChar() {
        return lastChar;
    }

    public void setLastChar(String lastChar) {
        this.lastChar = lastChar;
    }

    @Override
    public String toString() {
        return "CheckShipmentBillingVo [lastChar=" + lastChar + "]";
    }

}
