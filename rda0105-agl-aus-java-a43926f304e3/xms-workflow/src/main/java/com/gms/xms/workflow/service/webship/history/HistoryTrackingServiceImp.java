package com.gms.xms.workflow.service.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.TrackingDao;
import com.gms.xms.persistence.dao.webship.history.HistoryDetailDao;
import com.gms.xms.persistence.dao.webship.history.HistoryTrackingDao;
import com.gms.xms.txndb.vo.DeliveredTrackingListFilter;
import com.gms.xms.txndb.vo.TrackingVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryTrackingListShipmentVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryTrackingServiceImp implements IHistoryTrackingService {

    @Override
    public List<TrackingVo> trackingResult(DeliveredTrackingListFilter deliveredTrackingListFilter) throws DaoException {
        TrackingDao trackingDao = new TrackingDao();
        HistoryTrackingDao historyTrackingDao = new HistoryTrackingDao();
        List<TrackingVo> listTrackingVo = new ArrayList<TrackingVo>();
        List<HistoryTrackingListShipmentVo> historyTrackingListShipmentVos = new ArrayList<HistoryTrackingListShipmentVo>();
        deliveredTrackingListFilter.setIsAll("");
        listTrackingVo = trackingDao.getTrackingBySm(deliveredTrackingListFilter);
        if (listTrackingVo.size() > 0) {
            deliveredTrackingListFilter.setIsAll("all");
            listTrackingVo = trackingDao.getTrackingBySm(deliveredTrackingListFilter);
            return listTrackingVo;
        }

        deliveredTrackingListFilter.setIsAll("");
        historyTrackingListShipmentVos = historyTrackingDao.selectListTrackingShipment(deliveredTrackingListFilter);
        if (historyTrackingListShipmentVos.size() > 0) {
            deliveredTrackingListFilter.setIsAll("all");
            listTrackingVo = trackingDao.getTrackingBySm(deliveredTrackingListFilter);
            return listTrackingVo;
        }

        return listTrackingVo;
    }

    @Override
    public HistoryDetailInfoVo selectHistoryDetailInfo(HistoryDetailFilter filter) throws DaoException {
        HistoryDetailDao historyDetailDao = new HistoryDetailDao();
        return historyDetailDao.selectHistoryDetailInfo(filter);
    }

    @Override
    public int insTracking(Map<String, String> context, TrackingVo trackingVo) throws DaoException {
        TrackingDao dao = new TrackingDao();

        return dao.insTracking(context, trackingVo);
    }

}
