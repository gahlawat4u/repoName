package com.gms.xms.persistence.dao.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailPieceVo;

import java.util.List;

/**
 * Posted from HistoryDetailPieceDao
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailPieceDao extends BaseDao<HistoryDetailPieceVo> {
    /**
     * @return
     * @throws DaoException
     */

    public List<HistoryDetailPieceVo> selectPieceInfo(HistoryDetailFilter filter) throws DaoException {
        return selectList(filter, "HistoryDetail.selectPieceInfo");
    }

}
