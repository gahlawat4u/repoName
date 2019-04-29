package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;

/**
 * Posted from CustomerAccessorialVo
 * <p>
 * Author HungNT Date Apr 23, 2015
 */
public class CustomerAccessorialVo extends BaseVo {
    private static final long serialVersionUID = 4564545843513476768L;

    private Long customerCode;

    private Long accessorialid;

    private BigDecimal amount;

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Long getAccessorialid() {
        return accessorialid;
    }

    public void setAccessorialid(Long accessorialid) {
        this.accessorialid = accessorialid;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}