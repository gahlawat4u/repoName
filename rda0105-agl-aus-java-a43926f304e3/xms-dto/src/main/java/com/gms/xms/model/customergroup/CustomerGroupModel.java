package com.gms.xms.model.customergroup;

import com.gms.xms.model.BaseModel;

public class CustomerGroupModel extends BaseModel {

    private static final long serialVersionUID = -2051915283806116035L;

    private String customerGroupId;
    private String customerGroupName;

    @Override
    public String toString() {
        return "CustomerGroupModel [customerGroupId=" + customerGroupId + ", customerGroupName=" + customerGroupName + "]";
    }

    public String getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(String customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }
}
