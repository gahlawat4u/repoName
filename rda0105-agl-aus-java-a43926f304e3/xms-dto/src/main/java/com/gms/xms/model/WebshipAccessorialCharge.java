package com.gms.xms.model;

/**
 * Posted from WebshipAccessorialCharge
 * <p>
 * Author HungNT Date Apr 15, 2015
 */
public class WebshipAccessorialCharge extends BaseModel {
    private static final long serialVersionUID = -8375639431873704286L;

    private String accessorialName;
    private String accessorialValue;

    public String getAccessorialName() {
        return accessorialName;
    }

    public void setAccessorialName(String accessorialName) {
        this.accessorialName = accessorialName;
    }

    public String getAccessorialValue() {
        return accessorialValue;
    }

    public void setAccessorialValue(String accessorialValue) {
        this.accessorialValue = accessorialValue;
    }

    @Override
    public String toString() {
        return "WebshipAccessorialCharge [accessorialName=" + accessorialName + ", accessorialValue=" + accessorialValue + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessorialName == null) ? 0 : accessorialName.hashCode());
        result = prime * result + ((accessorialValue == null) ? 0 : accessorialValue.hashCode());
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
        WebshipAccessorialCharge other = (WebshipAccessorialCharge) obj;
        if (accessorialName == null) {
            if (other.accessorialName != null)
                return false;
        } else if (!accessorialName.equals(other.accessorialName))
            return false;
        if (accessorialValue == null) {
            if (other.accessorialValue != null)
                return false;
        } else if (!accessorialValue.equals(other.accessorialValue))
            return false;
        return true;
    }
}
