package com.gms.xms.txndb.vo.admin.quicksearch;

import com.gms.xms.txndb.vo.InvoiceVo;

/**
 * Posted from Apr 27, 2016 2:37:55 PM
 * <p>
 * Author huynd
 */
public class QuickSearchInvoiceVo extends InvoiceVo {

    private static final long serialVersionUID = 1L;

    private Long airbillCount;
    private Double margin;
    private Double total;

    public Long getAirbillCount() {
        return airbillCount;
    }

    public void setAirbillCount(Long airbillCount) {
        this.airbillCount = airbillCount;
    }

    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "QuickSearchInvoiceVo [airbillCount=" + airbillCount + ", margin=" + margin + ", total=" + total + "]";
    }
}
