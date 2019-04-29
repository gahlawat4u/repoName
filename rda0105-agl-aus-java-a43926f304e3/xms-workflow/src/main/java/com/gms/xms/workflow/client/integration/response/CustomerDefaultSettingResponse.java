package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;

/**
 * Posted from CustomerDefaultSettingRequest
 * <p>
 * Author HungNT Date Apr 10, 2015
 */
public class CustomerDefaultSettingResponse extends BaseResponse {
    private CustomerDefaultSettingVo customerDefaultSettingVo;

    public CustomerDefaultSettingVo getCustomerDefaultSettingVo() {
        return customerDefaultSettingVo;
    }

    public void setCustomerDefaultSettingVo(CustomerDefaultSettingVo customerDefaultSettingVo) {
        this.customerDefaultSettingVo = customerDefaultSettingVo;
    }
}