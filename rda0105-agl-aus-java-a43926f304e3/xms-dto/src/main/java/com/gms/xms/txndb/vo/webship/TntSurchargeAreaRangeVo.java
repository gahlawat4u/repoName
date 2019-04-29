package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

public class TntSurchargeAreaRangeVo extends BaseVo {
    private static final long serialVersionUID = -6594740830465428965L;

    private Integer id;

    private String country;

    private String city;

    private String state;

    private String postalCode;

    private Integer postalCode1;

    private Integer postalCode2;

    private String shortCity;

    private String shortState;

    private String carrierId;

    private String serviceId;

    private String packageId;

    private String chargeCode;

    private String configChargeId;

    private Boolean isRange;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    public Integer getPostalCode1() {
        return postalCode1;
    }

    public void setPostalCode1(Integer postalCode1) {
        this.postalCode1 = postalCode1;
    }

    public Integer getPostalCode2() {
        return postalCode2;
    }

    public void setPostalCode2(Integer postalCode2) {
        this.postalCode2 = postalCode2;
    }

    public String getShortCity() {
        return shortCity;
    }

    public void setShortCity(String shortCity) {
        this.shortCity = shortCity == null ? null : shortCity.trim();
    }

    public String getShortState() {
        return shortState;
    }

    public void setShortState(String shortState) {
        this.shortState = shortState == null ? null : shortState.trim();
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId == null ? null : carrierId.trim();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId == null ? null : packageId.trim();
    }

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode == null ? null : chargeCode.trim();
    }

    public String getConfigChargeId() {
        return configChargeId;
    }

    public void setConfigChargeId(String configChargeId) {
        this.configChargeId = configChargeId == null ? null : configChargeId.trim();
    }

    public Boolean getIsRange() {
        return isRange;
    }

    public void setIsRange(Boolean isRange) {
        this.isRange = isRange;
    }

    @Override
    public String toString() {
        return "TntSurchargeAreaRangeVo [id=" + id + ", country=" + country + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode + ", postalCode1=" + postalCode1 + ", postalCode2=" + postalCode2 + ", shortCity=" + shortCity + ", shortState=" + shortState + ", carrierId=" + carrierId + ", serviceId=" + serviceId + ", packageId=" + packageId + ", chargeCode=" + chargeCode + ", configChargeId=" + configChargeId + ", isRange=" + isRange + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carrierId == null) ? 0 : carrierId.hashCode());
        result = prime * result + ((chargeCode == null) ? 0 : chargeCode.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((configChargeId == null) ? 0 : configChargeId.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isRange == null) ? 0 : isRange.hashCode());
        result = prime * result + ((packageId == null) ? 0 : packageId.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((postalCode1 == null) ? 0 : postalCode1.hashCode());
        result = prime * result + ((postalCode2 == null) ? 0 : postalCode2.hashCode());
        result = prime * result + ((serviceId == null) ? 0 : serviceId.hashCode());
        result = prime * result + ((shortCity == null) ? 0 : shortCity.hashCode());
        result = prime * result + ((shortState == null) ? 0 : shortState.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
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
        TntSurchargeAreaRangeVo other = (TntSurchargeAreaRangeVo) obj;
        if (carrierId == null) {
            if (other.carrierId != null)
                return false;
        } else if (!carrierId.equals(other.carrierId))
            return false;
        if (chargeCode == null) {
            if (other.chargeCode != null)
                return false;
        } else if (!chargeCode.equals(other.chargeCode))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (configChargeId == null) {
            if (other.configChargeId != null)
                return false;
        } else if (!configChargeId.equals(other.configChargeId))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isRange == null) {
            if (other.isRange != null)
                return false;
        } else if (!isRange.equals(other.isRange))
            return false;
        if (packageId == null) {
            if (other.packageId != null)
                return false;
        } else if (!packageId.equals(other.packageId))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (postalCode1 == null) {
            if (other.postalCode1 != null)
                return false;
        } else if (!postalCode1.equals(other.postalCode1))
            return false;
        if (postalCode2 == null) {
            if (other.postalCode2 != null)
                return false;
        } else if (!postalCode2.equals(other.postalCode2))
            return false;
        if (serviceId == null) {
            if (other.serviceId != null)
                return false;
        } else if (!serviceId.equals(other.serviceId))
            return false;
        if (shortCity == null) {
            if (other.shortCity != null)
                return false;
        } else if (!shortCity.equals(other.shortCity))
            return false;
        if (shortState == null) {
            if (other.shortState != null)
                return false;
        } else if (!shortState.equals(other.shortState))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
    }
}