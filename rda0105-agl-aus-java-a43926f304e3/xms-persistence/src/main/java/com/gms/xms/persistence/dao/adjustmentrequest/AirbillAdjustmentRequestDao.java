package com.gms.xms.persistence.dao.adjustmentrequest;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestFilter;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AirbillAdjustmentRequestRequestDao
 * </p>
 *
 * @author hungnt - Nov 3, 2015
 */
public class AirbillAdjustmentRequestDao extends BaseDao<AirbillAdjustmentRequestVo> {
    public AirbillAdjustmentRequestDao() {
        super();
    }

    public AirbillAdjustmentRequestDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<AirbillAdjustmentRequestVo> selectAdjustmentTypes() throws DaoException {
        return this.selectList(null, "AirbillAdjustmentRequest.selectAdjustmentTypes");
    }

    public void insert(Map<String, String> context, AirbillAdjustmentRequestVo adjustment) throws DaoException {
        this.insert(context, adjustment, "AirbillAdjustmentRequest.insert");
    }

    public void update(Map<String, String> context, AirbillAdjustmentRequestVo adjustment) throws DaoException {
        update(context, adjustment, "AirbillAdjustmentRequest.update");
    }

    public void deleteById(Map<String, String> context, Long adjustmentId) throws DaoException {
        delete(context, adjustmentId, "AirbillAdjustmentRequest.deleteById");
    }

    public AirbillAdjustmentRequestVo selectById(Long adjustmentId) throws DaoException {
        return this.select(adjustmentId, "AirbillAdjustmentRequest.selectById");
    }

    public AirbillAdjustmentRequestVo selectTotalPayableOfAdjustType(AirbillAdjustmentRequestFilter filter) throws DaoException {
        return this.select(filter, "AirbillAdjustmentRequest.selectTotalPayableOfAdjustType");
    }

    public AirbillAdjustmentRequestVo selectTotalAdjustAmountOfAWB(AirbillAdjustmentRequestFilter filter) throws DaoException {
        return this.select(filter, "AirbillAdjustmentRequest.selectTotalAdjustAmountOfAWB");
    }

    public AirbillAdjustmentRequestVo selectTotalPayableOfAWB(AirbillAdjustmentRequestFilter filter) throws DaoException {
        return this.select(filter, "AirbillAdjustmentRequest.selectTotalPayableOfAWB");
    }
}