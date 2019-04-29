package com.gms.xms.model.webship.ship;

import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ship.QuoteVo;

import java.util.List;

/**
 * Posted from BookingDataVo
 * <p>
 * Author TANDT Date Apr 18, 2015
 */
public class BookingDataVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 2490994713223196046L;

    private AddressVo sAddress;
    private AddressVo rAddress;
    private ShipmentVo shipmentVo;
    private List<ShipmentDetailVo> shipmentDetails;
    private ScheduleCollectionVo scheduleCollection;
    private List<ShipmentProductDetailVo> shipmentProductDetail;
    private ShipmentInfoVo shipmentInfoVo;
    private String errorMessage;
    private QuoteVo quoteVo;

    public List<ShipmentProductDetailVo> getShipmentProductDetail() {
        return shipmentProductDetail;
    }

    public void setShipmentProductDetail(List<ShipmentProductDetailVo> shipmentProductDetail) {
        this.shipmentProductDetail = shipmentProductDetail;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ShipmentInfoVo getShipmentInfoVo() {
        return shipmentInfoVo;
    }

    public void setShipmentInfoVo(ShipmentInfoVo shipmentInfoVo) {
        this.shipmentInfoVo = shipmentInfoVo;
    }

    public ScheduleCollectionVo getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(ScheduleCollectionVo scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public List<ShipmentDetailVo> getShipmentDetails() {
        return shipmentDetails;
    }

    public void setShipmentDetails(List<ShipmentDetailVo> shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }

    private List<PieceVo> pieces;

    public List<PieceVo> getPieces() {
        return pieces;
    }

    public void setPieces(List<PieceVo> pieces) {
        this.pieces = pieces;
    }

    public ShipmentVo getShipmentVo() {
        return shipmentVo;
    }

    public void setShipmentVo(ShipmentVo shipmentVo) {
        this.shipmentVo = shipmentVo;
    }

    public AddressVo getsAddress() {
        return sAddress;
    }

    public void setsAddress(AddressVo sAddress) {
        this.sAddress = sAddress;
    }

    public AddressVo getrAddress() {
        return rAddress;
    }

    public void setrAddress(AddressVo rAddress) {
        this.rAddress = rAddress;
    }

    public QuoteVo getQuoteVo() {
        return quoteVo;
    }

    public void setQuoteVo(QuoteVo quoteVo) {
        this.quoteVo = quoteVo;
    }

    @Override
    public String toString() {
        return "BookingDataVo [sAddress=" + sAddress + ", rAddress=" + rAddress + ", shipmentVo=" + shipmentVo + ", shipmentDetails=" + shipmentDetails + ", scheduleCollection=" + scheduleCollection + ", shipmentProductDetail=" + shipmentProductDetail + ", shipmentInfoVo=" + shipmentInfoVo + ", errorMessage=" + errorMessage + ", quoteVo=" + quoteVo + ", pieces=" + pieces + "]";
    }

}
