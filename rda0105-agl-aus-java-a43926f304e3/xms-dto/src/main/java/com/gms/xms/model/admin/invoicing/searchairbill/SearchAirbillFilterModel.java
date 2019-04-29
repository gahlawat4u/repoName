package com.gms.xms.model.admin.invoicing.searchairbill;

import com.gms.xms.model.BaseModel;

/**
 * File Name: SearchAirbillFilterModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 21-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model.admin.invoicing.searchairbill <br/>
 */
public class SearchAirbillFilterModel extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = 7248159271491198121L;
    private String franchiseCode;
    private String isCheckFranchiseCode;
    private String saleRepId;
    private String isCheckSaleRepId;
    private String customerCode;
    private String invoiceCode;
    private String airbillNumberList;
    private String airbillNumber;
    private String invoiceDate;
    private String importDate;
    private String serviceId;
    private String isCheckServiceId;
    private String serviceLevel;
    private String isCheckServiceLevel;
    private String accessorialName;
    private String isCheckAccessorialName;
    private String packageTypeId;
    private String zone;
    private String isNoZone;
    private String minPieces;
    private String maxPieces;
    private String isCheckPieces;
    private String minWeight;
    private String maxWeight;
    private String isCheckWeight;
    private String senderCode;
    private String receiverCode;
    private String isCheckSenderCode;
    private String isCheckReceiverCode;
    private String senderName;
    private String invoiceStatus;
    private String startShipmentDate;
    private String endShipmentDate;
    private String startInvoiceDate;
    private String endInvoiceDate;
    private String franchiseList;
    private String shipmentTypeId;
    private String billingContents;
    private String billingBound;
    private String carrierId;
    private String totalRecord;

    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getIsCheckFranchiseCode() {
        return isCheckFranchiseCode;
    }

    public void setIsCheckFranchiseCode(String isCheckFranchiseCode) {
        this.isCheckFranchiseCode = isCheckFranchiseCode;
    }

    public String getSaleRepId() {
        return saleRepId;
    }

    public void setSaleRepId(String saleRepId) {
        this.saleRepId = saleRepId;
    }

    public String getIsCheckSaleRepId() {
        return isCheckSaleRepId;
    }

    public void setIsCheckSaleRepId(String isCheckSaleRepId) {
        this.isCheckSaleRepId = isCheckSaleRepId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getAirbillNumberList() {
        return airbillNumberList;
    }

    public void setAirbillNumberList(String airbillNumberList) {
        this.airbillNumberList = airbillNumberList;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getIsCheckServiceId() {
        return isCheckServiceId;
    }

    public void setIsCheckServiceId(String isCheckServiceId) {
        this.isCheckServiceId = isCheckServiceId;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getIsCheckServiceLevel() {
        return isCheckServiceLevel;
    }

    public void setIsCheckServiceLevel(String isCheckServiceLevel) {
        this.isCheckServiceLevel = isCheckServiceLevel;
    }

    public String getAccessorialName() {
        return accessorialName;
    }

    public void setAccessorialName(String accessorialName) {
        this.accessorialName = accessorialName;
    }

    public String getIsCheckAccessorialName() {
        return isCheckAccessorialName;
    }

    public void setIsCheckAccessorialName(String isCheckAccessorialName) {
        this.isCheckAccessorialName = isCheckAccessorialName;
    }

    public String getPackageTypeId() {
        return packageTypeId;
    }

    public void setPackageTypeId(String packageTypeId) {
        this.packageTypeId = packageTypeId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getIsNoZone() {
        return isNoZone;
    }

    public void setIsNoZone(String isNoZone) {
        this.isNoZone = isNoZone;
    }

    public String getMinPieces() {
        return minPieces;
    }

    public void setMinPieces(String minPieces) {
        this.minPieces = minPieces;
    }

    public String getMaxPieces() {
        return maxPieces;
    }

    public void setMaxPieces(String maxPieces) {
        this.maxPieces = maxPieces;
    }

    public String getIsCheckPieces() {
        return isCheckPieces;
    }

    public void setIsCheckPieces(String isCheckPieces) {
        this.isCheckPieces = isCheckPieces;
    }

    public String getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(String minWeight) {
        this.minWeight = minWeight;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getIsCheckWeight() {
        return isCheckWeight;
    }

    public void setIsCheckWeight(String isCheckWeight) {
        this.isCheckWeight = isCheckWeight;
    }

    public String getSenderCode() {
        return senderCode;
    }

    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public String getIsCheckSenderCode() {
        return isCheckSenderCode;
    }

    public void setIsCheckSenderCode(String isCheckSenderCode) {
        this.isCheckSenderCode = isCheckSenderCode;
    }

    public String getIsCheckReceiverCode() {
        return isCheckReceiverCode;
    }

    public void setIsCheckReceiverCode(String isCheckReceiverCode) {
        this.isCheckReceiverCode = isCheckReceiverCode;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getStartShipmentDate() {
        return startShipmentDate;
    }

    public void setStartShipmentDate(String startShipmentDate) {
        this.startShipmentDate = startShipmentDate;
    }

    public String getEndShipmentDate() {
        return endShipmentDate;
    }

    public void setEndShipmentDate(String endShipmentDate) {
        this.endShipmentDate = endShipmentDate;
    }

    public String getStartInvoiceDate() {
        return startInvoiceDate;
    }

    public void setStartInvoiceDate(String startInvoiceDate) {
        this.startInvoiceDate = startInvoiceDate;
    }

    public String getEndInvoiceDate() {
        return endInvoiceDate;
    }

    public void setEndInvoiceDate(String endInvoiceDate) {
        this.endInvoiceDate = endInvoiceDate;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getBillingContents() {
        return billingContents;
    }

    public void setBillingContents(String billingContents) {
        this.billingContents = billingContents;
    }

    public String getBillingBound() {
        return billingBound;
    }

    public void setBillingBound(String billingBound) {
        this.billingBound = billingBound;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "SearchAirbillFilterModel [franchiseCode=" + franchiseCode + ", isCheckFranchiseCode=" + isCheckFranchiseCode + ", saleRepId=" + saleRepId + ", isCheckSaleRepId=" + isCheckSaleRepId + ", customerCode=" + customerCode + ", invoiceCode=" + invoiceCode + ", airbillNumberList=" + airbillNumberList + ", airbillNumber=" + airbillNumber + ", invoiceDate=" + invoiceDate + ", importDate=" + importDate + ", serviceId=" + serviceId + ", isCheckServiceId=" + isCheckServiceId
                + ", serviceLevel=" + serviceLevel + ", isCheckServiceLevel=" + isCheckServiceLevel + ", accessorialName=" + accessorialName + ", isCheckAccessorialName=" + isCheckAccessorialName + ", packageTypeId=" + packageTypeId + ", zone=" + zone + ", isNoZone=" + isNoZone + ", minPieces=" + minPieces + ", maxPieces=" + maxPieces + ", isCheckPieces=" + isCheckPieces + ", minWeight=" + minWeight + ", maxWeight=" + maxWeight + ", isCheckWeight=" + isCheckWeight + ", senderCode=" + senderCode
                + ", receiverCode=" + receiverCode + ", isCheckSenderCode=" + isCheckSenderCode + ", isCheckReceiverCode=" + isCheckReceiverCode + ", senderName=" + senderName + ", invoiceStatus=" + invoiceStatus + ", startShipmentDate=" + startShipmentDate + ", endShipmentDate=" + endShipmentDate + ", startInvoiceDate=" + startInvoiceDate + ", endInvoiceDate=" + endInvoiceDate + ", franchiseList=" + franchiseList + ", shipmentTypeId=" + shipmentTypeId + ", billingContents=" + billingContents
                + ", billingBound=" + billingBound + ", carrierId=" + carrierId + ", totalRecord=" + totalRecord + ", page=" + page + ", pageSize=" + pageSize + ", orderField=" + orderField + ", orderType=" + orderType + "]";
    }
}