package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.txndb.vo.adjustment.AdjustmentHistoryVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from CarrierAdjustmentRejectSubmittedDaoService
 * <p>
 * Author TanDT Date Jun 1, 2015
 */
public class CarrierAdjustmentRejectSubmittedDaoService {

    private static final Log logger = LogFactory.getLog(CreditNoteDaoService.class);

    public void updateMultiCarrierAdjustmentById(Map<String, String> context, Long userId, List<Long> listAdjustmentId) throws DaoException {
        for (Long adjustmentId : listAdjustmentId) {
            this.updateCarrierAdjustmentById(context, userId, adjustmentId);
        }
    }

    /**
     * Action Accept for a one AdjustmentId
     *
     * @param adjustmentId
     * @throws DaoException
     */
    public void updateCarrierAdjustmentById(Map<String, String> context, Long userId, Long adjustmentId) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        AirbillAdjustmentDao airbillAdjustmentDao = new AirbillAdjustmentDao();
        AdjustmentHistoryDao adjustmentHistoryDao = new AdjustmentHistoryDao();

        try {

            // Update AdjustmentVo
            AirbillAdjustmentVo airbillAdjustmentVo = new AirbillAdjustmentVo();
            airbillAdjustmentVo.setStatus((byte) 3);
            airbillAdjustmentVo.setAdjustmentId(adjustmentId);
            airbillAdjustmentDao.update(context, airbillAdjustmentVo);
            // Insert AdjustmentHistory
            AdjustmentHistoryVo adjustmentHistoryVo = new AdjustmentHistoryVo();
            adjustmentHistoryVo.setAdjustmentId(adjustmentId);
            adjustmentHistoryVo.setStatus(3);
            adjustmentHistoryVo.setUserId(userId);
            adjustmentHistoryVo.setActionDate(new Date());
            adjustmentHistoryDao.insert(context, adjustmentHistoryVo);
            sessionClient.endTransaction();
        } catch (DaoException ex) {
            logger.error(ex);
            sessionClient.rollback();
            throw ex;
        }
    }

}
