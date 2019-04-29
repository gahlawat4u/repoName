package com.gms.xms.persistence.dao.adjustmentrequest;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestDetailFilter;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestDetailVo;

import java.util.List;

/**
 * Posted from AirbillAdjustmentRequestDetailDao
 * </p>
 *
 * @author hungnt - Nov 3, 2015
 */
public class AirbillAdjustmentRequestDetailDao extends BaseDao<AirbillAdjustmentRequestDetailVo> {
    public List<AirbillAdjustmentRequestDetailVo> selectDetailByFilter(AirbillAdjustmentRequestDetailFilter filter) throws DaoException {
        return this.selectList(filter, "AirbillAdjustmentRequest.selectDetailByFilter");
    }

    public Long countDetailByFilter(AirbillAdjustmentRequestDetailFilter filter) throws DaoException {
        return (Long) this.selectObject(filter, "AirbillAdjustmentRequest.countDetailByFilter");
    }
}