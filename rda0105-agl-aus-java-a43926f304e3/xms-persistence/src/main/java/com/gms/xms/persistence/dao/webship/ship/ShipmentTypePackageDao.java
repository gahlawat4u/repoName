package com.gms.xms.persistence.dao.webship.ship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.ShipmentTypePackageVo;

/**
 * Posted from ShipmentTypePackageDao
 * <p>
 * Author HungNT Date Jul 16, 2015
 */
public class ShipmentTypePackageDao extends BaseDao<ShipmentTypePackageVo> {
    public ShipmentTypePackageVo selectShipmentTypePackageByVo(ShipmentTypePackageVo shipmentTypePackageVo) throws DaoException {
        return select(shipmentTypePackageVo, "ShipmentTypePackage.selectShipmentTypePackageByVo");
    }
}