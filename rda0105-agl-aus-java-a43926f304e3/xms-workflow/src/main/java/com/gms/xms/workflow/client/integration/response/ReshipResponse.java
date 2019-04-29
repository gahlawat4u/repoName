package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.ShipmentInfoVo;


/**
 * Posted from ReshipResponse
 * <p>
 * Author TanDT Date Apr 17, 2015
 */
public class ReshipResponse extends BaseResponse {

    private ShipmentInfoVo shipmentInfoVo;

    public ShipmentInfoVo getShipmentInfoVo() {
        return shipmentInfoVo;
    }

    public void setShipmentInfoVo(ShipmentInfoVo shipmentInfoVo) {
        this.shipmentInfoVo = shipmentInfoVo;
    }
}
