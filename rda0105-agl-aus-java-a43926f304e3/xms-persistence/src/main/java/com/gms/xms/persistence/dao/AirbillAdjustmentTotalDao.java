package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.adjustment.AirbillAdjustmentDetailFilter;
import com.gms.xms.txndb.vo.adjustment.AirbillAdjustmentTotalVo;

/**
 * Posted from AirbillAdjustmentTotalDao
 * <p>
 * Author DatTV Date May 12, 2015 4:30:38 PM
 */
public class AirbillAdjustmentTotalDao extends BaseDao<AirbillAdjustmentTotalVo> {
    public AirbillAdjustmentTotalVo totalDetailByFilter(AirbillAdjustmentDetailFilter filter) throws DaoException {
        return this.select(filter, "AirbillAdjustment.totalDetailByFilter");
    }
}