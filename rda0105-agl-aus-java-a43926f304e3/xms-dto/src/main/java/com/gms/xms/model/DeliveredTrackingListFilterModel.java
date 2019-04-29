package com.gms.xms.model;


/**
 * Posted from DeliveredTrackingListFilterModel
 * <p>
 * Author TanDT Date Apr 15, 2015
 */
public class DeliveredTrackingListFilterModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -4657836322185351986L;
    private String shipmentId;
    private String isAll = "";
    private String day;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getIsAll() {
        return isAll;
    }

    public void setIsAll(String isAll) {
        this.isAll = isAll;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "DeliveredTrackingListFilterModel [shipmentId=" + shipmentId + ", isAll=" + isAll + ", day=" + day + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((isAll == null) ? 0 : isAll.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
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
        DeliveredTrackingListFilterModel other = (DeliveredTrackingListFilterModel) obj;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (isAll == null) {
            if (other.isAll != null)
                return false;
        } else if (!isAll.equals(other.isAll))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        return true;
    }


}