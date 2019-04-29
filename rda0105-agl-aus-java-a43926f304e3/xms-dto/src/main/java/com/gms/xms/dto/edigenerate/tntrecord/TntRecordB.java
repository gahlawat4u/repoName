package com.gms.xms.dto.edigenerate.tntrecord;

import com.gms.xms.common.constants.AppConstants.PADDING_TYPE;

/**
 * Posted from Sep 23, 2016 11:35:44 AM
 * <p>
 * Author dattrinh
 */
public class TntRecordB extends TntRecordBase {
    private String manifestIdentifier;
    private String senderIdentifierCode;
    private String senderAccountNumber;
    private String senderName;
    private String senderAddressLine1;
    private String senderAddressLine2;
    private String senderSuburb;
    private String senderState;
    private String senderPostCode;
    private String senderContactName;
    private String senderContactPhone;

    @Override
    public boolean isValidRow() {
        boolean isValid = !"NO".equalsIgnoreCase(this.getRecordType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getManifestIdentifier());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSenderIdentifierCode());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSenderAccountNumber());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSenderName());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSenderAddressLine1());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSenderAddressLine2());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSenderSuburb());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSenderState());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSenderPostCode());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSenderContactName());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSenderContactPhone());
        return isValid;
    }

    @Override
    public String getRow() {
        String row = "";
        row += this.getRecordType();
        row += this.getManifestIdentifier();
        row += this.getSenderIdentifierCode();
        row += this.getSenderAccountNumber();
        row += this.getSenderName();
        row += this.getSenderAddressLine1();
        row += this.getSenderAddressLine2();
        row += this.getSenderSuburb();
        row += this.getSenderState();
        row += this.getSenderPostCode();
        row += this.getSenderContactName();
        row += this.getSenderContactPhone();
        row += "\n";
        return row;
    }

    public String getManifestIdentifier() {
        return getRecord(manifestIdentifier, "CHAR", 10, "BF");
    }

    public void setManifestIdentifier(String manifestIdentifier) {
        this.manifestIdentifier = manifestIdentifier;
    }

    public String getSenderIdentifierCode() {
        return getRecord(senderIdentifierCode, "CHAR", 15, "BF", PADDING_TYPE.RIGHT);
    }

    public void setSenderIdentifierCode(String senderIdentifierCode) {
        this.senderIdentifierCode = senderIdentifierCode;
    }

    public String getSenderAccountNumber() {
        return getRecord(senderAccountNumber, "CHAR", 10, "BF", PADDING_TYPE.RIGHT);
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public String getSenderName() {
        return getRecord(senderName, "CHAR", 30, "BF", PADDING_TYPE.RIGHT);
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddressLine1() {
        return getRecord(senderAddressLine1, "CHAR", 30, "BF", PADDING_TYPE.RIGHT);
    }

    public void setSenderAddressLine1(String senderAddressLine1) {
        this.senderAddressLine1 = senderAddressLine1;
    }

    public String getSenderAddressLine2() {
        return getRecord(senderAddressLine2, "CHAR", 30, "BF", PADDING_TYPE.RIGHT);
    }

    public void setSenderAddressLine2(String senderAddressLine2) {
        this.senderAddressLine2 = senderAddressLine2;
    }

    public String getSenderSuburb() {
        return getRecord(senderSuburb, "CHAR", 20, "BF", PADDING_TYPE.RIGHT);
    }

    public void setSenderSuburb(String senderSuburb) {
        this.senderSuburb = senderSuburb;
    }

    public String getSenderState() {
        return getRecord(senderState, "CHAR", 3, "BF", PADDING_TYPE.RIGHT);
    }

    public void setSenderState(String senderState) {
        this.senderState = senderState;
    }

    public String getSenderPostCode() {
        return getRecord(senderPostCode, "CHAR", 4, "BF", PADDING_TYPE.RIGHT);
    }

    public void setSenderPostCode(String senderPostCode) {
        this.senderPostCode = senderPostCode;
    }

    public String getSenderContactName() {
        return getRecord(senderContactName, "CHAR", 20, "BF", PADDING_TYPE.RIGHT);
    }

    public void setSenderContactName(String senderContactName) {
        this.senderContactName = senderContactName;
    }

    public String getSenderContactPhone() {
        return getRecord(senderContactPhone, "CHAR", 13, "BF", PADDING_TYPE.RIGHT);
    }

    public void setSenderContactPhone(String senderContactPhone) {
        this.senderContactPhone = senderContactPhone;
    }
}
