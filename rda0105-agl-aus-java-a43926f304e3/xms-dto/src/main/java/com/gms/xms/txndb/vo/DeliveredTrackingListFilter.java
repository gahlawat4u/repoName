package com.gms.xms.txndb.vo;


/**
 * Posted from DeliveredTrackingListFilter
 * <p>
 * Author TanDT Date Apr 15, 2015
 */
public class DeliveredTrackingListFilter extends BaseVo {
    private static final long serialVersionUID = 1L;
    private long shipmentId;
    private String isAll = "";
    private Integer day;


    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getIsAll() {
        return isAll;
    }

    public void setIsAll(String isAll) {
        this.isAll = isAll;
    }

    @Override
    public String toString() {
        return "DeliveredTrackingListFilter [shipmentId=" + shipmentId + ", isAll=" + isAll + ", day=" + day + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((isAll == null) ? 0 : isAll.hashCode());
        result = prime * result + (int) (shipmentId ^ (shipmentId >>> 32));
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
        DeliveredTrackingListFilter other = (DeliveredTrackingListFilter) obj;
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
        if (shipmentId != other.shipmentId)
            return false;
        return true;
    }


}