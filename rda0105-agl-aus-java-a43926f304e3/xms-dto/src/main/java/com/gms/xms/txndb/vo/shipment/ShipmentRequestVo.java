package com.gms.xms.txndb.vo.shipment;

import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.ship.QuoteVo;

import java.util.List;

/**
 * Posted from ShipmentRequestVo
 * <p>
 * Author HungNT Date Apr 25, 2015
 */
public class ShipmentRequestVo extends BaseVo {
    private static final long serialVersionUID = -7587784902798380167L;

    private ShipmentInfoVo shipmentInfo;
    private QuoteVo quote;
    private ContentDetailVo contentDetail;
    private String shipmentReference;
    private String scheduleCollectionSelect;
    private ScheduleCollectionVo scheduleCollection;
    private WebshipLoginVo webshipLogin;
    private List<ShipmentProductDetailVo> shipmentProductDetails;
    private Integer selCollection;
    private Integer selCommercial;
    private Integer selPackingList;

    public List<ShipmentProductDetailVo> getShipmentProductDetails() {
        return shipmentProductDetails;
    }

    public void setShipmentProductDetails(List<ShipmentProductDetailVo> shipmentProductDetails) {
        this.shipmentProductDetails = shipmentProductDetails;
    }

    public WebshipLoginVo getWebshipLogin() {
        return webshipLogin;
    }

    public void setWebshipLogin(WebshipLoginVo webshipLogin) {
        this.webshipLogin = webshipLogin;
    }

    public String getScheduleCollectionSelect() {
        return scheduleCollectionSelect;
    }

    public void setScheduleCollectionSelect(String scheduleCollectionSelect) {
        this.scheduleCollectionSelect = scheduleCollectionSelect;
    }

    public ScheduleCollectionVo getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(ScheduleCollectionVo scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public ShipmentInfoVo getShipmentInfo() {
        return shipmentInfo;
    }

    public void setShipmentInfo(ShipmentInfoVo shipmentInfo) {
        this.shipmentInfo = shipmentInfo;
    }

    public QuoteVo getQuote() {
        return quote;
    }

    public void setQuote(QuoteVo quote) {
        this.quote = quote;
    }

    public ContentDetailVo getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(ContentDetailVo contentDetail) {
        this.contentDetail = contentDetail;
    }

    public String getShipmentReference() {
        return shipmentReference;
    }

    public void setShipmentReference(String shipmentReference) {
        this.shipmentReference = shipmentReference;
    }

    public Integer getSelCollection() {
        return selCollection;
    }

    public void setSelCollection(Integer selCollection) {
        this.selCollection = selCollection;
    }

    public Integer getSelCommercial() {
        return selCommercial;
    }

    public void setSelCommercial(Integer selCommercial) {
        this.selCommercial = selCommercial;
    }

    public Integer getSelPackingList() {
        return selPackingList;
    }

    public void setSelPackingList(Integer selPackingList) {
        this.selPackingList = selPackingList;
    }

    @Override
    public String toString() {
        return "ShipmentRequestVo [shipmentInfo=" + shipmentInfo + ", quote=" + quote + ", contentDetail=" + contentDetail + ", shipmentReference=" + shipmentReference + ", scheduleCollection=" + scheduleCollection + ", webshipLogin=" + webshipLogin + ", shipmentProductDetails=" + shipmentProductDetails + ", selCollection=" + selCollection + ", selCommercial=" + selCommercial + ", selPackingList=" + selPackingList + "]";
    }

}
