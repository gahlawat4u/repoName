package com.gms.xms.persistence.service.webship.shipmenttypepackage;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.webship.ship.ShipmentTypePackageDao;
import com.gms.xms.txndb.vo.webship.ShipmentTypePackageVo;

/**
 * Posted from ShipmentTypePackageServiceImp
 * <p>
 * Author HungNT Date Jul 16, 2015
 */
public class ShipmentTypePackageServiceImp implements IShipmentTypePackageService {
    private ShipmentTypePackageDao dao = new ShipmentTypePackageDao();

    public ShipmentTypePackageVo getShipmentTypePackageListByShipmentTypeAndPackageId(ShipmentTypePackageVo shipmentTypePackageVo) throws DaoException {
        ShipmentTypePackageVo shipmentTypePackageVos = dao.selectShipmentTypePackageByVo(shipmentTypePackageVo);
        return shipmentTypePackageVos;
    }
}
