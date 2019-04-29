package com.gms.xms.model.adjustment;

import com.gms.xms.model.BaseModel;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Posted from CarrierAdjustmentModel
 * <p>
 * Author TanDT Date May 26, 2015
 */
public class CarrierAdjustmentModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 64821869337519586L;
    private String adjustmentId;
    private String adjustmentType;
    private String airbillNumber;
    private String createDate;
    private String customerAmount;
    private String customerAmountNonGst;
    private String customerAmountGst;
    private String note;
    private String shipmentDate;
    private String deliveryDate;
    private String importDate;
    private String serviceName;
    private String shipmentId;
    private String carrierTaxAmount;
    private String carrierTotalAmount;
    private String carrierAmt;
    private String invoiceCode;
    private String customerCode;
    private String invoiceId;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getCustomerAmountNonGst() {
        return customerAmountNonGst;
    }

    public void setCustomerAmountNonGst(String customerAmountNonGst) {
        this.customerAmountNonGst = customerAmountNonGst;
    }

    public String getCustomerAmountGst() {
        return customerAmountGst;
    }

    public void setCustomerAmountGst(String customerAmountGst) {
        this.customerAmountGst = customerAmountGst;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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

    public String getCarrierTaxAmount() {
        return carrierTaxAmount;
    }

    public void setCarrierTaxAmount(String carrierTaxAmount) {
        this.carrierTaxAmount = carrierTaxAmount;
    }

    public String getCarrierTotalAmount() {
        return carrierTotalAmount;
    }

    public void setCarrierTotalAmount(String carrierTotalAmount) {
        this.carrierTotalAmount = carrierTotalAmount;
    }

    public String getCarrierAmt() {
        return carrierAmt;
    }

    public void setCarrierAmt(String carrierAmt) {
        this.carrierAmt = carrierAmt;
    }

    @JsonIgnore
    private String customerAmountFormatted;

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(String customerAmount) {
        this.customerAmount = customerAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getCustomerAmountFormatted() {
        return customerAmountFormatted;
    }

    public void setCustomerAmountFormatted(String customerAmountFormatted) {
        this.customerAmountFormatted = customerAmountFormatted;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
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
        return "CarrierAdjustmentModel [adjustmentId=" + adjustmentId + ", adjustmentType=" + adjustmentType + ", airbillNumber=" + airbillNumber + ", createDate=" + createDate + ", customerAmount=" + customerAmount + ", customerAmountNonGst=" + customerAmountNonGst + ", customerAmountGst=" + customerAmountGst + ", note=" + note + ", shipmentDate=" + shipmentDate + ", deliveryDate=" + deliveryDate + ", importDate=" + importDate + ", serviceName=" + serviceName + ", shipmentId=" + shipmentId
                + ", carrierTaxAmount=" + carrierTaxAmount + ", carrierTotalAmount=" + carrierTotalAmount + ", carrierAmt=" + carrierAmt + ", invoiceCode=" + invoiceCode + ", customerCode=" + customerCode + ", invoiceId=" + invoiceId + ", status=" + status + ", customerAmountFormatted=" + customerAmountFormatted + "]";
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
        result = prime * result + ((customerAmountFormatted == null) ? 0 : customerAmountFormatted.hashCode());
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
        CarrierAdjustmentModel other = (CarrierAdjustmentModel) obj;
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
        if (customerAmountFormatted == null) {
            if (other.customerAmountFormatted != null)
                return false;
        } else if (!customerAmountFormatted.equals(other.customerAmountFormatted))
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
