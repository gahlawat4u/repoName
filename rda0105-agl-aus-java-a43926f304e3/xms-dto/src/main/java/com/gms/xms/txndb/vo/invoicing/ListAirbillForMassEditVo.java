package com.gms.xms.txndb.vo.invoicing;

import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from May 12, 2016 4:01:38 PM
 * <p>
 * Author huynd
 */
public class ListAirbillForMassEditVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long shipmentId;
    private String airbillNumber;
    private Long invoiceId;
    private Date invoiceDate;
    private Long customerCode;
    private Integer invoiceStatus;

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

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    @Override
    public String toString() {
        return "ListAirbillForMassEditVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", customerCode=" + customerCode + ", invoiceStatus=" + invoiceStatus + "]";
    }

}
