package com.gms.xms.dto.edigenerate;

import com.gms.xms.txndb.vo.tnt.TntConnoteVo;

/**
 * Posted from Sep 22, 2016 11:44:51 AM
 * <p>
 * Author dattrinh
 */
public class TntShipmentDetailForEtVo extends TntConnoteVo {

    private static final long serialVersionUID = 1L;

    private String customerCode;
    private Integer billingType;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getBillingType() {
        return billingType;
    }

    public void setBillingType(Integer billingType) {
        this.billingType = billingType;
    }
}
