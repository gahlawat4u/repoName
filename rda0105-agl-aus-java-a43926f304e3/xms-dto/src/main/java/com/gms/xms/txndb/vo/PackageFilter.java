package com.gms.xms.txndb.vo;

import com.gms.xms.txndb.vo.webship.PackageVo;

/**
 * Posted from PackageFilter
 * <p>
 * Author HungNT Date Apr 21, 2015
 */
public class PackageFilter extends PackageVo {
    private static final long serialVersionUID = 7797780025350681774L;

    private Integer shipmentTypeId;

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    @Override
    public String toString() {
        return "PackageFilter [shipmentTypeId=" + shipmentTypeId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((shipmentTypeId == null) ? 0 : shipmentTypeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PackageFilter other = (PackageFilter) obj;
        if (shipmentTypeId == null) {
            if (other.shipmentTypeId != null)
                return false;
        } else if (!shipmentTypeId.equals(other.shipmentTypeId))
            return false;
        return true;
    }
}
