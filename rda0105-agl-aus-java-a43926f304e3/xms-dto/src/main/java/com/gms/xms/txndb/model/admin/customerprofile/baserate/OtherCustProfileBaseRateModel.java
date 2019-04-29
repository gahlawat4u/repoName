package com.gms.xms.txndb.model.admin.customerprofile.baserate;


/**
 * Posted from OtherCustProfileBaseRateModel
 * <p>
 * Author @author HungNT Apr 8, 2016
 */
public class OtherCustProfileBaseRateModel extends CustProfileBaseRateModel {

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
