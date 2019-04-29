package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.BatchProcessingFailDetailVo;

import java.util.Map;

/**
 * Posted from BatchProcessingFailDetailDao
 * <p>
 * Author TanDT Date May 6, 2015
 */
public class BatchProcessingFailDetailDao extends BaseDao<BatchProcessingFailDetailVo> {
    public Integer deleteBatchProcessingFailDetail(Map<String, String> context, BatchProcessingFailDetailVo batchProcessingFailDetailVo) throws DaoException {
        return (Integer) delete(context, batchProcessingFailDetailVo, "Delete_BatchProcessingFail");
    }
}