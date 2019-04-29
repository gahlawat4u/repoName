package com.gms.xms.model.admin.quicksearch;

import com.gms.xms.model.InvoiceModel;

/**
 * Posted from Apr 27, 2016 2:40:26 PM
 * <p>
 * Author huynd
 */
public class QuickSearchInvoiceModel extends InvoiceModel {

    private static final long serialVersionUID = 1L;

    private String airbillCount;
    private String margin;
    private String total;

    public String getAirbillCount() {
        return airbillCount;
    }

    public void setAirbillCount(String airbillCount) {
        this.airbillCount = airbillCount;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "QuickSearchInvoiceModel [airbillCount=" + airbillCount + ", margin=" + margin + ", total=" + total + "]";
    }
}
