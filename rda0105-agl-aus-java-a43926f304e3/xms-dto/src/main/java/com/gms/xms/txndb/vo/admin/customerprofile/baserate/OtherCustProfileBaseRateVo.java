package com.gms.xms.txndb.vo.admin.customerprofile.baserate;

/**
 * Posted from OtherCustProfileBaseRateVo
 * <p>
 * Author @author HungNT Apr 8, 2016
 */
public class OtherCustProfileBaseRateVo extends CustProfileBaseRateVo {

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
