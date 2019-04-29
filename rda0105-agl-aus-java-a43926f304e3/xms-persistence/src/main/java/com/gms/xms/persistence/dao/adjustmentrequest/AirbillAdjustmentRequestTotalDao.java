package com.gms.xms.persistence.dao.adjustmentrequest;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestDetailFilter;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestTotalVo;

/**
 * Posted from AirbillAdjustmentTotalDao
 * <p>
 * Author DatTV Date May 12, 2015 4:30:38 PM
 */
public class AirbillAdjustmentRequestTotalDao extends BaseDao<AirbillAdjustmentRequestTotalVo> {
    public AirbillAdjustmentRequestTotalVo totalDetailByFilter(AirbillAdjustmentRequestDetailFilter filter) throws DaoException {
        return select(filter, "AirbillAdjustmentRequest.totalDetailByFilter");
    }
}