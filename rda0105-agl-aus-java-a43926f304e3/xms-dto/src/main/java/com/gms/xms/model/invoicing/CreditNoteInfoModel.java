package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;

/**
 * Posted from ManageCreditNoteModel
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class CreditNoteInfoModel extends BaseModel {
    private static final long serialVersionUID = 6832824278180120620L;
    private String creditCode;
    private String createDate;
    private String customerCode;
    private String credits;
    private String shipmentAmount;
    private String gstAmount;
    private String nonShipmentAmount;
    private String nonGstAmount;
    private String totalCredited;
    private String billingCustomerName;
    private String billingContactName;
    private String billingAddress1;
    private String billingAddress2;
    private String billingCity;
    private String countryName;
    private String billingPostalCode;
    private String status;
    private String totalAmount;
    private String nonTotalAmount;
    private String taxPercent;

    public String getNonTotalAmount() {
        return nonTotalAmount;
    }

    public void setNonTotalAmount(String nonTotalAmount) {
        this.nonTotalAmount = nonTotalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getShipmentAmount() {
        return shipmentAmount;
    }

    public void setShipmentAmount(String shipmentAmount) {
        this.shipmentAmount = shipmentAmount;
    }

    public String getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(String gstAmount) {
        this.gstAmount = gstAmount;
    }

    public String getNonShipmentAmount() {
        return nonShipmentAmount;
    }

    public void setNonShipmentAmount(String nonShipmentAmount) {
        this.nonShipmentAmount = nonShipmentAmount;
    }

    public String getNonGstAmount() {
        return nonGstAmount;
    }

    public void setNonGstAmount(String nonGstAmount) {
        this.nonGstAmount = nonGstAmount;
    }

    public String getTotalCredited() {
        return totalCredited;
    }

    public void setTotalCredited(String totalCredited) {
        this.totalCredited = totalCredited;
    }

    public String getBillingCustomerName() {
        return billingCustomerName;
    }

    public void setBillingCustomerName(String billingCustomerName) {
        this.billingCustomerName = billingCustomerName;
    }

    public String getBillingContactName() {
        return billingContactName;
    }

    public void setBillingContactName(String billingContactName) {
        this.billingContactName = billingContactName;
    }

    public String getBillingAddress1() {
        return billingAddress1;
    }

    public void setBillingAddress1(String billingAddress1) {
        this.billingAddress1 = billingAddress1;
    }

    public String getBillingAddress2() {
        return billingAddress2;
    }

    public void setBillingAddress2(String billingAddress2) {
        this.billingAddress2 = billingAddress2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    @Override
    public String toString() {
        return "CreditNoteInfoModel [creditCode=" + creditCode + ", createDate=" + createDate + ", customerCode=" + customerCode + ", credits=" + credits + ", shipmentAmount=" + shipmentAmount + ", gstAmount=" + gstAmount + ", nonShipmentAmount=" + nonShipmentAmount + ", nonGstAmount=" + nonGstAmount + ", totalCredited=" + totalCredited + ", billingCustomerName=" + billingCustomerName + ", billingContactName=" + billingContactName + ", billingAddress1=" + billingAddress1 + ", billingAddress2="
                + billingAddress2 + ", billingCity=" + billingCity + ", countryName=" + countryName + ", billingPostalCode=" + billingPostalCode + ", status=" + status + ", totalAmount=" + totalAmount + ", nonTotalAmount=" + nonTotalAmount + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((billingAddress1 == null) ? 0 : billingAddress1.hashCode());
        result = prime * result + ((billingAddress2 == null) ? 0 : billingAddress2.hashCode());
        result = prime * result + ((billingCity == null) ? 0 : billingCity.hashCode());
        result = prime * result + ((billingContactName == null) ? 0 : billingContactName.hashCode());
        result = prime * result + ((billingCustomerName == null) ? 0 : billingCustomerName.hashCode());
        result = prime * result + ((billingPostalCode == null) ? 0 : billingPostalCode.hashCode());
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((creditCode == null) ? 0 : creditCode.hashCode());
        result = prime * result + ((credits == null) ? 0 : credits.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((gstAmount == null) ? 0 : gstAmount.hashCode());
        result = prime * result + ((nonGstAmount == null) ? 0 : nonGstAmount.hashCode());
        result = prime * result + ((nonShipmentAmount == null) ? 0 : nonShipmentAmount.hashCode());
        result = prime * result + ((nonTotalAmount == null) ? 0 : nonTotalAmount.hashCode());
        result = prime * result + ((shipmentAmount == null) ? 0 : shipmentAmount.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((totalAmount == null) ? 0 : totalAmount.hashCode());
        result = prime * result + ((totalCredited == null) ? 0 : totalCredited.hashCode());
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
        CreditNoteInfoModel other = (CreditNoteInfoModel) obj;
        if (billingAddress1 == null) {
            if (other.billingAddress1 != null)
                return false;
        } else if (!billingAddress1.equals(other.billingAddress1))
            return false;
        if (billingAddress2 == null) {
            if (other.billingAddress2 != null)
                return false;
        } else if (!billingAddress2.equals(other.billingAddress2))
            return false;
        if (billingCity == null) {
            if (other.billingCity != null)
                return false;
        } else if (!billingCity.equals(other.billingCity))
            return false;
        if (billingContactName == null) {
            if (other.billingContactName != null)
                return false;
        } else if (!billingContactName.equals(other.billingContactName))
            return false;
        if (billingCustomerName == null) {
            if (other.billingCustomerName != null)
                return false;
        } else if (!billingCustomerName.equals(other.billingCustomerName))
            return false;
        if (billingPostalCode == null) {
            if (other.billingPostalCode != null)
                return false;
        } else if (!billingPostalCode.equals(other.billingPostalCode))
            return false;
        if (countryName == null) {
            if (other.countryName != null)
                return false;
        } else if (!countryName.equals(other.countryName))
            return false;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (creditCode == null) {
            if (other.creditCode != null)
                return false;
        } else if (!creditCode.equals(other.creditCode))
            return false;
        if (credits == null) {
            if (other.credits != null)
                return false;
        } else if (!credits.equals(other.credits))
            return false;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (gstAmount == null) {
            if (other.gstAmount != null)
                return false;
        } else if (!gstAmount.equals(other.gstAmount))
            return false;
        if (nonGstAmount == null) {
            if (other.nonGstAmount != null)
                return false;
        } else if (!nonGstAmount.equals(other.nonGstAmount))
            return false;
        if (nonShipmentAmount == null) {
            if (other.nonShipmentAmount != null)
                return false;
        } else if (!nonShipmentAmount.equals(other.nonShipmentAmount))
            return false;
        if (nonTotalAmount == null) {
            if (other.nonTotalAmount != null)
                return false;
        } else if (!nonTotalAmount.equals(other.nonTotalAmount))
            return false;
        if (shipmentAmount == null) {
            if (other.shipmentAmount != null)
                return false;
        } else if (!shipmentAmount.equals(other.shipmentAmount))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (totalAmount == null) {
            if (other.totalAmount != null)
                return false;
        } else if (!totalAmount.equals(other.totalAmount))
            return false;
        if (totalCredited == null) {
            if (other.totalCredited != null)
                return false;
        } else if (!totalCredited.equals(other.totalCredited))
            return false;
        return true;
    }

    public String getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(String taxPercent) {
        this.taxPercent = taxPercent;
    }

}
