package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.ServiceVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * Posted from ManageCustomerBaseRateVo
 * <p>
 * Author DatTV Sep 17, 2015
 */
public class ManageCustomerBaseRateVo extends BaseVo {

    private static final long serialVersionUID = -6098577123616203120L;

    private Long customerCode;
    private Double minimunBaseCharge;
    private List<ServiceVo> services;

    @Override
    public String toString() {
        return "ManageCustomerBaseRateVo [customerCode=" + customerCode + ", minimunBaseCharge=" + minimunBaseCharge + ", services=" + services + "]";
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(Double minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    public List<ServiceVo> getServices() {
        return services;
    }

    public void setServices(List<ServiceVo> services) {
        this.services = services;
    }
}