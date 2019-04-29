package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.common.json.JsonObjectVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Posted from FranchisePayableMSOverpaymentVo.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:24:44 PM
 */
public class FranchisePayableMSOverpaymentVo extends BaseVo {

    private static final long serialVersionUID = 1L;
    private Date originPaymentDate;
    private String customerNumber;
    private String customerName;
    private String overpaymentType;
    private BigDecimal amount;

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getOriginPaymentDate() {
        return originPaymentDate;
    }

    public void setOriginPaymentDate(Date originPaymentDate) {
        this.originPaymentDate = originPaymentDate;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @JsonSerialize(using = JsonObjectVo2ModelSerializer.class)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOverpaymentType() {
        return overpaymentType;
    }

    public void setOverpaymentType(String overpaymentType) {
        this.overpaymentType = overpaymentType;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FranchisePayableMSOverpaymentVo [originPaymentDate=" + originPaymentDate + ", customerNumber=" + customerNumber + ", customerName=" + customerName + ", overpaymentType=" + overpaymentType + ", amount=" + amount + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((customerNumber == null) ? 0 : customerNumber.hashCode());
        result = prime * result + ((originPaymentDate == null) ? 0 : originPaymentDate.hashCode());
        result = prime * result + ((overpaymentType == null) ? 0 : overpaymentType.hashCode());
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
        FranchisePayableMSOverpaymentVo other = (FranchisePayableMSOverpaymentVo) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (customerNumber == null) {
            if (other.customerNumber != null)
                return false;
        } else if (!customerNumber.equals(other.customerNumber))
            return false;
        if (originPaymentDate == null) {
            if (other.originPaymentDate != null)
                return false;
        } else if (!originPaymentDate.equals(other.originPaymentDate))
            return false;
        if (overpaymentType == null) {
            if (other.overpaymentType != null)
                return false;
        } else if (!overpaymentType.equals(other.overpaymentType))
            return false;
        return true;
    }
}
