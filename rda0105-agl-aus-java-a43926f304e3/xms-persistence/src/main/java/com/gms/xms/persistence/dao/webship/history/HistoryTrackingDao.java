package com.gms.xms.persistence.dao.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.DeliveredTrackingListFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryTrackingListShipmentVo;

import java.util.List;

/**
 * Posted from HistoryTrackingDao
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryTrackingDao extends BaseDao<HistoryTrackingListShipmentVo> {
    /**
     * @return
     * @throws DaoException
     */

    public List<HistoryTrackingListShipmentVo> selectListTrackingShipment(DeliveredTrackingListFilter filter) throws DaoException {
        return selectList(filter, "HistoryTracking.listTrackingShipment");
    }
}
