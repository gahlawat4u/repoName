package com.gms.xms.txndb.vo.invoicing.searchairbill;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * File Name: SearchAirbillFilter.java <br/>
 * Author: TANDT <br/>
 * Create Date: 21-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.filter.admin <br/>
 */
public class SearchAirbillFilter extends BaseVo {
    private static final long serialVersionUID = -9049809846732879209L;
    private Long franchiseCode;
    private Boolean isCheckFranchiseCode;
    private Integer saleRepId;
    private Boolean isCheckSaleRepId;
    private Long customerCode;
    private String invoiceCode;
    private String airbillNumberList;
    private String airbillNumber;
    private Date invoiceDate;
    private Date importDate;
    private Integer serviceId;
    private Boolean isCheckServiceId;
    private String serviceLevel;
    private Boolean isCheckServiceLevel;
    private String accessorialName;
    private Boolean isCheckAccessorialName;
    private Integer packageTypeId;
    private String zone;
    private Boolean isNoZone;
    private Integer minPieces;
    private Integer maxPieces;
    private Boolean isCheckPieces;
    private Double minWeight;
    private Double maxWeight;
    private Boolean isCheckWeight;
    private String senderCode;
    private String receiverCode;
    private Boolean isCheckSenderCode;
    private Boolean isCheckReceiverCode;
    private String senderName;
    private Integer invoiceStatus;
    private Date startShipmentDate;
    private Date endShipmentDate;
    private Date startInvoiceDate;
    private Date endInvoiceDate;
    private String franchiseList;
    private Integer shipmentTypeId;
    private Integer billingContents;
    private Integer billingBound;
    private Integer carrierId;
    private Integer totalRecord;

    private Integer page;
    private Integer pageSize;
    private String orderField;
    private Integer orderType;

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public Boolean getIsCheckFranchiseCode() {
        return isCheckFranchiseCode;
    }

    public void setIsCheckFranchiseCode(Boolean isCheckFranchiseCode) {
        this.isCheckFranchiseCode = isCheckFranchiseCode;
    }

    public Integer getSaleRepId() {
        return saleRepId;
    }

    public void setSaleRepId(Integer saleRepId) {
        this.saleRepId = saleRepId;
    }

    public Boolean getIsCheckSaleRepId() {
        return isCheckSaleRepId;
    }

