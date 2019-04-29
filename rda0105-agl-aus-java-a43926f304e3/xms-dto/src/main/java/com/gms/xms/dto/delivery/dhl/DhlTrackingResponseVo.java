package com.gms.xms.dto.delivery.dhl;

import com.gms.xms.txndb.vo.TrackingVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;

import java.util.List;

/**
 * Posted from DhlTrackingResponseVo
 * <p>
 * Author dattrinh Feb 1, 2016 5:03:42 PM
 */
public class DhlTrackingResponseVo {
    private HistoryDetailInfoVo detailInfo;
    private List<TrackingVo> trackingList;

    public HistoryDetailInfoVo getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(HistoryDetailInfoVo detailInfo) {
        this.detailInfo = detailInfo;
    }

    public List<TrackingVo> getTrackingList() {
        return trackingList;
    }

    public void setTrackingList(List<TrackingVo> trackingList) {
        this.trackingList = trackingList;
    }

    @Override
    public String toString() {
        return "DhlTrackingResponseVo [detailInfo=" + detailInfo + ", trackingList=" + trackingList + "]";
    }
}
