package com.gms.xms.txndb.vo.webship.history;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from HistoryTrackingListShipmentFilter
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryTrackingListShipmentFilter extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 8770002259057982182L;

    private Long shipmentId;
    private Integer day;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "HistoryTrackingListShipmentFilter [shipmentId=" + shipmentId + ", day=" + day + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
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
        HistoryTrackingListShipmentFilter other = (HistoryTrackingListShipmentFilter) obj;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        return true;
    }


}