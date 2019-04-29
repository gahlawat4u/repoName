package com.gms.xms.txndb.vo.admin.customer.baserate;

/**
 * Posted from Apr 6, 2016 1:39:35 PM
 * <p>
 * Author dattrinh
 */
public class OtherCustBaseRateVo extends CustBaseRateVo {

    private static final long serialVersionUID = 1L;

    private Integer serviceId;
    private String serviceName;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
