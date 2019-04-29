package com.gms.xms.txndb.vo.dhl;

import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;

public class DHLPickupRequestVo extends BaseVo {
    private static final long serialVersionUID = -8996612125196811708L;

    private HistoryDetailInfoVo historyDetailInfoVo;
    private ShipmentVo shipmentVo;
    private ScheduleCollectionVo scheduleCollectionVo;
    private AddressVo addressVo;
    private PieceVo piece;

    public HistoryDetailInfoVo getHistoryDetailInfoVo() {
        return historyDetailInfoVo;
    }

    public void setHistoryDetailInfoVo(HistoryDetailInfoVo historyDetailInfoVo) {
        this.historyDetailInfoVo = historyDetailInfoVo;
    }

    public ShipmentVo getShipmentVo() {
        return shipmentVo;
    }

    public void setShipmentVo(ShipmentVo shipmentVo) {
        this.shipmentVo = shipmentVo;
    }

    public ScheduleCollectionVo getScheduleCollectionVo() {
        return scheduleCollectionVo;
    }

    public void setScheduleCollectionVo(ScheduleCollectionVo scheduleCollectionVo) {
        this.scheduleCollectionVo = scheduleCollectionVo;
    }

    public AddressVo getAddressVo() {
        return addressVo;
    }

    public void setAddressVo(AddressVo addressVo) {
        this.addressVo = addressVo;
    }

    public PieceVo getPiece() {
        return piece;
    }

    public void setPiece(PieceVo piece) {
        this.piece = piece;
    }

}
