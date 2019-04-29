package com.gms.xms.persistence.dao.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailAccessorialVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;

import java.util.List;

/**
 * Posted from HistoryDetailPieceDao
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailAccessorialDao extends BaseDao<HistoryDetailAccessorialVo> {
    /**
     * @return
     * @throws DaoException
     */

    public List<HistoryDetailAccessorialVo> selectHistoryDetailAccessorial(HistoryDetailFilter filter) throws DaoException {
        return selectList(filter, "HistoryDetail.selectHistoryDetailAccessorial");
    }

}
