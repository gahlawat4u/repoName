package com.gms.xms.dto.edigenerate.tntrecord;

import com.gms.xms.common.constants.AppConstants.PADDING_TYPE;

/**
 * Posted from Sep 23, 2016 11:35:44 AM
 * <p>
 * Author dattrinh
 */
public class TntRecordC extends TntRecordBase {
    private String manifestIdentifier;
    private String consignmentNoteNumber;
    private String linesThisConsignment;
    private String receiverIdentifierCode;
    private String receiverAccountNumber;
    private String receiverName;
    private String receiverAddressLine1;
    private String receiverAddressLine2;
    private String receiverSuburb;
    private String receiverState;
    private String receiverPostCode;
    private String receiverContactName;
    private String receiverContactPhone;
    private String conNoteDate;
    private String service;
    private String dangerousGoodsFlag;
    private String payerFlag;
    private String cancelledFlag;
    private String foodStuffsFlag;
    private String extendedWarrantyValue;
    private String extendedWarrantyClass;
    private String additionalService1;
    private String additionalService2;
    private String additionalService3;
    private String additionalService4;
    private String additionalService5;
    private String handRateAmount;
    private String otherCharges;
    private String customerConsignmentReference;

    @Override
    public boolean isValidRow() {
        boolean isValid = !"NO".equalsIgnoreCase(this.getRecordType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getManifestIdentifier());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getConsignmentNoteNumber());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getLinesThisConsignment());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getReceiverIdentifierCode());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getReceiverAccountNumber());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getReceiverName());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getReceiverAddressLine1());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getReceiverAddressLine2());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getReceiverSuburb());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getReceiverState());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getReceiverPostCode());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getReceiverContactName());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getReceiverContactPhone());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getConNoteDate());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getService());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getDangerousGoodsFlag());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getPayerFlag());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getCancelledFlag());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getFoodStuffsFlag());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getExtendedWarrantyValue());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getExtendedWarrantyClass());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getAdditionalService1());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getAdditionalService2());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getAdditionalService3());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getAdditionalService4());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getAdditionalService5());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getHandRateAmount());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getOtherCharges());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getCustomerConsignmentReference());
        return isValid;
    }

    @Override
    public String getRow() {
        String row = "";
        row += this.getRecordType();
        row += this.getManifestIdentifier();
        row += this.getConsignmentNoteNumber();
        row += this.getLinesThisConsignment();
        row += this.getReceiverIdentifierCode();
        row += this.getReceiverAccountNumber();
        row += this.getReceiverName();
        row += this.getReceiverAddressLine1();
        row += this.getReceiverAddressLine2();
        row += this.getReceiverSuburb();
        row += this.getReceiverState();
        row += this.getReceiverPostCode();
        row += this.getReceiverContactName();
        row += this.getReceiverContactPhone();
        row += this.getConNoteDate();
        row += this.getService();
        row += this.getDangerousGoodsFlag();
        row += this.getPayerFlag();
        row += this.getCancelledFlag();
        row += this.getFoodStuffsFlag();
        row += this.getExtendedWarrantyValue();
        row += this.getExtendedWarrantyClass();
        row += this.getAdditionalService1();
        row += this.getAdditionalService2();
        row += this.getAdditionalService3();
        row += this.getAdditionalService4();
        row += this.getAdditionalService5();
        row += this.getHandRateAmount();
        row += this.getOtherCharges();
        row += this.getCustomerConsignmentReference();
        row += "\n";
        return row;
    }

    public String getManifestIdentifier() {
        return getRecord(manifestIdentifier, "CHAR", 10, "BF");
    }

    public void setManifestIdentifier(String manifestIdentifier) {
        this.manifestIdentifier = manifestIdentifier;
    }

    public String getConsignmentNoteNumber() {
        return getRecord(consignmentNoteNumber, "CHAR", 15, "BF", PADDING_TYPE.RIGHT);
    }

    public void setConsignmentNoteNumber(String consignmentNoteNumber) {
        this.consignmentNoteNumber = consignmentNoteNumber;
    }

    public String getLinesThisConsignment() {
        return getRecord(linesThisConsignment, "NUM", 3, "ZF");
    }

    public void setLinesThisConsignment(String linesThisConsignment) {
        this.linesThisConsignment = linesThisConsignment;
    }

    public String getReceiverIdentifierCode() {
        return getRecord(receiverIdentifierCode, "CHAR", 15, "BF", PADDING_TYPE.RIGHT);
    }

    public void setReceiverIdentifierCode(String receiverIdentifierCode) {
        this.receiverIdentifierCode = receiverIdentifierCode;
    }

    public String getReceiverAccountNumber() {
        return getRecord(receiverAccountNumber, "CHAR", 10, "BF", PADDING_TYPE.RIGHT);
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public String getReceiverName() {
        return getRecord(receiverName, "CHAR", 30, "BF", PADDING_TYPE.RIGHT);
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddressLine1() {
        return getRecord(receiverAddressLine1, "CHAR", 30, "BF", PADDING_TYPE.RIGHT);
    }

    public void setReceiverAddressLine1(String receiverAddressLine1) {
        this.receiverAddressLine1 = receiverAddressLine1;
    }

    public String getReceiverAddressLine2() {
        return getRecord(receiverAddressLine2, "CHAR", 30, "BF", PADDING_TYPE.RIGHT);
    }

    public void setReceiverAddressLine2(String receiverAddressLine2) {
        this.receiverAddressLine2 = receiverAddressLine2;
    }

    public String getReceiverSuburb() {
        return getRecord(receiverSuburb, "CHAR", 20, "BF", PADDING_TYPE.RIGHT);
    }

    public void setReceiverSuburb(String receiverSuburb) {
        this.receiverSuburb = receiverSuburb;
    }

    public String getReceiverState() {
        return getRecord(receiverState, "CHAR", 3, "BF", PADDING_TYPE.RIGHT);
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverPostCode() {
        return getRecord(receiverPostCode, "CHAR", 4, "BF", PADDING_TYPE.RIGHT);
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    public String getReceiverContactName() {
        return getRecord(receiverContactName, "CHAR", 20, "BF", PADDING_TYPE.RIGHT);
    }

    public void setReceiverContactName(String receiverContactName) {
        this.receiverContactName = receiverContactName;
    }

    public String getReceiverContactPhone() {
        return getRecord(receiverContactPhone, "CHAR", 13, "BF", PADDING_TYPE.RIGHT);
    }

    public void setReceiverContactPhone(String receiverContactPhone) {
        this.receiverContactPhone = receiverContactPhone;
    }

    public String getConNoteDate() {
        return getRecord(conNoteDate, "CHAR", 8, "");
    }

    public void setConNoteDate(String conNoteDate) {
        this.conNoteDate = conNoteDate;
    }

    public String getService() {
        return getRecord(service, "CHAR", 4, "BF", PADDING_TYPE.RIGHT);
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDangerousGoodsFlag() {
        return getRecord(dangerousGoodsFlag, "CHAR", 1, "");
    }

    public void setDangerousGoodsFlag(String dangerousGoodsFlag) {
        this.dangerousGoodsFlag = dangerousGoodsFlag;
    }

    public String getPayerFlag() {
        return getRecord(payerFlag, "CHAR", 1, "");
    }

    public void setPayerFlag(String payerFlag) {
        this.payerFlag = payerFlag;
    }

    public String getCancelledFlag() {
        return getRecord(cancelledFlag, "CHAR", 1, "");
    }

    public void setCancelledFlag(String cancelledFlag) {
        this.cancelledFlag = cancelledFlag;
    }

    public String getFoodStuffsFlag() {
        return getRecord(foodStuffsFlag, "CHAR", 1, "");
    }

    public void setFoodStuffsFlag(String foodStuffsFlag) {
        this.foodStuffsFlag = foodStuffsFlag;
    }

    public String getExtendedWarrantyValue() {
        return getRecord(extendedWarrantyValue, "NUM", 8, "ZF");
    }

    public void setExtendedWarrantyValue(String extendedWarrantyValue) {
        this.extendedWarrantyValue = extendedWarrantyValue;
    }

    public String getExtendedWarrantyClass() {
        return getRecord(extendedWarrantyClass, "CHAR", 1, "BF");
    }

    public void setExtendedWarrantyClass(String extendedWarrantyClass) {
        this.extendedWarrantyClass = extendedWarrantyClass;
    }

    public String getAdditionalService1() {
        return getRecord(additionalService1, "CHAR", 3, "BF");
    }

    public void setAdditionalService1(String additionalService1) {
        this.additionalService1 = additionalService1;
    }

    public String getAdditionalService2() {
        return getRecord(additionalService2, "CHAR", 3, "BF");
    }

    public void setAdditionalService2(String additionalService2) {
        this.additionalService2 = additionalService2;
    }

    public String getAdditionalService3() {
        return getRecord(additionalService3, "CHAR", 3, "BF");
    }

    public void setAdditionalService3(String additionalService3) {
        this.additionalService3 = additionalService3;
    }

    public String getAdditionalService4() {
        return getRecord(additionalService4, "CHAR", 3, "BF");
    }

    public void setAdditionalService4(String additionalService4) {
        this.additionalService4 = additionalService4;
    }

    public String getAdditionalService5() {
        return getRecord(additionalService5, "CHAR", 3, "BF");
    }

    public void setAdditionalService5(String additionalService5) {
        this.additionalService5 = additionalService5;
    }

    public String getHandRateAmount() {
        return getRecord(handRateAmount, "NUM9.2", 9, "ZF");
    }

    public void setHandRateAmount(String handRateAmount) {
        this.handRateAmount = handRateAmount;
    }

    public String getOtherCharges() {
        return getRecord(otherCharges, "NUM9.2", 9, "ZF");
    }

    public void setOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
    }

    public String getCustomerConsignmentReference() {
        return getRecord(customerConsignmentReference, "CHAR", 30, "BF", PADDING_TYPE.RIGHT);
    }

    public void setCustomerConsignmentReference(String customerConsignmentReference) {
        this.customerConsignmentReference = customerConsignmentReference;
    }
}
