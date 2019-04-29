package com.gms.xms.persistence.service.webship.shipmenttypepackage;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.ShipmentTypePackageVo;

/**
 * Posted from IShipmentTypePackageService
 * <p>
 * Author HungNT Date Jul 16, 2015
 */
public interface IShipmentTypePackageService {
    public ShipmentTypePackageVo getShipmentTypePackageListByShipmentTypeAndPackageId(ShipmentTypePackageVo shipmentTypePackageVo) throws DaoException;
}
