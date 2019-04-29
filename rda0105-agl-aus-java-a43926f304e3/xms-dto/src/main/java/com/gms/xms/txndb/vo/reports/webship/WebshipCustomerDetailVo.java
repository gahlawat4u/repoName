package com.gms.xms.txndb.vo.reports.webship;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonLong2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;
import java.util.List;

/**
 * Posted from WebshipCustomerDetailVo
 * <p>
 * Author DatTV Dec 11, 2015
 */
public class WebshipCustomerDetailVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String invoiceCode;
    private Long shipmentId;
    private String airbillNumber;
    private String customerCode;
    private Date shipmentDate;
    private String termOfTrade;
    private String courierMessage;
    private Boolean preClearance;
    private String senderCompanyName;
    private String senderContactName;
    private String senderAddress1;
    private String senderAddress2;
    private String senderCity;
    private String senderPostalCode;
    private String senderState;
    private String senderCountryName;
    private String receiverCompanyName;
    private String receiverContactName;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverCity;
    private String receiverPostalCode;
    private String receiverState;
    private String receiverCountryName;
    private Long noOfPieces;
    private Double weight;
    private String weightUnit;
    private Double quoted;
    private String serviceType;
    private List<WebshipCustomerDetailChargeVo> charges;
    private Double totalCustomerCost;
    private Double totalFranchiseCost;
    private Double totalMargin;
    private String dutiesTaxesFee;
    private Boolean insurance;
    private Boolean dangerousGoods;
    private Double withInsurance;

    @Override
    public String toString() {
        return "WebshipCustomerDetailVo [invoiceCode=" + invoiceCode + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", customerCode=" + customerCode + ", shipmentDate=" + shipmentDate + ", termOfTrade=" + termOfTrade + ", courierMessage=" + courierMessage + ", preClearance=" + preClearance + ", senderCompanyName=" + senderCompanyName + ", senderContactName=" + senderContactName + ", senderAddress1=" + senderAddress1 + ", senderAddress2=" + senderAddress2 + ", senderCity="
                + senderCity + ", senderPostalCode=" + senderPostalCode + ", senderState=" + senderState + ", senderCountryName=" + senderCountryName + ", receiverCompanyName=" + receiverCompanyName + ", receiverContactName=" + receiverContactName + ", receiverAddress1=" + receiverAddress1 + ", receiverAddress2=" + receiverAddress2 + ", receiverCity=" + receiverCity + ", receiverPostalCode=" + receiverPostalCode + ", receiverState=" + receiverState + ", receiverCountryName="
                + receiverCountryName + ", noOfPieces=" + noOfPieces + ", weight=" + weight + ", weightUnit=" + weightUnit + ", quoted=" + quoted + ", serviceType=" + serviceType + ", charges=" + charges + ", totalCustomerCost=" + totalCustomerCost + ", totalFranchiseCost=" + totalFranchiseCost + ", totalMargin=" + totalMargin + ", dutiesTaxesFee=" + dutiesTaxesFee + ", insurance=" + insurance + ", dangerousGoods=" + dangerousGoods + ", withInsurance=" + withInsurance + "]";
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getTermOfTrade() {
        return termOfTrade;
    }

    public void setTermOfTrade(String termOfTrade) {
        this.termOfTrade = termOfTrade;
    }

    public String getCourierMessage() {
        return courierMessage;
    }

    public void setCourierMessage(String courierMessage) {
        this.courierMessage = courierMessage;
    }

    public Boolean getPreClearance() {
        return preClearance;
    }

    public void setPreClearance(Boolean preClearance) {
        this.preClearance = preClearance;
    }

    public String getSenderCompanyName() {
        return senderCompanyName;
    }

    public void setSenderCompanyName(String senderCompanyName) {
        this.senderCompanyName = senderCompanyName;
    }

    public String getSenderContactName() {
        return senderContactName;
    }

    public void setSenderContactName(String senderContactName) {
        this.senderContactName = senderContactName;
    }

    public String getSenderAddress1() {
        return senderAddress1;
    }

    public void setSenderAddress1(String senderAddress1) {
        this.senderAddress1 = senderAddress1;
    }

    public String getSenderAddress2() {
        return senderAddress2;
    }

    public void setSenderAddress2(String senderAddress2) {
        this.senderAddress2 = senderAddress2;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderPostalCode() {
        return senderPostalCode;
    }

    public void setSenderPostalCode(String senderPostalCode) {
        this.senderPostalCode = senderPostalCode;
    }

    public String getSenderState() {
        return senderState;
    }

    public void setSenderState(String senderState) {
        this.senderState = senderState;
    }

    public String getSenderCountryName() {
        return senderCountryName;
    }

    public void setSenderCountryName(String senderCountryName) {
        this.senderCountryName = senderCountryName;
    }

    public String getReceiverCompanyName() {
        return receiverCompanyName;
    }

    public void setReceiverCompanyName(String receiverCompanyName) {
        this.receiverCompanyName = receiverCompanyName;
    }

    public String getReceiverContactName() {
        return receiverContactName;
    }

    public void setReceiverContactName(String receiverContactName) {
        this.receiverContactName = receiverContactName;
    }

    public String getReceiverAddress1() {
        return receiverAddress1;
    }

    public void setReceiverAddress1(String receiverAddress1) {
        this.receiverAddress1 = receiverAddress1;
    }

    public String getReceiverAddress2() {
        return receiverAddress2;
    }

    public void setReceiverAddress2(String receiverAddress2) {
        this.receiverAddress2 = receiverAddress2;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverPostalCode() {
        return receiverPostalCode;
    }

    public void setReceiverPostalCode(String receiverPostalCode) {
        this.receiverPostalCode = receiverPostalCode;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverCountryName() {
        return receiverCountryName;
    }

    public void setReceiverCountryName(String receiverCountryName) {
        this.receiverCountryName = receiverCountryName;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Long noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getQuoted() {
        return quoted;
    }

    public void setQuoted(Double quoted) {
        this.quoted = quoted;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<WebshipCustomerDetailChargeVo> getCharges() {
        return charges;
    }

    public void setCharges(List<WebshipCustomerDetailChargeVo> charges) {
        this.charges = charges;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalCustomerCost() {
        return totalCustomerCost;
    }

    public void setTotalCustomerCost(Double totalCustomerCost) {
        this.totalCustomerCost = totalCustomerCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalFranchiseCost() {
        return totalFranchiseCost;
    }

    public void setTotalFranchiseCost(Double totalFranchiseCost) {
        this.totalFranchiseCost = totalFranchiseCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalMargin() {
        return totalMargin;
    }

    public void setTotalMargin(Double totalMargin) {
        this.totalMargin = totalMargin;
    }

    public String getDutiesTaxesFee() {
        return dutiesTaxesFee;
    }

    public void setDutiesTaxesFee(String dutiesTaxesFee) {
        this.dutiesTaxesFee = dutiesTaxesFee;
    }

    public Boolean getInsurance() {
        return insurance;
    }

    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
    }

    public Boolean getDangerousGoods() {
        return dangerousGoods;
    }

    public void setDangerousGoods(Boolean dangerousGoods) {
        this.dangerousGoods = dangerousGoods;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getWithInsurance() {
        return withInsurance;
    }

    public void setWithInsurance(Double withInsurance) {
        this.withInsurance = withInsurance;
    }
}
