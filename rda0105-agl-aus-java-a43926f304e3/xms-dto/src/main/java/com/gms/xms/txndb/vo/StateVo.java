package com.gms.xms.txndb.vo;

public class StateVo extends BaseVo {
    private static final long serialVersionUID = 8960295130144683160L;

    private Long id;

    private String stateName;

    private String stateCode;

    private String cityName;

    private String cityCode;

    private Long countryId;

    private String dhlZone;

    private String fromPostCode;

    private String toPostCode;

    private Byte status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getDhlZone() {
        return dhlZone;
    }

    public void setDhlZone(String dhlZone) {
        this.dhlZone = dhlZone;
    }

    public String getFromPostCode() {
        return fromPostCode;
    }

    public void setFromPostCode(String fromPostCode) {
        this.fromPostCode = fromPostCode;
    }

    public String getToPostCode() {
        return toPostCode;
    }

    public void setToPostCode(String toPostCode) {
        this.toPostCode = toPostCode;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StateVo [id=" + id + ", stateName=" + stateName + ", stateCode=" + stateCode + ", cityName=" + cityName + ", cityCode=" + cityCode + ", countryId=" + countryId + ", dhlZone=" + dhlZone + ", fromPostCode=" + fromPostCode + ", toPostCode=" + toPostCode + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cityCode == null) ? 0 : cityCode.hashCode());
        result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
        result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
        result = prime * result + ((dhlZone == null) ? 0 : dhlZone.hashCode());
        result = prime * result + ((fromPostCode == null) ? 0 : fromPostCode.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
        result = prime * result + ((stateName == null) ? 0 : stateName.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((toPostCode == null) ? 0 : toPostCode.hashCode());
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
        StateVo other = (StateVo) obj;
        if (cityCode == null) {
            if (other.cityCode != null)
                return false;
        } else if (!cityCode.equals(other.cityCode))
            return false;
        if (cityName == null) {
            if (other.cityName != null)
                return false;
        } else if (!cityName.equals(other.cityName))
            return false;
        if (countryId == null) {
            if (other.countryId != null)
                return false;
        } else if (!countryId.equals(other.countryId))
            return false;
        if (dhlZone == null) {
            if (other.dhlZone != null)
                return false;
        } else if (!dhlZone.equals(other.dhlZone))
            return false;
        if (fromPostCode == null) {
            if (other.fromPostCode != null)
                return false;
        } else if (!fromPostCode.equals(other.fromPostCode))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (stateCode == null) {
            if (other.stateCode != null)
                return false;
        } else if (!stateCode.equals(other.stateCode))
            return false;
        if (stateName == null) {
            if (other.stateName != null)
                return false;
        } else if (!stateName.equals(other.stateName))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (toPostCode == null) {
            if (other.toPostCode != null)
                return false;
        } else if (!toPostCode.equals(other.toPostCode))
            return false;
        return true;
    }
}