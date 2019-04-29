package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.ShipmentDetailVo;

import java.util.List;
import java.util.Map;

public class ShipmentDetailDao extends BaseDao<ShipmentDetailVo> {
    public ShipmentDetailDao() {
        super();
    }

    public ShipmentDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<ShipmentDetailVo> getAllAccessorialByShipmentId(long shipmentId) throws DaoException {
        return selectList(shipmentId, "allaccessorialofshipment");
    }

    public AccessorialVo joinAccessorial(long accessorialId) throws DaoException {
        return (AccessorialVo) selectObject(accessorialId, "ShipmentDetail.joinAccessorial");
    }

    public void insert(Map<String, String> context, ShipmentDetailVo shipmentDetailVo) throws DaoException {
        insert(context, shipmentDetailVo, "ShipmentDetail.insert");
    }

    public void update(Map<String, String> context, ShipmentDetailVo shipmentDetailVo) throws DaoException {
        update(context, shipmentDetailVo, "ShipmentDetail.updateAmount");
    }
}
