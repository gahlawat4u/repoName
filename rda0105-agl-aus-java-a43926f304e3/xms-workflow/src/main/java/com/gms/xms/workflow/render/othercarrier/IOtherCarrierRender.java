package com.gms.xms.workflow.render.othercarrier;

import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.webship.history.HistoryDetailAccessorialModel;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.model.webship.history.HistoryDetailPieceModel;

import java.util.List;

public interface IOtherCarrierRender {
    public void genShipmentReceiptHtml(HistoryDetailInfoModel detailInfoModel, List<HistoryDetailPieceModel> pieces, CustomerAddressModel franchiseAddress, String outPutFilePath, List<HistoryDetailAccessorialModel> detailAccessorialModels) throws Exception;
}
