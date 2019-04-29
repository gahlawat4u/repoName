package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.adjustment.AirbillAdjustmentDetailFilter;
import com.gms.xms.txndb.vo.adjustment.AirbillAdjustmentDetailVo;

import java.util.List;

/**
 * Posted from AirbillAdjustmentDetailDao
 * <p>
 * Author DatTV Date May 12, 2015 2:30:33 PM
 */
public class AirbillAdjustmentDetailDao extends BaseDao<AirbillAdjustmentDetailVo> {
    public List<AirbillAdjustmentDetailVo> selectDetailByFilter(AirbillAdjustmentDetailFilter filter) throws DaoException {
        return this.selectList(filter, "AirbillAdjustment.selectDetailByFilter");
    }

    public Long countDetailByFilter(AirbillAdjustmentDetailFilter filter) throws DaoException {
        return (Long) this.selectObject(filter, "AirbillAdjustment.countDetailByFilter");
    }
}