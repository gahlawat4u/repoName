package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

public class ShipmentTypePackageVo extends BaseVo {
    private static final long serialVersionUID = -5514694421891640218L;

    private Integer spId;

    private Integer shipmentTypeId;

    private Integer packageId;

    private String defaultContentType;

    private Integer allowDoxAddpiece;

    private Integer allowWpxAddpiece;

    private Integer allowDox;

    private Integer allowWpx;

    private Integer allowCustomValue;

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getDefaultContentType() {
        return defaultContentType;
    }

    public void setDefaultContentType(String defaultContentType) {
        this.defaultContentType = defaultContentType == null ? null : defaultContentType.trim();
    }

    public Integer getAllowDoxAddpiece() {
        return allowDoxAddpiece;
    }

    public void setAllowDoxAddpiece(Integer allowDoxAddpiece) {
        this.allowDoxAddpiece = allowDoxAddpiece;
    }

    public Integer getAllowWpxAddpiece() {
        return allowWpxAddpiece;
    }

    public void setAllowWpxAddpiece(Integer allowWpxAddpiece) {
        this.allowWpxAddpiece = allowWpxAddpiece;
    }

    public Integer getAllowDox() {
        return allowDox;
    }

    public void setAllowDox(Integer allowDox) {
        this.allowDox = allowDox;
    }

    public Integer getAllowWpx() {
        return allowWpx;
    }

    public void setAllowWpx(Integer allowWpx) {
        this.allowWpx = allowWpx;
    }

    public Integer getAllowCustomValue() {
        return allowCustomValue;
    }

    public void setAllowCustomValue(Integer allowCustomValue) {
        this.allowCustomValue = allowCustomValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allowCustomValue == null) ? 0 : allowCustomValue.hashCode());
        result = prime * result + ((allowDox == null) ? 0 : allowDox.hashCode());
        result = prime * result + ((allowDoxAddpiece == null) ? 0 : allowDoxAddpiece.hashCode());
        result = prime * result + ((allowWpx == null) ? 0 : allowWpx.hashCode());
        result = prime * result + ((allowWpxAddpiece == null) ? 0 : allowWpxAddpiece.hashCode());
        result = prime * result + ((defaultContentType == null) ? 0 : defaultContentType.hashCode());
        result = prime * result + ((packageId == null) ? 0 : packageId.hashCode());
        result = prime * result + ((shipmentTypeId == null) ? 0 : shipmentTypeId.hashCode());
        result = prime * result + ((spId == null) ? 0 : spId.hashCode());
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
        ShipmentTypePackageVo other = (ShipmentTypePackageVo) obj;
        if (allowCustomValue == null) {
            if (other.allowCustomValue != null)
                return false;
        } else if (!allowCustomValue.equals(other.allowCustomValue))
            return false;
        if (allowDox == null) {
            if (other.allowDox != null)
                return false;
        } else if (!allowDox.equals(other.allowDox))
            return false;
        if (allowDoxAddpiece == null) {
            if (other.allowDoxAddpiece != null)
                return false;
        } else if (!allowDoxAddpiece.equals(other.allowDoxAddpiece))
            return false;
        if (allowWpx == null) {
            if (other.allowWpx != null)
                return false;
        } else if (!allowWpx.equals(other.allowWpx))
            return false;
        if (allowWpxAddpiece == null) {
            if (other.allowWpxAddpiece != null)
                return false;
        } else if (!allowWpxAddpiece.equals(other.allowWpxAddpiece))
            return false;
        if (defaultContentType == null) {
            if (other.defaultContentType != null)
                return false;
        } else if (!defaultContentType.equals(other.defaultContentType))
            return false;
        if (packageId == null) {
            if (other.packageId != null)
                return false;
        } else if (!packageId.equals(other.packageId))
            return false;
        if (shipmentTypeId == null) {
            if (other.shipmentTypeId != null)
                return false;
        } else if (!shipmentTypeId.equals(other.shipmentTypeId))
            return false;
        if (spId == null) {
            if (other.spId != null)
                return false;
        } else if (!spId.equals(other.spId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ShipmentTypePackageVo [spId=" + spId + ", shipmentTypeId=" + shipmentTypeId + ", packageId=" + packageId + ", defaultContentType=" + defaultContentType + ", allowDoxAddpiece=" + allowDoxAddpiece + ", allowWpxAddpiece=" + allowWpxAddpiece + ", allowDox=" + allowDox + ", allowWpx=" + allowWpx + ", allowCustomValue=" + allowCustomValue + "]";
    }
}