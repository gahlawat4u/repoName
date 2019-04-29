package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.ShipmentProductDetailVo;

import java.util.List;
import java.util.Map;

/**
 * @author TANDT
 */
public class ShipmentProductDetailDao extends BaseDao<ShipmentProductDetailVo> {
    public ShipmentProductDetailDao() {
        super();
    }

    public ShipmentProductDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void insert(Map<String, String> context, ShipmentProductDetailVo shipmentDetailVo) throws DaoException {
        insert(context, shipmentDetailVo, "ShipmentProductDetailMap.insert");
    }

    public List<ShipmentProductDetailVo> selectByShipment(Long shipmentId) throws DaoException {
        return selectList(shipmentId, "ShipmentProductDetailMap.selectProductDetailByShipmentId");
    }
}
