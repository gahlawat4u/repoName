package com.gms.xms.txndb.vo.webship.ship;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypePackageVo;

/**
 * Posted from ShipmentTypePackageServiceVo
 * <p>
 * Author HungNT Date Jul 16, 2015
 */
public class ShipmentTypePackageServiceVo extends BaseVo {
    private static final long serialVersionUID = -5846642892814916955L;

    private PackageVo packageType;
    private ShipmentTypePackageVo shipmentTypePackage;

    public PackageVo getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageVo packageType) {
        this.packageType = packageType;
    }

    public ShipmentTypePackageVo getShipmentTypePackage() {
        return shipmentTypePackage;
    }

    public void setShipmentTypePackage(ShipmentTypePackageVo shipmentTypePackage) {
        this.shipmentTypePackage = shipmentTypePackage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((packageType == null) ? 0 : packageType.hashCode());
        result = prime * result + ((shipmentTypePackage == null) ? 0 : shipmentTypePackage.hashCode());
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
        ShipmentTypePackageServiceVo other = (ShipmentTypePackageServiceVo) obj;
        if (packageType == null) {
            if (other.packageType != null)
                return false;
        } else if (!packageType.equals(other.packageType))
            return false;
        if (shipmentTypePackage == null) {
            if (other.shipmentTypePackage != null)
                return false;
        } else if (!shipmentTypePackage.equals(other.shipmentTypePackage))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ShipmentTypePackageServiceVo [packageType=" + packageType + ", shipmentTypePackage=" + shipmentTypePackage + "]";
    }
}
