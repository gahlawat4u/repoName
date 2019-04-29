package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.ServiceVo;

/**
 * Posted from ServiceResponse
 * <p>
 * Author HungNT Date Apr 16, 2015
 */
public class ServiceResponse extends BaseResponse {
    private ServiceVo serviceVo;

    public ServiceVo getServiceVo() {
        return serviceVo;
    }

    public void setServiceVo(ServiceVo serviceVo) {
        this.serviceVo = serviceVo;
    }
}
