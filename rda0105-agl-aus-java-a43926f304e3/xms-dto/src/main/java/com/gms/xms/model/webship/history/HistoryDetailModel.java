package com.gms.xms.model.webship.history;

import com.gms.xms.model.BaseModel;

/**
 * Posted from HistoryDetailModel
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 8770002259057982182L;

    private HistoryDetailInfoModel historyDetailInfoModel;
    private HistoryDetailPieceModel historyDetailPieceModel;
    private HistoryDetailAccessorialModel historyDetailAccessorialModel;

    public HistoryDetailInfoModel getHistoryDetailInfoModel() {
        return historyDetailInfoModel;
    }

    public void setHistoryDetailInfoModel(HistoryDetailInfoModel historyDetailInfoModel) {
        this.historyDetailInfoModel = historyDetailInfoModel;
    }

    public HistoryDetailPieceModel getHistoryDetailPieceModel() {
        return historyDetailPieceModel;
    }

    public void setHistoryDetailPieceModel(HistoryDetailPieceModel historyDetailPieceModel) {
        this.historyDetailPieceModel = historyDetailPieceModel;
    }

    public HistoryDetailAccessorialModel getHistoryDetailAccessorialModel() {
        return historyDetailAccessorialModel;
    }

    public void setHistoryDetailAccessorialModel(HistoryDetailAccessorialModel historyDetailAccessorialModel) {
        this.historyDetailAccessorialModel = historyDetailAccessorialModel;
    }

}