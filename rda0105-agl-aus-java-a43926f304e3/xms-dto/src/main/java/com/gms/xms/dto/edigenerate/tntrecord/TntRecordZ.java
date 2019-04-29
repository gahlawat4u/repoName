package com.gms.xms.dto.edigenerate.tntrecord;

/**
 * Posted from Sep 23, 2016 11:35:44 AM
 * <p>
 * Author dattrinh
 */
public class TntRecordZ extends TntRecordBase {
    private String transmissionIdentifier;
    private String recordCheckCount;

    @Override
    public boolean isValidRow() {
        boolean isValid = !"NO".equalsIgnoreCase(this.getRecordType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getTransmissionIdentifier());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getRecordCheckCount());
        return isValid;
    }

    @Override
    public String getRow() {
        String row = "";
        row += this.getRecordType();
        row += this.getTransmissionIdentifier();
        row += this.getRecordCheckCount();
        row += "\n";
        return row;
    }

    public String getTransmissionIdentifier() {
        return getRecord(transmissionIdentifier, "CHAR", 20, "BF");
    }

    public void setTransmissionIdentifier(String transmissionIdentifier) {
        this.transmissionIdentifier = transmissionIdentifier;
    }

    public String getRecordCheckCount() {
        return getRecord(recordCheckCount, "NUM", 5, "ZF");
    }

    public void setRecordCheckCount(String recordCheckCount) {
        this.recordCheckCount = recordCheckCount;
    }
}
