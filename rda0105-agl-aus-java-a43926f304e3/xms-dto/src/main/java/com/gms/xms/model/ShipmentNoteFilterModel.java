package com.gms.xms.model;

/**
 * Posted from ShipmentNoteFilterModel
 * <p>
 * Author TanDT Date Jul 10, 2015
 */
public class ShipmentNoteFilterModel extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = 3383793961230170377L;

    private String shipmentId;

    private String sizeRecord;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getSizeRecord() {
        return sizeRecord;
    }

    public void setSizeRecord(String sizeRecord) {
        this.sizeRecord = sizeRecord;
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
        ShipmentNoteFilterModel other = (ShipmentNoteFilterModel) obj;
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

    @Override
    public String toString() {
        return "ShipmentNoteFilterModel [shipmentId=" + shipmentId + ", sizeRecord=" + sizeRecord + "]";
    }

}
