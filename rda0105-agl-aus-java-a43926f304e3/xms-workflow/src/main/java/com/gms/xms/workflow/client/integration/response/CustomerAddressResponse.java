package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.CustomerAddressVo;

import java.util.List;

/**
 * Posted from CustomerAddressResponse
 * <p>
 * Author DatTV Date Apr 9, 2015 11:11:35 AM
 */
public class CustomerAddressResponse extends BaseResponse {
    private List<CustomerAddressVo> customerList;
    private CustomerAddressVo customerAddressVo;

    public List<CustomerAddressVo> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerAddressVo> customerList) {
        this.customerList = customerList;
    }

    public CustomerAddressVo getCustomerAddressVo() {
        return customerAddressVo;
    }

    public void setCustomerAddressVo(CustomerAddressVo customerAddressVo) {
        this.customerAddressVo = customerAddressVo;
    }
}
