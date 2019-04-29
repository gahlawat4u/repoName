package com.gms.xms.dto.edigenerate.tntrecord;

/**
 * Posted from Sep 23, 2016 11:35:44 AM
 * <p>
 * Author dattrinh
 */
public class TntRecordA extends TntRecordBase {
    private String transmissionIdentifier;
    private String senderInterChangeAddress;
    private String receiverInterChangeAddress;
    private String tradingPartnerIdentifier;
    private String carrier;
    private String fileGenerationDate;
    private String fileGenerationTime;
    private String fileVersionNumber;
    private String routingEffectiveDate;
    private String routingVersionNumber;

    @Override
    public boolean isValidRow() {
        boolean isValid = !"NO".equalsIgnoreCase(this.getRecordType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getTransmissionIdentifier());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getSenderInterChangeAddress());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getReceiverInterChangeAddress());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getTradingPartnerIdentifier());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getCarrier());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getFileGenerationDate());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getFileGenerationTime());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getFileVersionNumber());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getRoutingEffectiveDate());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getRoutingVersionNumber());
        return isValid;
    }

    @Override
    public String getRow() {
        String row = "";
        row += this.getRecordType();
        row += this.getTransmissionIdentifier();
        row += this.getSenderInterChangeAddress();
        row += this.getReceiverInterChangeAddress();
        row += this.getTradingPartnerIdentifier();
        row += this.getCarrier();
        row += this.getFileGenerationDate();
        row += this.getFileGenerationTime();
        row += this.getFileVersionNumber();
        row += this.getRoutingEffectiveDate();
        row += this.getRoutingVersionNumber();
        row += "\n";
        return row;
    }

    public String getTransmissionIdentifier() {
        return getRecord(transmissionIdentifier, "CHAR", 20, "BF");
    }

    public void setTransmissionIdentifier(String transmissionIdentifier) {
        this.transmissionIdentifier = transmissionIdentifier;
    }

    public String getSenderInterChangeAddress() {
        return getRecord(senderInterChangeAddress, "CHAR", 15, "BF");
    }

    public void setSenderInterChangeAddress(String senderInterChangeAddress) {
        this.senderInterChangeAddress = senderInterChangeAddress;
    }

    public String getReceiverInterChangeAddress() {
        return getRecord(receiverInterChangeAddress, "CHAR", 15, "BF");
    }

    public void setReceiverInterChangeAddress(String receiverInterChangeAddress) {
        this.receiverInterChangeAddress = receiverInterChangeAddress;
    }

    public String getTradingPartnerIdentifier() {
        return getRecord(tradingPartnerIdentifier, "CHAR", 12, "BF");
    }

    public void setTradingPartnerIdentifier(String tradingPartnerIdentifier) {
        this.tradingPartnerIdentifier = tradingPartnerIdentifier;
    }

    public String getCarrier() {
        return getRecord(carrier, "CHAR", 3, "");
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getFileGenerationDate() {
        return getRecord(fileGenerationDate, "CHAR", 8, "");
    }

    public void setFileGenerationDate(String fileGenerationDate) {
        this.fileGenerationDate = fileGenerationDate;
    }

    public String getFileGenerationTime() {
        return getRecord(fileGenerationTime, "CHAR", 4, "");
    }

    public void setFileGenerationTime(String fileGenerationTime) {
        this.fileGenerationTime = fileGenerationTime;
    }

    public String getFileVersionNumber() {
        return getRecord(fileVersionNumber, "CHAR", 2, "");
    }

    public void setFileVersionNumber(String fileVersionNumber) {
        this.fileVersionNumber = fileVersionNumber;
    }

    public String getRoutingEffectiveDate() {
        return getRecord(routingEffectiveDate, "CHAR", 8, "");
    }

    public void setRoutingEffectiveDate(String routingEffectiveDate) {
        this.routingEffectiveDate = routingEffectiveDate;
    }

    public String getRoutingVersionNumber() {
        return getRecord(routingVersionNumber, "CHAR", 3, "");
    }

    public void setRoutingVersionNumber(String routingVersionNumber) {
        this.routingVersionNumber = routingVersionNumber;
    }
}
