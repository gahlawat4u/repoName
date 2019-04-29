package com.gms.xms.txndb.vo.adjustment;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from CarrierAdjustmentVo
 * <p>
 * Author TanDT Date May 26, 2015
 */
public class CarrierAdjustmentVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 2066913153000364639L;
    private Long adjustmentId;
    private String adjustmentType;
    private String airbillNumber;
    private Date createDate;
    private Double customerAmount;
    private Double customerAmountNonGst;
    private Double customerAmountGst;
    private String note;
    private Date shipmentDate;
    private Date deliveryDate;
    private Date importDate;
    private String serviceName;
    private Long shipmentId;
    private Double carrierTaxAmount;
    private Double carrierTotalAmount;
    private Double carrierAmt;
    private String invoiceCode;
    private Long customerCode;
    private Long invoiceId;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public Double getCustomerAmountNonGst() {
        return customerAmountNonGst;
    }

    public void setCustomerAmountNonGst(Double customerAmountNonGst) {
        this.customerAmountNonGst = customerAmountNonGst;
    }

    public Double getCustomerAmountGst() {
        return customerAmountGst;
    }

    public void setCustomerAmountGst(Double customerAmountGst) {
        this.customerAmountGst = customerAmountGst;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
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

    public Double getCarrierTaxAmount() {
        return carrierTaxAmount;
    }

    public void setCarrierTaxAmount(Double carrierTaxAmount) {
        this.carrierTaxAmount = carrierTaxAmount;
    }

    public Double getCarrierTotalAmount() {
        return carrierTotalAmount;
    }

    public void setCarrierTotalAmount(Double carrierTotalAmount) {
        this.carrierTotalAmount = carrierTotalAmount;
    }

    public Double getCarrierAmt() {
        return carrierAmt;
    }

    public void setCarrierAmt(Double carrierAmt) {
        this.carrierAmt = carrierAmt;
    }

    public void setCustomerAmount(Double customerAmount) {
        this.customerAmount = customerAmount;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(Long adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getCustomerAmount() {
        return customerAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipmentDate() {
        return shipmentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getImportDate() {
        return importDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "CarrierAdjustmentVo [adjustmentId=" + adjustmentId + ", adjustmentType=" + adjustmentType + ", airbillNumber=" + airbillNumber + ", createDate=" + createDate + ", customerAmount=" + customerAmount + ", customerAmountNonGst=" + customerAmountNonGst + ", customerAmountGst=" + customerAmountGst + ", note=" + note + ", shipmentDate=" + shipmentDate + ", deliveryDate=" + deliveryDate + ", importDate=" + importDate + ", serviceName=" + serviceName + ", shipmentId=" + shipmentId
                + ", carrierTaxAmount=" + carrierTaxAmount + ", carrierTotalAmount=" + carrierTotalAmount + ", carrierAmt=" + carrierAmt + ", invoiceCode=" + invoiceCode + ", customerCode=" + customerCode + ", invoiceId=" + invoiceId + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adjustmentId == null) ? 0 : adjustmentId.hashCode());
        result = prime * result + ((adjustmentType == null) ? 0 : adjustmentType.hashCode());
        result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
        result = prime * result + ((carrierAmt == null) ? 0 : carrierAmt.hashCode());
        result = prime * result + ((carrierTaxAmount == null) ? 0 : carrierTaxAmount.hashCode());
        result = prime * result + ((carrierTotalAmount == null) ? 0 : carrierTotalAmount.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((customerAmount == null) ? 0 : customerAmount.hashCode());
        result = prime * result + ((customerAmountGst == null) ? 0 : customerAmountGst.hashCode());
        result = prime * result + ((customerAmountNonGst == null) ? 0 : customerAmountNonGst.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
        result = prime * result + ((importDate == null) ? 0 : importDate.hashCode());
        result = prime * result + ((invoiceCode == null) ? 0 : invoiceCode.hashCode());
        result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
        result = prime * result + ((shipmentDate == null) ? 0 : shipmentDate.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CarrierAdjustmentVo other = (CarrierAdjustmentVo) obj;
        if (adjustmentId == null) {
            if (other.adjustmentId != null)
                return false;
        } else if (!adjustmentId.equals(other.adjustmentId))
            return false;
        if (adjustmentType == null) {
            if (other.adjustmentType != null)
                return false;
        } else if (!adjustmentType.equals(other.adjustmentType))
            return false;
        if (airbillNumber == null) {
            if (other.airbillNumber != null)
                return false;
        } else if (!airbillNumber.equals(other.airbillNumber))
            return false;
        if (carrierAmt == null) {
            if (other.carrierAmt != null)
                return false;
        } else if (!carrierAmt.equals(other.carrierAmt))
            return false;
        if (carrierTaxAmount == null) {
            if (other.carrierTaxAmount != null)
                return false;
        } else if (!carrierTaxAmount.equals(other.carrierTaxAmount))
            return false;
        if (carrierTotalAmount == null) {
            if (other.carrierTotalAmount != null)
                return false;
        } else if (!carrierTotalAmount.equals(other.carrierTotalAmount))
            return false;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (customerAmount == null) {
            if (other.customerAmount != null)
                return false;
        } else if (!customerAmount.equals(other.customerAmount))
            return false;
        if (customerAmountGst == null) {
            if (other.customerAmountGst != null)
                return false;
        } else if (!customerAmountGst.equals(other.customerAmountGst))
            return false;
        if (customerAmountNonGst == null) {
            if (other.customerAmountNonGst != null)
                return false;
        } else if (!customerAmountNonGst.equals(other.customerAmountNonGst))
            return false;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (deliveryDate == null) {
            if (other.deliveryDate != null)
                return false;
        } else if (!deliveryDate.equals(other.deliveryDate))
            return false;
        if (importDate == null) {
            if (other.importDate != null)
                return false;
        } else if (!importDate.equals(other.importDate))
            return false;
        if (invoiceCode == null) {
            if (other.invoiceCode != null)
                return false;
        } else if (!invoiceCode.equals(other.invoiceCode))
            return false;
        if (invoiceId == null) {
            if (other.invoiceId != null)
                return false;
        } else if (!invoiceId.equals(other.invoiceId))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        if (serviceName == null) {
            if (other.serviceName != null)
                return false;
        } else if (!serviceName.equals(other.serviceName))
            return false;
        if (shipmentDate == null) {
            if (other.shipmentDate != null)
                return false;
        } else if (!shipmentDate.equals(other.shipmentDate))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

}
