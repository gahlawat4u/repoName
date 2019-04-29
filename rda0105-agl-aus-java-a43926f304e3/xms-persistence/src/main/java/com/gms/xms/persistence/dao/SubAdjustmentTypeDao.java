package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.adjustment.SubAdjustmentTypeVo;

import java.util.List;

/**
 * Posted from SubAdjustmentTypeDao
 * <p>
 * Author DatTV Date May 18, 2015 2:31:25 PM
 */
public class SubAdjustmentTypeDao extends BaseDao<SubAdjustmentTypeVo> {
    public SubAdjustmentTypeDao() {
        super();
    }

    public SubAdjustmentTypeDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<SubAdjustmentTypeVo> selectByAWB(ShipmentBillingVo shipmentBillingVo) throws DaoException {
        return this.selectList(shipmentBillingVo, "SubAdjustmentType.selectByAWB");
    }
}