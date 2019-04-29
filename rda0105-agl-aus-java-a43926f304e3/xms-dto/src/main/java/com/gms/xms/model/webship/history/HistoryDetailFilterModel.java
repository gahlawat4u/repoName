package com.gms.xms.model.webship.history;

import com.gms.xms.model.BaseModel;

/**
 * Posted from HistoryDetailFilter
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailFilterModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 8770002259057982182L;

    private String shipmentId;
    private String defaultOriginCountry;
    private String lbToKg;
    private String inToCm;
    private String weightValue;


    public String getDefaultOriginCountry() {
        return defaultOriginCountry;
    }

    public void setDefaultOriginCountry(String defaultOriginCountry) {
        this.defaultOriginCountry = defaultOriginCountry;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getLbToKg() {
        return lbToKg;
    }

    public void setLbToKg(String lbToKg) {
        this.lbToKg = lbToKg;
    }

    public String getInToCm() {
        return inToCm;
    }

    public void setInToCm(String inToCm) {
        this.inToCm = inToCm;
    }

    public String getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(String weightValue) {
        this.weightValue = weightValue;
    }

    @Override
    public String toString() {
        return "HistoryDetailFilterModel [shipmentId=" + shipmentId + ", defaultOriginCountry=" + defaultOriginCountry + ", lbToKg=" + lbToKg + ", inToCm=" + inToCm + ", weightValue=" + weightValue + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((defaultOriginCountry == null) ? 0 : defaultOriginCountry.hashCode());
        result = prime * result + ((inToCm == null) ? 0 : inToCm.hashCode());
        result = prime * result + ((lbToKg == null) ? 0 : lbToKg.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((weightValue == null) ? 0 : weightValue.hashCode());
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
        HistoryDetailFilterModel other = (HistoryDetailFilterModel) obj;
        if (defaultOriginCountry == null) {
            if (other.defaultOriginCountry != null)
                return false;
        } else if (!defaultOriginCountry.equals(other.defaultOriginCountry))
            return false;
        if (inToCm == null) {
            if (other.inToCm != null)
                return false;
        } else if (!inToCm.equals(other.inToCm))
            return false;
        if (lbToKg == null) {
            if (other.lbToKg != null)
                return false;
        } else if (!lbToKg.equals(other.lbToKg))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (weightValue == null) {
            if (other.weightValue != null)
                return false;
        } else if (!weightValue.equals(other.weightValue))
            return false;
        return true;
    }

}