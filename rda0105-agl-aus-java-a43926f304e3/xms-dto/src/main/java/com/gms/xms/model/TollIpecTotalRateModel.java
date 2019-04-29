package com.gms.xms.model;


/**
 * @author tkvcl
 */
public class TollIpecTotalRateModel extends BaseModel {


    /**
     *
     */
    private static final long serialVersionUID = 1461898656769955571L;

    private String fromZone;

    private String toZone;

    public String getFromZone() {
        return fromZone;
    }

    public void setFromZone(String fromZone) {
        this.fromZone = fromZone;
    }

    public String getToZone() {
        return toZone;
    }

    public void setToZone(String toZone) {
        this.toZone = toZone;
    }

    @Override
    public String toString() {
        return "TollIpecTotalRateModel [fromZone=" + fromZone + ", toZone=" + toZone + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fromZone == null) ? 0 : fromZone.hashCode());
        result = prime * result + ((toZone == null) ? 0 : toZone.hashCode());
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
        TollIpecTotalRateModel other = (TollIpecTotalRateModel) obj;
        if (fromZone == null) {
            if (other.fromZone != null)
                return false;
        } else if (!fromZone.equals(other.fromZone))
            return false;
        if (toZone == null) {
            if (other.toZone != null)
                return false;
        } else if (!toZone.equals(other.toZone))
            return false;
        return true;
    }


}