package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.adjustment.AdjustmentHistoryVo;

import java.util.Map;

/**
 * Posted from AdjustmentHistoryDao
 * <p>
 * Author TanDT Date May 28, 2015
 */
public class AdjustmentHistoryDao extends BaseDao<AdjustmentHistoryVo> {

    public AdjustmentHistoryDao() {
        super();
    }

    public AdjustmentHistoryDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void insert(Map<String, String> context, AdjustmentHistoryVo adjustmentHistoryVo) throws DaoException {
        insert(context, adjustmentHistoryVo, "AdjustmentHistory.insert");
    }
}
