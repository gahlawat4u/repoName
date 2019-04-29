package com.gms.xms.model;

import com.gms.xms.model.webship.DhlCountryModel;

/**
 * Posted from CountryModel
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class TollIpecTotalRateFilterModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 6736725040246006122L;

    private String countryId;

    private String countryName;

    private String displayName;

    private String otherName;

    private String countryCode;

    private String gstPercent;

    private String isVatQuotable;

    private String modifiedDate;

    private String isShow;

    private DhlCountryModel dhlCountry;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getGstPercent() {
        return gstPercent;
    }

    public void setGstPercent(String gstPercent) {
        this.gstPercent = gstPercent;
    }

    public String getIsVatQuotable() {
        return isVatQuotable;
    }

    public void setIsVatQuotable(String isVatQuotable) {
        this.isVatQuotable = isVatQuotable;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public DhlCountryModel getDhlCountry() {
        return dhlCountry;
    }

    public void setDhlCountry(DhlCountryModel dhlCountry) {
        this.dhlCountry = dhlCountry;
    }

    @Override
    public String toString() {
        return "CountryModel [countryId=" + countryId + ", countryName=" + countryName + ", displayName=" + displayName + ", otherName=" + otherName + ", countryCode=" + countryCode + ", gstPercent=" + gstPercent + ", isVatQuotable=" + isVatQuotable + ", modifiedDate=" + modifiedDate + ", isShow=" + isShow + ", dhlCountry=" + dhlCountry + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
        result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
        result = prime * result + ((dhlCountry == null) ? 0 : dhlCountry.hashCode());
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((gstPercent == null) ? 0 : gstPercent.hashCode());
        result = prime * result + ((isShow == null) ? 0 : isShow.hashCode());
        result = prime * result + ((isVatQuotable == null) ? 0 : isVatQuotable.hashCode());
        result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
        result = prime * result + ((otherName == null) ? 0 : otherName.hashCode());
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
        TollIpecTotalRateFilterModel other = (TollIpecTotalRateFilterModel) obj;
        if (countryCode == null) {
            if (other.countryCode != null)
                return false;
        } else if (!countryCode.equals(other.countryCode))
            return false;
        if (countryId == null) {
            if (other.countryId != null)
                return false;
        } else if (!countryId.equals(other.countryId))
            return false;
        if (countryName == null) {
            if (other.countryName != null)
                return false;
        } else if (!countryName.equals(other.countryName))
            return false;
        if (dhlCountry == null) {
            if (other.dhlCountry != null)
                return false;
        } else if (!dhlCountry.equals(other.dhlCountry))
            return false;
        if (displayName == null) {
            if (other.displayName != null)
                return false;
        } else if (!displayName.equals(other.displayName))
            return false;
        if (gstPercent == null) {
            if (other.gstPercent != null)
                return false;
        } else if (!gstPercent.equals(other.gstPercent))
            return false;
        if (isShow == null) {
            if (other.isShow != null)
                return false;
        } else if (!isShow.equals(other.isShow))
            return false;
        if (isVatQuotable == null) {
            if (other.isVatQuotable != null)
                return false;
        } else if (!isVatQuotable.equals(other.isVatQuotable))
            return false;
        if (modifiedDate == null) {
            if (other.modifiedDate != null)
                return false;
        } else if (!modifiedDate.equals(other.modifiedDate))
            return false;
        if (otherName == null) {
            if (other.otherName != null)
                return false;
        } else if (!otherName.equals(other.otherName))
            return false;
        return true;
    }

}