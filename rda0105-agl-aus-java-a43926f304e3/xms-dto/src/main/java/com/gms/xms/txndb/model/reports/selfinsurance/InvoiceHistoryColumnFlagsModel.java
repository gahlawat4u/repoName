package com.gms.xms.txndb.model.reports.selfinsurance;

import com.gms.xms.model.BaseModel;

public class InvoiceHistoryColumnFlagsModel extends BaseModel {
    private static final long serialVersionUID = -4678801754806139882L;

    private String hasPieces;
    private String hasDeadWeight;
    private String hasDimension;
    private String hasQuoted;
    private String hasSender;
    private String hasReceiver;

    public String getHasPieces() {
        return hasPieces;
    }

    public void setHasPieces(String hasPieces) {
        this.hasPieces = hasPieces;
    }

    public String getHasDeadWeight() {
        return hasDeadWeight;
    }

    public void setHasDeadWeight(String hasDeadWeight) {
        this.hasDeadWeight = hasDeadWeight;
    }

    public String getHasDimension() {
        return hasDimension;
    }

    public void setHasDimension(String hasDimension) {
        this.hasDimension = hasDimension;
    }

    public String getHasQuoted() {
        return hasQuoted;
    }

    public void setHasQuoted(String hasQuoted) {
        this.hasQuoted = hasQuoted;
    }

    public String getHasSender() {
        return hasSender;
    }

    public void setHasSender(String hasSender) {
        this.hasSender = hasSender;
    }

    public String getHasReceiver() {
        return hasReceiver;
    }

    public void setHasReceiver(String hasReceiver) {
        this.hasReceiver = hasReceiver;
    }

    @Override
    public String toString() {
        return "InvoiceHistoryColumnFlagsModel [hasPieces=" + hasPieces + ", hasDeadWeight=" + hasDeadWeight + ", hasDimension=" + hasDimension + ", hasQuoted=" + hasQuoted + ", hasSender=" + hasSender + ", hasReceiver=" + hasReceiver + "]";
    }
}