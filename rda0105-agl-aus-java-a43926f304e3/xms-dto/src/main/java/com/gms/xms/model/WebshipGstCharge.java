package com.gms.xms.model;

public class WebshipGstCharge extends BaseModel {
    private static final long serialVersionUID = 4486424306985786246L;

    private String gstName;
    private String gstValue;

    public String getGstName() {
        return gstName;
    }

    public void setGstName(String gstName) {
        this.gstName = gstName;
    }

    public String getGstValue() {
        return gstValue;
    }

    public void setGstValue(String gstValue) {
        this.gstValue = gstValue;
    }

    @Override
    public String toString() {
        return "WebshipGstCharge [gstName=" + gstName + ", gstValue=" + gstValue + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gstName == null) ? 0 : gstName.hashCode());
        result = prime * result + ((gstValue == null) ? 0 : gstValue.hashCode());
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
        WebshipGstCharge other = (WebshipGstCharge) obj;
        if (gstName == null) {
            if (other.gstName != null)
                return false;
        } else if (!gstName.equals(other.gstName))
            return false;
        if (gstValue == null) {
            if (other.gstValue != null)
                return false;
        } else if (!gstValue.equals(other.gstValue))
            return false;
        return true;
    }
}
