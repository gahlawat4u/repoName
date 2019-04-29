package com.gms.xms.persistence.service.webship.shipment;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.ShipmentProductDetailDao;
import com.gms.xms.txndb.vo.ShipmentProductDetailVo;

import java.util.List;

public class ShipmentProductDetailServiceImp implements IShipmentProductDetailService {

    @Override
    public List<ShipmentProductDetailVo> selectByShipment(Long shipmentId) throws DaoException {
        ShipmentProductDetailDao dao = new ShipmentProductDetailDao();
        return dao.selectByShipment(shipmentId);
    }

}
