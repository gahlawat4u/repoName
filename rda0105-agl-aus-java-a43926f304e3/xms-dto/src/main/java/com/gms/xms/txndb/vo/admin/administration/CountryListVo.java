package com.gms.xms.txndb.vo.admin.administration;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * @author TANDT
 */
public class CountryListVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -7376254104376123870L;
    private Long countryId;
    private String countryName;
    private String displayName;
    private String otherName;
    private String countryCode;
    private String gstPercent;
    private Boolean isVatQuotable;
    private Date modifiedDate;
    private Boolean isShow;
    private String dhlCountryId;
    private String dhlApCode;
    private String dhlApZone;
    private String showOrigin;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
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

    public Boolean getIsVatQuotable() {
        return isVatQuotable;
    }

    public void setIsVatQuotable(Boolean isVatQuotable) {
        this.isVatQuotable = isVatQuotable;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public String getDhlCountryId() {
        return dhlCountryId;
    }

    public void setDhlCountryId(String dhlCountryId) {
        this.dhlCountryId = dhlCountryId;
    }

    public String getDhlApCode() {
        return dhlApCode;
    }

    public void setDhlApCode(String dhlApCode) {
        this.dhlApCode = dhlApCode;
    }

    public String getDhlApZone() {
        return dhlApZone;
    }

    public void setDhlApZone(String dhlApZone) {
        this.dhlApZone = dhlApZone;
    }

    public String getShowOrigin() {
        return showOrigin;
    }

    public void setShowOrigin(String showOrigin) {
        this.showOrigin = showOrigin;
    }

    @Override
    public String toString() {
        return "CountryListVo [countryId=" + countryId + ", countryName=" + countryName + ", displayName=" + displayName + ", otherName=" + otherName + ", countryCode=" + countryCode + ", gstPercent=" + gstPercent + ", isVatQuotable=" + isVatQuotable + ", modifiedDate=" + modifiedDate + ", isShow=" + isShow + ", dhlCountryId=" + dhlCountryId + ", dhlApCode=" + dhlApCode + ", dhlApZone=" + dhlApZone + ", showOrigin=" + showOrigin + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
        result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
        result = prime * result + ((dhlApCode == null) ? 0 : dhlApCode.hashCode());
        result = prime * result + ((dhlApZone == null) ? 0 : dhlApZone.hashCode());
        result = prime * result + ((dhlCountryId == null) ? 0 : dhlCountryId.hashCode());
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((gstPercent == null) ? 0 : gstPercent.hashCode());
        result = prime * result + ((isShow == null) ? 0 : isShow.hashCode());
        result = prime * result + ((isVatQuotable == null) ? 0 : isVatQuotable.hashCode());
        result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
        result = prime * result + ((otherName == null) ? 0 : otherName.hashCode());
        result = prime * result + ((showOrigin == null) ? 0 : showOrigin.hashCode());
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
        CountryListVo other = (CountryListVo) obj;
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
        if (dhlApCode == null) {
            if (other.dhlApCode != null)
                return false;
        } else if (!dhlApCode.equals(other.dhlApCode))
            return false;
        if (dhlApZone == null) {
            if (other.dhlApZone != null)
                return false;
        } else if (!dhlApZone.equals(other.dhlApZone))
            return false;
        if (dhlCountryId == null) {
            if (other.dhlCountryId != null)
                return false;
        } else if (!dhlCountryId.equals(other.dhlCountryId))
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
        if (showOrigin == null) {
            if (other.showOrigin != null)
                return false;
        } else if (!showOrigin.equals(other.showOrigin))
            return false;
        return true;
    }

}