    public void setIsCheckSaleRepId(Boolean isCheckSaleRepId) {
        this.isCheckSaleRepId = isCheckSaleRepId;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
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

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getImportDate() {
        return importDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Boolean getIsCheckServiceId() {
        return isCheckServiceId;
    }

    public void setIsCheckServiceId(Boolean isCheckServiceId) {
        this.isCheckServiceId = isCheckServiceId;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public Boolean getIsCheckServiceLevel() {
        return isCheckServiceLevel;
    }

    public void setIsCheckServiceLevel(Boolean isCheckServiceLevel) {
        this.isCheckServiceLevel = isCheckServiceLevel;
    }

    public String getAccessorialName() {
        return accessorialName;
    }

    public void setAccessorialName(String accessorialName) {
        this.accessorialName = accessorialName;
    }

    public Boolean getIsCheckAccessorialName() {
        return isCheckAccessorialName;
    }

    public void setIsCheckAccessorialName(Boolean isCheckAccessorialName) {
        this.isCheckAccessorialName = isCheckAccessorialName;
    }

    public Integer getPackageTypeId() {
        return packageTypeId;
    }

    public void setPackageTypeId(Integer packageTypeId) {
        this.packageTypeId = packageTypeId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Boolean getIsNoZone() {
        return isNoZone;
    }

    public void setIsNoZone(Boolean isNoZone) {
        this.isNoZone = isNoZone;
    }

    public Integer getMinPieces() {
        return minPieces;
    }

    public void setMinPieces(Integer minPieces) {
        this.minPieces = minPieces;
    }

    public Integer getMaxPieces() {
        return maxPieces;
    }

    public void setMaxPieces(Integer maxPieces) {
        this.maxPieces = maxPieces;
    }

    public Boolean getIsCheckPieces() {
        return isCheckPieces;
    }

    public void setIsCheckPieces(Boolean isCheckPieces) {
        this.isCheckPieces = isCheckPieces;
    }

    public Double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(Double minWeight) {
        this.minWeight = minWeight;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Boolean getIsCheckWeight() {
        return isCheckWeight;
    }

    public void setIsCheckWeight(Boolean isCheckWeight) {
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

    public Boolean getIsCheckSenderCode() {
        return isCheckSenderCode;
    }

    public void setIsCheckSenderCode(Boolean isCheckSenderCode) {
        this.isCheckSenderCode = isCheckSenderCode;
    }

    public Boolean getIsCheckReceiverCode() {
        return isCheckReceiverCode;
    }

    public void setIsCheckReceiverCode(Boolean isCheckReceiverCode) {
        this.isCheckReceiverCode = isCheckReceiverCode;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getStartShipmentDate() {
        return startShipmentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setStartShipmentDate(Date startShipmentDate) {
        this.startShipmentDate = startShipmentDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getEndShipmentDate() {
        return endShipmentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setEndShipmentDate(Date endShipmentDate) {
        this.endShipmentDate = endShipmentDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getStartInvoiceDate() {
        return startInvoiceDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setStartInvoiceDate(Date startInvoiceDate) {
        this.startInvoiceDate = startInvoiceDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getEndInvoiceDate() {
        return endInvoiceDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setEndInvoiceDate(Date endInvoiceDate) {
        this.endInvoiceDate = endInvoiceDate;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Integer getBillingContents() {
        return billingContents;
    }

    public void setBillingContents(Integer billingContents) {
        this.billingContents = billingContents;
    }

    public Integer getBillingBound() {
        return billingBound;
    }

    public void setBillingBound(Integer billingBound) {
        this.billingBound = billingBound;
    }

    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    @JsonIgnore
    public Integer getStartRecord() {
        if (page != null && pageSize != null) {
            return (page - 1) * pageSize;
        } else
            return null;
    }

    @JsonIgnore
    public String getOrderBy1() {
        if (StringUtils.isBlank(orderField) || orderType == null) {
            return "order by invoice_date desc, airbill_number desc";
        }
        return orderField + " " + (orderType == 0 ? "asc" : "desc") + ", invoice_date desc, airbill_number desc";
    }

    @JsonIgnore
    public String getOrderBy2() {
        //XTD-107: order by shipment id desc to keep ordering stable and show the .
        String orderResult = "sb1.shipmentid DESC, sb1.accessorial_count";
        if (StringUtils.isBlank(orderField) || orderType == null) {
            return orderResult;
        }
        return orderField + " " + (orderType == 0 ? "asc" : "desc") + "," + orderResult;
    }

    @Override
    public String toString() {
        return "SearchAirbillFilter [franchiseCode=" + franchiseCode + ", isCheckFranchiseCode=" + isCheckFranchiseCode + ", saleRepId=" + saleRepId + ", isCheckSaleRepId=" + isCheckSaleRepId + ", customerCode=" + customerCode + ", invoiceCode=" + invoiceCode + ", airbillNumberList=" + airbillNumberList + ", airbillNumber=" + airbillNumber + ", invoiceDate=" + invoiceDate + ", importDate=" + importDate + ", serviceId=" + serviceId + ", isCheckServiceId=" + isCheckServiceId + ", serviceLevel="
                + serviceLevel + ", isCheckServiceLevel=" + isCheckServiceLevel + ", accessorialName=" + accessorialName + ", isCheckAccessorialName=" + isCheckAccessorialName + ", packageTypeId=" + packageTypeId + ", zone=" + zone + ", isNoZone=" + isNoZone + ", minPieces=" + minPieces + ", maxPieces=" + maxPieces + ", isCheckPieces=" + isCheckPieces + ", minWeight=" + minWeight + ", maxWeight=" + maxWeight + ", isCheckWeight=" + isCheckWeight + ", senderCode=" + senderCode + ", receiverCode="
                + receiverCode + ", isCheckSenderCode=" + isCheckSenderCode + ", isCheckReceiverCode=" + isCheckReceiverCode + ", senderName=" + senderName + ", invoiceStatus=" + invoiceStatus + ", startShipmentDate=" + startShipmentDate + ", endShipmentDate=" + endShipmentDate + ", startInvoiceDate=" + startInvoiceDate + ", endInvoiceDate=" + endInvoiceDate + ", franchiseList=" + franchiseList + ", shipmentTypeId=" + shipmentTypeId + ", billingContents=" + billingContents + ", billingBound="
                + billingBound + ", carrierId=" + carrierId + ", totalRecord=" + totalRecord + ", page=" + page + ", pageSize=" + pageSize + ", orderField=" + orderField + ", orderType=" + orderType + "]";
    }
}