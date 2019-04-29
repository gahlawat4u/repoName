package com.gms.xms.txndb.vo;

/**
 * Posted from ShipmentNoteFilter
 * <p>
 * Author TanDT Date Jul 10, 2015
 */
public class ShipmentNoteFilter extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -2302898635883481256L;

    private Long shipmentId;

    private Integer sizeRecord;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Integer getSizeRecord() {
        return sizeRecord;
    }

    public void setSizeRecord(Integer sizeRecord) {
        this.sizeRecord = sizeRecord;
    }

    @Override
    public String toString() {
        return "ShipmentNoteFilter [shipmentId=" + shipmentId + ", sizeRecord=" + sizeRecord + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((sizeRecord == null) ? 0 : sizeRecord.hashCode());
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
        ShipmentNoteFilter other = (ShipmentNoteFilter) obj;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (sizeRecord == null) {
            if (other.sizeRecord != null)
                return false;
        } else if (!sizeRecord.equals(other.sizeRecord))
            return false;
        return true;
    }

}
