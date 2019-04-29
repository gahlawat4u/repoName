package com.gms.xms.persistence.service.webship.shipment;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.ShipmentProductDetailVo;

import java.util.List;

public interface IShipmentProductDetailService {
    public List<ShipmentProductDetailVo> selectByShipment(Long shipmentId) throws DaoException;
}
