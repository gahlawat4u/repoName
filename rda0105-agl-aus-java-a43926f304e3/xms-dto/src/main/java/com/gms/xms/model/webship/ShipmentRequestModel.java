package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.ContentDetailModel;
import com.gms.xms.model.webship.ship.QuoteModel;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;

import java.util.List;

/**
 * Posted from ShipmentRequestModel
 * <p>
 * Author HungNT Date Apr 25, 2015
 */
public class ShipmentRequestModel extends BaseModel {
    private static final long serialVersionUID = -3555669342651970150L;

    private ShipmentInfoModel shipmentInfo;
    private QuoteModel quote;
    private ContentDetailModel contentDetail;
    private String shipmentReference;
    private String scheduleCollectionSelect;
    private ScheduleCollectionModel scheduleCollection;
    private WebshipLoginVo webshipLogin;
    private List<ShipmentProductDetailModel> shipmentProductDetails;
    private String selCollection;
    private String selCommercial;
    private String selPackingList;

    public List<ShipmentProductDetailModel> getShipmentProductDetails() {
        return shipmentProductDetails;
    }

    public void setShipmentProductDetails(List<ShipmentProductDetailModel> shipmentProductDetails) {
        this.shipmentProductDetails = shipmentProductDetails;
    }

    public WebshipLoginVo getWebshipLogin() {
        return webshipLogin;
    }

    public void setWebshipLogin(WebshipLoginVo webshipLogin) {
        this.webshipLogin = webshipLogin;
    }

    public ScheduleCollectionModel getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(ScheduleCollectionModel scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public ShipmentInfoModel getShipmentInfo() {
        return shipmentInfo;
    }

    public void setShipmentInfo(ShipmentInfoModel shipmentInfo) {
        this.shipmentInfo = shipmentInfo;
    }

    public QuoteModel getQuote() {
        return quote;
    }

    public void setQuote(QuoteModel quote) {
        this.quote = quote;
    }

    public ContentDetailModel getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(ContentDetailModel contentDetail) {
        this.contentDetail = contentDetail;
    }

    public String getShipmentReference() {
        return shipmentReference;
    }

    public void setShipmentReference(String shipmentReference) {
        this.shipmentReference = shipmentReference;
    }

    public String getScheduleCollectionSelect() {
        return scheduleCollectionSelect;
    }

    public void setScheduleCollectionSelect(String scheduleCollectionSelect) {
        this.scheduleCollectionSelect = scheduleCollectionSelect;
    }

    public String getSelCollection() {
        return selCollection;
    }

    public void setSelCollection(String selCollection) {
        this.selCollection = selCollection;
    }

    public String getSelCommercial() {
        return selCommercial;
    }

    public void setSelCommercial(String selCommercial) {
        this.selCommercial = selCommercial;
    }

    public String getSelPackingList() {
        return selPackingList;
    }

    public void setSelPackingList(String selPackingList) {
        this.selPackingList = selPackingList;
    }

    @Override
    public String toString() {
        return "ShipmentRequestModel [shipmentInfo=" + shipmentInfo + ", quote=" + quote + ", contentDetail=" + contentDetail + ", shipmentReference=" + shipmentReference + ", scheduleCollection=" + scheduleCollection + ", webshipLogin=" + webshipLogin + ", shipmentProductDetails=" + shipmentProductDetails + ", selCollection=" + selCollection + ", selCommercial=" + selCommercial + ", selPackingList=" + selPackingList + "]";
    }

}
