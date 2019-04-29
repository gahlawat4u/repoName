package com.gms.xms.persistence.dao.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.history.HistoryProductAirbillVo;

import java.util.List;

/**
 * Posted from HistoryDetailDao
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryProductAirbillDao extends BaseDao<HistoryProductAirbillVo> {
    /**
     * @return
     * @throws DaoException
     */

    public List<HistoryProductAirbillVo> selectHistoryProductAirbill(Long shipmentId) throws DaoException {
        return selectList(shipmentId, "HistoryProductAirbill.selectHistoryProductAirbill");
    }
}
