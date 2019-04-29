package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.CarrierSuburbFilter;

/**
 * Posted from CarrierSuburbRequest
 * <p>
 * Author HungNT Date Apr 21, 2015
 */
public class CarrierSuburbRequest extends BaseRequest {
    private CarrierSuburbFilter carrierSuburbFilter;

    public CarrierSuburbFilter getCarrierSuburbFilter() {
        return carrierSuburbFilter;
    }

    public void setCarrierSuburbFilter(CarrierSuburbFilter carrierSuburbFilter) {
        this.carrierSuburbFilter = carrierSuburbFilter;
    }
}
