package com.gms.xms.dto.edigenerate.tntrecord;

import com.gms.xms.common.constants.AppConstants.PADDING_TYPE;

/**
 * Posted from Sep 23, 2016 11:35:44 AM
 * <p>
 * Author dattrinh
 */
public class TntRecordH extends TntRecordBase {
    private String manifestIdentifier;
    private String conNoteNumber;
    private String sequence;
    private String itemIdentifier_1_9;
    private String itemIdentifier_2_10;
    private String itemIdentifier_3_11;
    private String itemIdentifier_4_12;
    private String itemIdentifier_5_13;
    private String itemIdentifier_6_14;
    private String itemIdentifier_7_15;
    private String itemIdentifier_8_16;

    @Override
    public boolean isValidRow() {
        boolean isValid = !"NO".equalsIgnoreCase(this.getRecordType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getManifestIdentifier());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getConNoteNumber());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSequence());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getItemIdentifier_1_9());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getItemIdentifier_2_10());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getItemIdentifier_3_11());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getItemIdentifier_4_12());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getItemIdentifier_5_13());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getItemIdentifier_6_14());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getItemIdentifier_7_15());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getItemIdentifier_8_16());
        return isValid;
    }

    @Override
    public String getRow() {
        String row = "";
        row += this.getRecordType();
        row += this.getManifestIdentifier();
        row += this.getConNoteNumber();
        row += this.getSequence();
        row += this.getItemIdentifier_1_9();
        row += this.getItemIdentifier_2_10();
        row += this.getItemIdentifier_3_11();
        row += this.getItemIdentifier_4_12();
        row += this.getItemIdentifier_5_13();
        row += this.getItemIdentifier_6_14();
        row += this.getItemIdentifier_7_15();
        row += this.getItemIdentifier_8_16();
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

    public String getSequence() {
        return getRecord(sequence, "NUM", 3, "ZF");
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getItemIdentifier_1_9() {
        return getRecord(itemIdentifier_1_9, "CHAR", 20, "BF");
    }

    public void setItemIdentifier_1_9(String itemIdentifier_1_9) {
        this.itemIdentifier_1_9 = itemIdentifier_1_9;
    }

    public String getItemIdentifier_2_10() {
        return getRecord(itemIdentifier_2_10, "CHAR", 20, "BF");
    }

    public void setItemIdentifier_2_10(String itemIdentifier_2_10) {
        this.itemIdentifier_2_10 = itemIdentifier_2_10;
    }

    public String getItemIdentifier_3_11() {
        return getRecord(itemIdentifier_3_11, "CHAR", 20, "BF");
    }

    public void setItemIdentifier_3_11(String itemIdentifier_3_11) {
        this.itemIdentifier_3_11 = itemIdentifier_3_11;
    }

    public String getItemIdentifier_4_12() {
        return getRecord(itemIdentifier_4_12, "CHAR", 20, "BF");
    }

    public void setItemIdentifier_4_12(String itemIdentifier_4_12) {
        this.itemIdentifier_4_12 = itemIdentifier_4_12;
    }

    public String getItemIdentifier_5_13() {
        return getRecord(itemIdentifier_5_13, "CHAR", 20, "BF");
    }

    public void setItemIdentifier_5_13(String itemIdentifier_5_13) {
        this.itemIdentifier_5_13 = itemIdentifier_5_13;
    }

    public String getItemIdentifier_6_14() {
        return getRecord(itemIdentifier_6_14, "CHAR", 20, "BF");
    }

    public void setItemIdentifier_6_14(String itemIdentifier_6_14) {
        this.itemIdentifier_6_14 = itemIdentifier_6_14;
    }

    public String getItemIdentifier_7_15() {
        return getRecord(itemIdentifier_7_15, "CHAR", 20, "BF");
    }

    public void setItemIdentifier_7_15(String itemIdentifier_7_15) {
        this.itemIdentifier_7_15 = itemIdentifier_7_15;
    }

    public String getItemIdentifier_8_16() {
        return getRecord(itemIdentifier_8_16, "CHAR", 20, "BF");
    }

    public void setItemIdentifier_8_16(String itemIdentifier_8_16) {
        this.itemIdentifier_8_16 = itemIdentifier_8_16;
    }
}
