package com.gms.xms.persistence.dao.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.ShipmentVo;

import java.util.Map;

/**
 * Posted from ShipmentDao
 * <p>
 * Author TanDT Date Jul 7, 2015
 */
public class ShipmentDao extends BaseDao<ShipmentVo> {
    /**
     * @return
     * @throws DaoException
     */
    public void delete(Map<String, String> context, Long shipmentId) throws DaoException {
        delete(context, shipmentId, "ShipmentMapper.delete");
    }

    public void inser(Map<String, String> context, ShipmentVo shipmentVo) throws DaoException {
        insert(context, shipmentVo, "ShipmentMapper.insert");
    }

    public void update(Map<String, String> context, ShipmentVo shipmentVo) throws DaoException {
        update(context, shipmentVo, "ShipmentMapper.update");
    }

}
