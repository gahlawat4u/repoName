package com.gms.xms.txndb.vo.webship.history;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from HistoryDetailAccessorialVo
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryTrackingListShipmentVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 8770002259057982182L;

    private Long shipmentId;
    private String airbillNumber;
    private String shipmentTypeName;
    private Byte trackStatus;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public Byte getTrackStatus() {
        return trackStatus;
    }

    public void setTrackStatus(Byte trackStatus) {
        this.trackStatus = trackStatus;
    }

    @Override
    public String toString() {
        return "HistoryTrackingListShipmentVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", shipmentTypeName=" + shipmentTypeName + ", trackStatus=" + trackStatus + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((shipmentTypeName == null) ? 0 : shipmentTypeName.hashCode());
        result = prime * result + ((trackStatus == null) ? 0 : trackStatus.hashCode());
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
        HistoryTrackingListShipmentVo other = (HistoryTrackingListShipmentVo) obj;
        if (airbillNumber == null) {
            if (other.airbillNumber != null)
                return false;
        } else if (!airbillNumber.equals(other.airbillNumber))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (shipmentTypeName == null) {
            if (other.shipmentTypeName != null)
                return false;
        } else if (!shipmentTypeName.equals(other.shipmentTypeName))
            return false;
        if (trackStatus == null) {
            if (other.trackStatus != null)
                return false;
        } else if (!trackStatus.equals(other.trackStatus))
            return false;
        return true;
    }


}