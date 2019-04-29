package com.gms.xms.txndb.vo.reports.selfinsurance;

import com.gms.xms.txndb.vo.BaseVo;

public class InvoiceHistoryColumnFlagsVo extends BaseVo {
    private static final long serialVersionUID = 3816715607311741087L;

    private Boolean hasPieces;
    private Boolean hasDeadWeight;
    private Boolean hasDimension;
    private Boolean hasQuoted;
    private Boolean hasSender;
    private Boolean hasReceiver;

    public Boolean getHasPieces() {
        return hasPieces;
    }

    public void setHasPieces(Boolean hasPieces) {
        this.hasPieces = hasPieces;
    }

    public Boolean getHasDeadWeight() {
        return hasDeadWeight;
    }

    public void setHasDeadWeight(Boolean hasDeadWeight) {
        this.hasDeadWeight = hasDeadWeight;
    }

    public Boolean getHasDimension() {
        return hasDimension;
    }

    public void setHasDimension(Boolean hasDimension) {
        this.hasDimension = hasDimension;
    }

    public Boolean getHasQuoted() {
        return hasQuoted;
    }

    public void setHasQuoted(Boolean hasQuoted) {
        this.hasQuoted = hasQuoted;
    }

    public Boolean getHasSender() {
        return hasSender;
    }

    public void setHasSender(Boolean hasSender) {
        this.hasSender = hasSender;
    }

    public Boolean getHasReceiver() {
        return hasReceiver;
    }

    public void setHasReceiver(Boolean hasReceiver) {
        this.hasReceiver = hasReceiver;
    }

    @Override
    public String toString() {
        return "InvoiceHistoryColumnFlagsVo [hasPieces=" + hasPieces + ", hasDeadWeight=" + hasDeadWeight + ", hasDimension=" + hasDimension + ", hasQuoted=" + hasQuoted + ", hasSender=" + hasSender + ", hasReceiver=" + hasReceiver + "]";
    }
}