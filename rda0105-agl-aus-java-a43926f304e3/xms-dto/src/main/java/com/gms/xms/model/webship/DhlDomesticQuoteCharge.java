package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.WebshipAccessorialCharge;
import com.gms.xms.model.WebshipGstCharge;

import java.util.List;

public class DhlDomesticQuoteCharge extends BaseModel {
    private static final long serialVersionUID = -3725656130932462097L;

    private String baseCharge;
    private List<WebshipAccessorialCharge> accessorialList;
    private List<WebshipGstCharge> gstList;
    private String totalCharges;
    private String quotedWeight;
    private String weightType;

    public String getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(String baseCharge) {
        this.baseCharge = baseCharge;
    }

    public List<WebshipAccessorialCharge> getAccessorialList() {
        return accessorialList;
    }

    public void setAccessorialList(List<WebshipAccessorialCharge> accessorialList) {
        this.accessorialList = accessorialList;
    }

    public List<WebshipGstCharge> getGstList() {
        return gstList;
    }

    public void setGstList(List<WebshipGstCharge> gstList) {
        this.gstList = gstList;
    }

    public String getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(String totalCharges) {
        this.totalCharges = totalCharges;
    }

    public String getQuotedWeight() {
        return quotedWeight;
    }

    public void setQuotedWeight(String quotedWeight) {
        this.quotedWeight = quotedWeight;
    }

    public String getWeightType() {
        return weightType;
    }

    public void setWeightType(String weightType) {
        this.weightType = weightType;
    }

    @Override
    public String toString() {
        return "DhlDomesticQuoteCharge [baseCharge=" + baseCharge + ", accessorialList=" + accessorialList + ", gstList=" + gstList + ", totalCharges=" + totalCharges + ", quotedWeight=" + quotedWeight + ", weightType=" + weightType + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessorialList == null) ? 0 : accessorialList.hashCode());
        result = prime * result + ((baseCharge == null) ? 0 : baseCharge.hashCode());
        result = prime * result + ((gstList == null) ? 0 : gstList.hashCode());
        result = prime * result + ((quotedWeight == null) ? 0 : quotedWeight.hashCode());
        result = prime * result + ((totalCharges == null) ? 0 : totalCharges.hashCode());
        result = prime * result + ((weightType == null) ? 0 : weightType.hashCode());
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
        DhlDomesticQuoteCharge other = (DhlDomesticQuoteCharge) obj;
        if (accessorialList == null) {
            if (other.accessorialList != null)
                return false;
        } else if (!accessorialList.equals(other.accessorialList))
            return false;
        if (baseCharge == null) {
            if (other.baseCharge != null)
                return false;
        } else if (!baseCharge.equals(other.baseCharge))
            return false;
        if (gstList == null) {
            if (other.gstList != null)
                return false;
        } else if (!gstList.equals(other.gstList))
            return false;
        if (quotedWeight == null) {
            if (other.quotedWeight != null)
                return false;
        } else if (!quotedWeight.equals(other.quotedWeight))
            return false;
        if (totalCharges == null) {
            if (other.totalCharges != null)
                return false;
        } else if (!totalCharges.equals(other.totalCharges))
            return false;
        if (weightType == null) {
            if (other.weightType != null)
                return false;
        } else if (!weightType.equals(other.weightType))
            return false;
        return true;
    }
}
