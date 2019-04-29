package com.gms.xms.model.webship.history;

import com.gms.xms.model.BaseModel;

/**
 * Posted from HistoryTrackingListShipmentFilterModel
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryTrackingListShipmentFilterModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 8770002259057982182L;

    private String shipmentId;
    private String day;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "HistoryTrackingListShipmentFilterModel [shipmentId=" + shipmentId + ", day=" + day + "]";
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
        HistoryTrackingListShipmentFilterModel other = (HistoryTrackingListShipmentFilterModel) obj;
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