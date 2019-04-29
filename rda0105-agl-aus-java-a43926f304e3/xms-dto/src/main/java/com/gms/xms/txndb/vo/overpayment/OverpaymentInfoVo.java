package com.gms.xms.txndb.vo.overpayment;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;

/**
 * Posted from OverpaymentInfoVo
 * <p>
 * Author DatTV Date Apr 23, 2015 11:05:00 AM
 */
public class OverpaymentInfoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String franchiseCode;
    private String customerCode;
    private String customerName;
    private String source;
    private BigDecimal totalOverpayment;
    private Integer status;

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalOverpayment() {
        return totalOverpayment;
    }

    public void setTotalOverpayment(BigDecimal totalOverpayment) {
        this.totalOverpayment = totalOverpayment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OverpaymentInfoVo [franchiseCode=" + franchiseCode + ", customerCode=" + customerCode + ", customerName=" + customerName + ", source=" + source + ", totalOverpayment=" + totalOverpayment + ", status=" + status + "]";
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}