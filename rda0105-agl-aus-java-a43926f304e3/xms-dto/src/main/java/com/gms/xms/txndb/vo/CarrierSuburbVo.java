package com.gms.xms.txndb.vo;

/**
 * Posted from CarrierSuburbVo
 * <p>
 * Author HungNT Date Apr 21, 2015
 */
public class CarrierSuburbVo extends BaseVo {
    private static final long serialVersionUID = 8223662407369529612L;

    private String suburbName;

    private String stateCode;

    private String postCode;

    private Long carrier;

    private String primaryPort;

    private String secondaryPort;

    private String countryFlag;

    public String getSuburbName() {
        return suburbName;
    }

    public void setSuburbName(String suburbName) {
        this.suburbName = suburbName == null ? null : suburbName.trim();
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode == null ? null : stateCode.trim();
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

    public String getPrimaryPort() {
        return primaryPort;
    }

    public void setPrimaryPort(String primaryPort) {
        this.primaryPort = primaryPort == null ? null : primaryPort.trim();
    }

    public String getSecondaryPort() {
        return secondaryPort;
    }

    public void setSecondaryPort(String secondaryPort) {
        this.secondaryPort = secondaryPort == null ? null : secondaryPort.trim();
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag == null ? null : countryFlag.trim();
    }

    @Override
    public String toString() {
        return "CarrierSuburbVo [suburbName=" + suburbName + ", stateCode=" + stateCode + ", postCode=" + postCode + ", carrier=" + carrier + ", primaryPort=" + primaryPort + ", secondaryPort=" + secondaryPort + ", countryFlag=" + countryFlag + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
        result = prime * result + ((countryFlag == null) ? 0 : countryFlag.hashCode());
        result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
        result = prime * result + ((primaryPort == null) ? 0 : primaryPort.hashCode());
        result = prime * result + ((secondaryPort == null) ? 0 : secondaryPort.hashCode());
        result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
        result = prime * result + ((suburbName == null) ? 0 : suburbName.hashCode());
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
        CarrierSuburbVo other = (CarrierSuburbVo) obj;
        if (carrier == null) {
            if (other.carrier != null)
                return false;
        } else if (!carrier.equals(other.carrier))
            return false;
        if (countryFlag == null) {
            if (other.countryFlag != null)
                return false;
        } else if (!countryFlag.equals(other.countryFlag))
            return false;
        if (postCode == null) {
            if (other.postCode != null)
                return false;
        } else if (!postCode.equals(other.postCode))
            return false;
        if (primaryPort == null) {
            if (other.primaryPort != null)
                return false;
        } else if (!primaryPort.equals(other.primaryPort))
            return false;
        if (secondaryPort == null) {
            if (other.secondaryPort != null)
                return false;
        } else if (!secondaryPort.equals(other.secondaryPort))
            return false;
        if (stateCode == null) {
            if (other.stateCode != null)
                return false;
        } else if (!stateCode.equals(other.stateCode))
            return false;
        if (suburbName == null) {
            if (other.suburbName != null)
                return false;
        } else if (!suburbName.equals(other.suburbName))
            return false;
        return true;
    }
}