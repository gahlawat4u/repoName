package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.dto.delivery.DhlCapabilityVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ship.QuoteVo;

/**
 * Posted from GetQuoteResponse
 * <p>
 * Author HungNT Date Apr 18, 2015
 */
public class GetQuoteResponse extends BaseResponse {
    private QuoteVo quoteVo;
    private ShipmentInfoVo shipmentInfoVo;
    private DhlCapabilityVo dhlCapabilityVo;
    private Boolean isOtherCarrier;
    private Boolean isUpsLargeCharger;

    public QuoteVo getQuoteVo() {
        return quoteVo;
    }

    public void setQuoteVo(QuoteVo quoteVo) {
        this.quoteVo = quoteVo;
    }

    public ShipmentInfoVo getShipmentInfoVo() {
        return shipmentInfoVo;
    }

    public void setShipmentInfoVo(ShipmentInfoVo shipmentInfoVo) {
        this.shipmentInfoVo = shipmentInfoVo;
    }

    public DhlCapabilityVo getDhlCapabilityVo() {
        return dhlCapabilityVo;
    }

    public void setDhlCapabilityVo(DhlCapabilityVo dhlCapabilityVo) {
        this.dhlCapabilityVo = dhlCapabilityVo;
    }

    public Boolean getIsOtherCarrier() {
        return isOtherCarrier;
    }

    public void setIsOtherCarrier(Boolean isOtherCarrier) {
        this.isOtherCarrier = isOtherCarrier;
    }

    public Boolean getUpsLargeCharger() {
        return isUpsLargeCharger;
    }

    public void setUpsLargeCharger(Boolean upsLargeCharger) {
        isUpsLargeCharger = upsLargeCharger;
    }
}
