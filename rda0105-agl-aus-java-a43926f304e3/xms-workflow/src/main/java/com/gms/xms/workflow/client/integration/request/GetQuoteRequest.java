package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;

/**
 * Posted from GetQuoteRequest
 * <p>
 * Author HungNT Date Apr 18, 2015
 */
public class GetQuoteRequest extends BaseRequest {
    private ShipmentInfoVo shipmentInfoVo;
    private WebshipLoginVo webshipLoginVo;

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
