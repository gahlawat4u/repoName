package com.gms.xms.txndb.vo.reports.selfinsurance;

import com.gms.xms.txndb.vo.BaseVo;

public class WebshipLabelColumnFlagsVo extends BaseVo {
    private static final long serialVersionUID = -4155956868885508399L;

    private Boolean hasCreateDate;
    private Boolean hasShipDate;
    private Boolean hasPickupDate;
    private Boolean hasPieces;
    private Boolean hasDeadWeight;
    private Boolean hasDimension;
    private Boolean hasWeight;
    private Boolean hasQuoted;
    private Boolean hasSender;
    private Boolean hasReceiver;

    public Boolean getHasCreateDate() {
        return hasCreateDate;
    }

    public void setHasCreateDate(Boolean hasCreateDate) {
        this.hasCreateDate = hasCreateDate;
    }

    public Boolean getHasShipDate() {
        return hasShipDate;
    }

    public void setHasShipDate(Boolean hasShipDate) {
        this.hasShipDate = hasShipDate;
    }

    public Boolean getHasPickupDate() {
        return hasPickupDate;
    }

    public void setHasPickupDate(Boolean hasPickupDate) {
        this.hasPickupDate = hasPickupDate;
    }

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

    public Boolean getHasWeight() {
        return hasWeight;
    }

    public void setHasWeight(Boolean hasWeight) {
        this.hasWeight = hasWeight;
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
        return "WebshipLabelColumnFlagsVo [hasCreateDate=" + hasCreateDate + ", hasShipDate=" + hasShipDate + ", hasPickupDate=" + hasPickupDate + ", hasPieces=" + hasPieces + ", hasDeadWeight=" + hasDeadWeight + ", hasDimension=" + hasDimension + ", hasWeight=" + hasWeight + ", hasQuoted=" + hasQuoted + ", hasSender=" + hasSender + ", hasReceiver=" + hasReceiver + "]";
    }
}