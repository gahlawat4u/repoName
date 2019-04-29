package com.gms.xms.model;

/**
 * @author tkvcl
 */
public class TollIpecKgRateFilterModel extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = -8252962817557666125L;
    private String fromZone;
    private String toZone;
    private String serviceTypeName;

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

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    @Override
    public String toString() {
        return "TollIpecKgRateFilterModel [fromZone=" + fromZone + ", toZone=" + toZone + ", serviceTypeName=" + serviceTypeName + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fromZone == null) ? 0 : fromZone.hashCode());
        result = prime * result + ((serviceTypeName == null) ? 0 : serviceTypeName.hashCode());
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
        TollIpecKgRateFilterModel other = (TollIpecKgRateFilterModel) obj;
        if (fromZone == null) {
            if (other.fromZone != null)
                return false;
        } else if (!fromZone.equals(other.fromZone))
            return false;
        if (serviceTypeName == null) {
            if (other.serviceTypeName != null)
                return false;
        } else if (!serviceTypeName.equals(other.serviceTypeName))
            return false;
        if (toZone == null) {
            if (other.toZone != null)
                return false;
        } else if (!toZone.equals(other.toZone))
            return false;
        return true;
    }


}