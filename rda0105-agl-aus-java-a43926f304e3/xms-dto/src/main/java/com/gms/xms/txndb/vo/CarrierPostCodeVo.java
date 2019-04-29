package com.gms.xms.txndb.vo;

public class CarrierPostCodeVo extends BaseVo {
    private static final long serialVersionUID = 808855962820873438L;

    private String zoneCode;

    private String postCode;

    private Long carrier;

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode == null ? null : zoneCode.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public Long getCarrier() {
        return carrier;
    }

    public void setCarrier(Long carrier) {
        this.carrier = carrier;
    }

    @Override
    public String toString() {
        return "CarrierPostCodeVo [zoneCode=" + zoneCode + ", postCode=" + postCode + ", carrier=" + carrier + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
        result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
        result = prime * result + ((zoneCode == null) ? 0 : zoneCode.hashCode());
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
        CarrierPostCodeVo other = (CarrierPostCodeVo) obj;
        if (carrier == null) {
            if (other.carrier != null)
                return false;
        } else if (!carrier.equals(other.carrier))
            return false;
        if (postCode == null) {
            if (other.postCode != null)
                return false;
        } else if (!postCode.equals(other.postCode))
            return false;
        if (zoneCode == null) {
            if (other.zoneCode != null)
                return false;
        } else if (!zoneCode.equals(other.zoneCode))
            return false;
        return true;
    }
}