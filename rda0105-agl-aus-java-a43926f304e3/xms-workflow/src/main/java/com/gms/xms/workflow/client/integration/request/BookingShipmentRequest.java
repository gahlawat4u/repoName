package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.model.webship.ship.QuoteModel;
import com.gms.xms.txndb.vo.ContentDetailVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;

/**
 * Posted from BookingShipmentRequest
 * <p>
 * Author TANDT
 */
public class BookingShipmentRequest extends BaseRequest {
    private ShipmentInfoVo shipmentInfoVo;
    private WebshipLoginVo webshipLoginVo;
    private QuoteModel quoteModel;
    private ContentDetailVo contentDetail;
    private ScheduleCollectionVo scheduleCollection;

    public ScheduleCollectionVo getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(ScheduleCollectionVo scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public ContentDetailVo getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(ContentDetailVo contentDetail) {
        this.contentDetail = contentDetail;
    }

    public QuoteModel getQuoteModel() {
        return quoteModel;
    }

    public void setQuoteModel(QuoteModel quoteModel) {
        this.quoteModel = quoteModel;
    }

    public ShipmentInfoVo getShipmentInfoVo() {
        return shipmentInfoVo;
    }

    public void setShipmentInfoVo(ShipmentInfoVo shipmentInfoVo) {
        this.shipmentInfoVo = shipmentInfoVo;
    }

    public WebshipLoginVo getWebshipLoginVo() {
        return webshipLoginVo;
    }

    public void setWebshipLoginVo(WebshipLoginVo webshipLoginVo) {
        this.webshipLoginVo = webshipLoginVo;
    }
}
