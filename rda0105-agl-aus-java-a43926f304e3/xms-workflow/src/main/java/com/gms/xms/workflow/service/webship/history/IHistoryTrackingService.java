package com.gms.xms.workflow.service.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.DeliveredTrackingListFilter;
import com.gms.xms.txndb.vo.TrackingVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;

import java.util.List;
import java.util.Map;

public interface IHistoryTrackingService {
    public List<TrackingVo> trackingResult(DeliveredTrackingListFilter deliveredTrackingListFilter) throws DaoException;

    public HistoryDetailInfoVo selectHistoryDetailInfo(HistoryDetailFilter filter) throws DaoException;

    public int insTracking(Map<String, String> context, TrackingVo trackingVo) throws DaoException;
}
