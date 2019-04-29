package com.gms.xms.persistence.dao.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailVo;

/**
 * Posted from HistoryDetailDao
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailDao extends BaseDao<HistoryDetailVo> {
    /**
     * @return
     * @throws DaoException
     */

    public HistoryDetailInfoVo selectHistoryDetailInfo(HistoryDetailFilter filter) throws DaoException {
        return (HistoryDetailInfoVo) selectObject(filter, "HistoryDetail.selectHistoryDetailInfo");
    }
}
