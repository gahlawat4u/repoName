package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;

/**
 * Posted from CustomerDefaultSettingRequest
 * <p>
 * Author HungNT Date Apr 10, 2015
 */
public class CustomerDefaultSettingRequest extends BaseRequest {
    private CustomerDefaultSettingVo customerDefaultSettingVo;

    public CustomerDefaultSettingVo getCustomerDefaultSettingVo() {
        return customerDefaultSettingVo;
    }

    public void setCustomerDefaultSettingVo(CustomerDefaultSettingVo customerDefaultSettingVo) {
        this.customerDefaultSettingVo = customerDefaultSettingVo;
    }
}