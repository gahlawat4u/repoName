package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.ShipmentBillingFilter;

public class WarrantyServiceDao extends BaseDao<Object> {
    public Integer countWarrantyServiceShipment(ShipmentBillingFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "WarrantyService.countWarrantyServiceShipment");
    }

    public Integer countWarrantyServiceShipmentBilling(ShipmentBillingFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "WarrantyService.countWarrantyServiceShipmentBilling");
    }
}
