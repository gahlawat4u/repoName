package com.gms.xms.txndb.vo;

/**
 * Posted from May 13, 2016 8:47:47 AM
 * <p>
 * Author huynd
 */
public class WebshipAddressForMassEditVo extends AddressVo {

    private static final long serialVersionUID = 1L;

    private Integer shipmentSender;
    private Integer sbSender;
    private String companyName;

    public Integer getShipmentSender() {
        return shipmentSender;
    }

    public void setShipmentSender(Integer shipmentSender) {
        this.shipmentSender = shipmentSender;
    }

    public Integer getSbSender() {
        return sbSender;
    }

    public void setSbSender(Integer sbSender) {
        this.sbSender = sbSender;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "WebshipAddressForMassEditVo [shipmentSender=" + shipmentSender + ", sbSender=" + sbSender + ", companyName=" + companyName + "]";
    }

}
