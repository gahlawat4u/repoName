package com.gms.xms.txndb.model.admin.customer.baserate;

/**
 * Posted from Apr 6, 2016 1:39:35 PM
 * <p>
 * Author dattrinh
 */
public class OtherCustBaseRateModel extends CustBaseRateModel {

    private static final long serialVersionUID = 1L;

    private String serviceId;
    private String serviceName;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
