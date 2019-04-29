package com.gms.xms.dto.edigenerate.tntrecord;

import com.gms.xms.common.constants.AppConstants.PADDING_TYPE;

/**
 * Posted from Sep 23, 2016 11:35:44 AM
 * <p>
 * Author dattrinh
 */
public class TntRecordJ extends TntRecordBase {
    private String manifestIdentifier;
    private String conNoteNumber;
    private String alertType;
    private String addressType;
    private String address;

    @Override
    public boolean isValidRow() {
        boolean isValid = !"NO".equalsIgnoreCase(this.getRecordType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getManifestIdentifier());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getConNoteNumber());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getAlertType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getAddressType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getAddress());
        return isValid;
    }

    @Override
    public String getRow() {
        String row = "";
        row += this.getRecordType();
        row += this.getManifestIdentifier();
        row += this.getConNoteNumber();
        row += this.getAlertType();
        row += this.getAddressType();
        row += this.getAddress();
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

    public String getAlertType() {
        return getRecord(alertType, "CHAR", 2, "BF");
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAddressType() {
        return getRecord(addressType, "CHAR", 1, "BF");
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddress() {
        return getRecord(address, "CHAR", 80, "BF");
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
