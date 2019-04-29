package com.gms.xms.txndb.vo;


/**
 * @author tkvcl
 */
public class TollIpecPostcodeVo extends BaseVo {


    /**
     *
     */
    private static final long serialVersionUID = 3630301412154904506L;

    private String postalCode;

    private String town;

    private Double amount;

    private String zone;

    private String state;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}