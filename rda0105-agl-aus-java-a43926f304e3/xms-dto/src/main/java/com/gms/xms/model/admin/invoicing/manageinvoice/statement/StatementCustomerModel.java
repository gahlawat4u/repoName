package com.gms.xms.model.admin.invoicing.manageinvoice.statement;

import com.gms.xms.model.BaseModel;
import org.apache.commons.lang.StringUtils;

/**
 * Posted from StatementCustomerModel
 * <p>
 * Author dattrinh Mar 16, 2016 11:36:30 AM
 */
public class StatementCustomerModel extends BaseModel {

    private static final long serialVersionUID = 1L;

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
        String displayName = this.getCustomerCode();
        displayName = StringUtils.isBlank(this.getCustomerName()) ? displayName : displayName + " - " + this.getCustomerName();
        return displayName;
    }

    @Override
    public String toString() {
        return "StatementCustomerModel [customerCode=" + customerCode + ", customerName=" + customerName + "]";
    }
}
