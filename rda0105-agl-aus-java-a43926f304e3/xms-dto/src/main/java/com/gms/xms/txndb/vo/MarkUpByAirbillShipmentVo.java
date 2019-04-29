package com.gms.xms.txndb.vo;

/**
 * Posted from Jul 25, 2016 10:39:01 AM
 * <p>
 * Author huynd
 */
public class MarkUpByAirbillShipmentVo extends InvoiceVo {

    private static final long serialVersionUID = 1L;

    private Double markupRate;

    public Double getMarkupRate() {
        return markupRate;
    }

    public void setMarkupRate(Double markupRate) {
        this.markupRate = markupRate;
    }

    @Override
    public String toString() {
        return "MarkUpByAirbillShipmentVo [markupRate=" + markupRate + "]";
    }

}
