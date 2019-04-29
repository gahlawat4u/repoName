package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.txndb.vo.adjustment.AdjustmentRequestFilterVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AirbillAdjustmentDao
 * <p>
 * Author DatTV Date May 12, 2015 2:30:04 PM
 */
public class AirbillAdjustmentDao extends BaseDao<Object> {
    public AirbillAdjustmentDao() {
        super();
    }

    public AirbillAdjustmentDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<AirbillAdjustmentVo> selectAdjustmentTypes() throws DaoException {
        return this.selectList(null, "AirbillAdjustment.selectAdjustmentTypes");
    }

    public List<Long> selectAdjustmentId() throws DaoException {
        return this.selectList(null, "AirbillAdjustment.selectAdjustmentId");
    }

    public void insert(Map<String, String> context, AirbillAdjustmentVo adjustment) throws DaoException {
        this.insert(context, adjustment, "AirbillAdjustment.insert");
    }

    public void update(Map<String, String> context, AirbillAdjustmentVo adjustment) throws DaoException {
        update(context, adjustment, "AirbillAdjustment.update");
    }

    public void deleteById(Map<String, String> context, Long adjustmentId) throws DaoException {
        delete(context, adjustmentId, "AirbillAdjustment.deleteById");
    }

    public AirbillAdjustmentVo selectById(Long adjustmentId) throws DaoException {
        return (AirbillAdjustmentVo) this.select(adjustmentId, "AirbillAdjustment.selectById");
    }

    public AirbillAdjustmentVo selectTotalPayableOfAdjustType(AdjustmentRequestFilterVo filter) throws DaoException {
        return (AirbillAdjustmentVo) this.select(filter, "AirbillAdjustment.selectTotalPayableOfAdjustType");
    }

    public AirbillAdjustmentVo selectTotalAdjustAmountOfAWB(AdjustmentRequestFilterVo filter) throws DaoException {
        return (AirbillAdjustmentVo) this.select(filter, "AirbillAdjustment.selectTotalAdjustAmountOfAWB");
    }

    public AirbillAdjustmentVo selectTotalPayableOfAWB(AdjustmentRequestFilterVo filter) throws DaoException {
        return (AirbillAdjustmentVo) this.select(filter, "AirbillAdjustment.selectTotalPayableOfAWB");
    }

    public Double getAglWarrantyAdjustedAmount(AirbillAdjustmentVo adjustmentVo) throws DaoException {
        return (Double) this.selectObject(adjustmentVo, "AirbillAdjustment.getAglWarrantyAdjustedAmount");
    }

    public List<String> getRequestTypes() throws DaoException {
        return selectList(null, "AirbillAdjustment.getRequestTypes");
    }
}