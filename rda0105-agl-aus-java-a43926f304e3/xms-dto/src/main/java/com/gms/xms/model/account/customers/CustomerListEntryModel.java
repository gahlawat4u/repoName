package com.gms.xms.model.account.customers;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustomerListEntryModel
 * <p>
 * Author DatTV Sep 7, 2015
 */
public class CustomerListEntryModel extends BaseModel {

    private static final long serialVersionUID = 7924470228862592157L;

    private String customerCode;
    private String customerName;
    private String mtd;
    private String ytd;
    private String lastShipmentDate;

    @Override
    public String toString() {
        return "CustomerListEntryModel [customerCode=" + customerCode + ", customerName=" + customerName + ", mtd=" + mtd + ", ytd=" + ytd + ", lastShipmentDate=" + lastShipmentDate + "]";
    }

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

    public String getMtd() {
        return mtd;
    }

    public void setMtd(String mtd) {
        this.mtd = mtd;
    }

    public String getYtd() {
        return ytd;
    }

    public void setYtd(String ytd) {
        this.ytd = ytd;
    }

    public String getLastShipmentDate() {
        return lastShipmentDate;
    }

    public void setLastShipmentDate(String lastShipmentDate) {
        this.lastShipmentDate = lastShipmentDate;
    }
}
