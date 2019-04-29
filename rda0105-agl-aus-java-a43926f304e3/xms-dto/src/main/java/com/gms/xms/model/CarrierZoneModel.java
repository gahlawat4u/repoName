package com.gms.xms.model;

/**
 * File Name: CarrierZoneModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 30-11-2015 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model <br/>
 * Class: CarrierZoneModel
 */
public class CarrierZoneModel extends BaseModel {

    private static final long serialVersionUID = 4788811006427730448L;

    private String zoneCode;

    private String zoneName;

    private String carrier;

    private String stateCode;

    private String zoneNo;

    private String isDisplay;

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getZoneNo() {
        return zoneNo;
    }

    public void setZoneNo(String zoneNo) {
        this.zoneNo = zoneNo;
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    @Override
    public String toString() {
        return "CarrierZoneModel [zoneCode=" + zoneCode + ", zoneName=" + zoneName + ", carrier=" + carrier + ", stateCode=" + stateCode + ", zoneNo=" + zoneNo + ", isDisplay=" + isDisplay + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
        result = prime * result + ((isDisplay == null) ? 0 : isDisplay.hashCode());
        result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
        result = prime * result + ((zoneCode == null) ? 0 : zoneCode.hashCode());
        result = prime * result + ((zoneName == null) ? 0 : zoneName.hashCode());
        result = prime * result + ((zoneNo == null) ? 0 : zoneNo.hashCode());
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
        CarrierZoneModel other = (CarrierZoneModel) obj;
        if (carrier == null) {
            if (other.carrier != null)
                return false;
        } else if (!carrier.equals(other.carrier))
            return false;
        if (isDisplay == null) {
            if (other.isDisplay != null)
                return false;
        } else if (!isDisplay.equals(other.isDisplay))
            return false;
        if (stateCode == null) {
            if (other.stateCode != null)
                return false;
        } else if (!stateCode.equals(other.stateCode))
            return false;
        if (zoneCode == null) {
            if (other.zoneCode != null)
                return false;
        } else if (!zoneCode.equals(other.zoneCode))
            return false;
        if (zoneName == null) {
            if (other.zoneName != null)
                return false;
        } else if (!zoneName.equals(other.zoneName))
            return false;
        if (zoneNo == null) {
            if (other.zoneNo != null)
                return false;
        } else if (!zoneNo.equals(other.zoneNo))
            return false;
        return true;
    }

}