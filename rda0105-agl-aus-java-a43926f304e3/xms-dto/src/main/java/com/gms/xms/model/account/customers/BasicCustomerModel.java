package com.gms.xms.model.account.customers;

import com.gms.xms.model.BaseModel;
import org.apache.commons.lang.StringUtils;

/**
 * Posted from BasicCustomerModel
 * <p>
 * Author DatTV Sep 9, 2015
 */
public class BasicCustomerModel extends BaseModel {
    private static final long serialVersionUID = 9009191474627206356L;

    private String customerCode;
    private String customerName;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDisplayName() {
        String displayName = "";
        boolean isOption = "-1".equalsIgnoreCase(this.getCustomerCode()) || "0".equalsIgnoreCase(this.getCustomerCode());
        if (isOption) {
            displayName = this.getCustomerName();
        } else {
            displayName = this.getCustomerCode();
            if (!StringUtils.isBlank(this.getCustomerName())) {
                displayName += " - " + this.getCustomerName();
            }
        }
        return displayName;
    }

    @Override
    public String toString() {
        return "BasicCustomerModel [customerCode=" + customerCode + ", customerName=" + customerName + "]";
    }
}
