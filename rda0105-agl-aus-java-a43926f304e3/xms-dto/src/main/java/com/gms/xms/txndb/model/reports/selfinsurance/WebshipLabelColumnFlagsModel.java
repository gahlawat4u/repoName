package com.gms.xms.txndb.model.reports.selfinsurance;

import com.gms.xms.model.BaseModel;

public class WebshipLabelColumnFlagsModel extends BaseModel {
    private static final long serialVersionUID = 8972271102150085744L;
    private String hasCreateDate;
    private String hasShipDate;
    private String hasPickupDate;
    private String hasPieces;
    private String hasDeadWeight;
    private String hasDimension;
    private String hasWeight;
    private String hasQuoted;
    private String hasSender;
    private String hasReceiver;

    public String getHasCreateDate() {
        return hasCreateDate;
    }

    public void setHasCreateDate(String hasCreateDate) {
        this.hasCreateDate = hasCreateDate;
    }

    public String getHasShipDate() {
        return hasShipDate;
    }

    public void setHasShipDate(String hasShipDate) {
        this.hasShipDate = hasShipDate;
    }

    public String getHasPickupDate() {
        return hasPickupDate;
    }

    public void setHasPickupDate(String hasPickupDate) {
        this.hasPickupDate = hasPickupDate;
    }

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

    public String getHasWeight() {
        return hasWeight;
    }

    public void setHasWeight(String hasWeight) {
        this.hasWeight = hasWeight;
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
        return "WebshipLabelColumnFlagsModel [hasCreateDate=" + hasCreateDate + ", hasShipDate=" + hasShipDate + ", hasPickupDate=" + hasPickupDate + ", hasPieces=" + hasPieces + ", hasDeadWeight=" + hasDeadWeight + ", hasDimension=" + hasDimension + ", hasWeight=" + hasWeight + ", hasQuoted=" + hasQuoted + ", hasSender=" + hasSender + ", hasReceiver=" + hasReceiver + "]";
    }
}