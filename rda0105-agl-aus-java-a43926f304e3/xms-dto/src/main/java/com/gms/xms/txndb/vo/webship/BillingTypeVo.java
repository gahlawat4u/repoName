package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from BillingTypeVo
 * <p>
 * Author HungNT Date Mar 25, 2015
 */
public class BillingTypeVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -2283097098889900549L;

    private Integer billingId;

    private String billingName;

    private Long localizationId;

    public Integer getBillingId() {
        return billingId;
    }

    public void setBillingId(Integer billingId) {
        this.billingId = billingId;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName == null ? null : billingName.trim();
    }

    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationid) {
        this.localizationId = localizationid;
    }
}