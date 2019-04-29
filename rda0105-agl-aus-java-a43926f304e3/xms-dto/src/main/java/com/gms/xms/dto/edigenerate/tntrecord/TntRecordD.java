package com.gms.xms.dto.edigenerate.tntrecord;

import com.gms.xms.common.constants.AppConstants.PADDING_TYPE;

/**
 * Posted from Sep 23, 2016 11:35:44 AM
 * <p>
 * Author dattrinh
 */
public class TntRecordD extends TntRecordBase {
    private String manifestIdentifier;
    private String conNoteNumber;
    private String thirdPartyIdentifierCode;
    private String thirdPartyAccountNumber;
    private String thirdPartyName;
    private String thirdPartyAddressLine1;
    private String thirdPartyAddressLine2;
    private String thirdPartySuburb;
    private String thirdPartyState;
    private String thirdPartyPostCode;

    @Override
    public boolean isValidRow() {
        boolean isValid = !"NO".equalsIgnoreCase(this.getRecordType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getManifestIdentifier());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getConNoteNumber());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getThirdPartyIdentifierCode());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getThirdPartyAccountNumber());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getThirdPartyName());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getThirdPartyAddressLine1());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getThirdPartyAddressLine2());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getThirdPartySuburb());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getThirdPartyState());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getThirdPartyPostCode());
        return isValid;
    }

    @Override
    public String getRow() {
        String row = "";
        row += this.getRecordType();
        row += this.getManifestIdentifier();
        row += this.getConNoteNumber();
        row += this.getThirdPartyIdentifierCode();
        row += this.getThirdPartyAccountNumber();
        row += this.getThirdPartyName();
        row += this.getThirdPartyAddressLine1();
        row += this.getThirdPartyAddressLine2();
        row += this.getThirdPartySuburb();
        row += this.getThirdPartyState();
        row += this.getThirdPartyPostCode();
        row += "\n";
        return row;
    }

    public String getManifestIdentifier() {
        return getRecord(manifestIdentifier, "CHAR", 10, "BF");
    }

    public void setManifestIdentifier(String manifestIdentifier) {
        this.manifestIdentifier = manifestIdentifier;
    }

    public String getConNoteNumber() {
        return getRecord(conNoteNumber, "CHAR", 15, "BF", PADDING_TYPE.RIGHT);
    }

    public void setConNoteNumber(String conNoteNumber) {
        this.conNoteNumber = conNoteNumber;
    }

    public String getThirdPartyIdentifierCode() {
        return getRecord(thirdPartyIdentifierCode, "CHAR", 15, "BF", PADDING_TYPE.RIGHT);
    }

    public void setThirdPartyIdentifierCode(String thirdPartyIdentifierCode) {
        this.thirdPartyIdentifierCode = thirdPartyIdentifierCode;
    }

    public String getThirdPartyAccountNumber() {
        return getRecord(thirdPartyAccountNumber, "CHAR", 10, "BF", PADDING_TYPE.RIGHT);
    }

    public void setThirdPartyAccountNumber(String thirdPartyAccountNumber) {
        this.thirdPartyAccountNumber = thirdPartyAccountNumber;
    }

    public String getThirdPartyName() {
        return getRecord(thirdPartyName, "CHAR", 30, "BF", PADDING_TYPE.RIGHT);
    }

    public void setThirdPartyName(String thirdPartyName) {
        this.thirdPartyName = thirdPartyName;
    }

    public String getThirdPartyAddressLine1() {
        return getRecord(thirdPartyAddressLine1, "CHAR", 30, "BF", PADDING_TYPE.RIGHT);
    }

    public void setThirdPartyAddressLine1(String thirdPartyAddressLine1) {
        this.thirdPartyAddressLine1 = thirdPartyAddressLine1;
    }

    public String getThirdPartyAddressLine2() {
        return getRecord(thirdPartyAddressLine2, "CHAR", 30, "BF", PADDING_TYPE.RIGHT);
    }

    public void setThirdPartyAddressLine2(String thirdPartyAddressLine2) {
        this.thirdPartyAddressLine2 = thirdPartyAddressLine2;
    }

    public String getThirdPartySuburb() {
        return getRecord(thirdPartySuburb, "CHAR", 20, "BF", PADDING_TYPE.RIGHT);
    }

    public void setThirdPartySuburb(String thirdPartySuburb) {
        this.thirdPartySuburb = thirdPartySuburb;
    }

    public String getThirdPartyState() {
        return getRecord(thirdPartyState, "CHAR", 3, "BF", PADDING_TYPE.RIGHT);
    }

    public void setThirdPartyState(String thirdPartyState) {
        this.thirdPartyState = thirdPartyState;
    }

    public String getThirdPartyPostCode() {
        return getRecord(thirdPartyPostCode, "CHAR", 4, "BF", PADDING_TYPE.RIGHT);
    }

    public void setThirdPartyPostCode(String thirdPartyPostCode) {
        this.thirdPartyPostCode = thirdPartyPostCode;
    }
}
