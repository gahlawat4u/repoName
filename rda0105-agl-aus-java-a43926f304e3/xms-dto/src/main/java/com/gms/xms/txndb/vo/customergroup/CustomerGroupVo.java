package com.gms.xms.txndb.vo.customergroup;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from CustomerGroupVo
 * <p>
 * Author DatTV Sep 10, 2015
 */
public class CustomerGroupVo extends BaseVo {

    private static final long serialVersionUID = -411686853413089697L;

    private Integer customerGroupId;
    private String customerGroupName;

    @Override
    public String toString() {
        return "CustomerGroupVo [customerGroupId=" + customerGroupId + ", customerGroupName=" + customerGroupName + "]";
    }

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }
}
