package com.gms.xms.model.surchargelist;

import com.gms.xms.model.AccessorialModel;

/**
 * Posted from SurchargeListModel
 * <p>
 * Author HungNT Date Sep 15, 2015
 */
public class SurchargeListModel extends AccessorialModel {
    private static final long serialVersionUID = -1851745519794429850L;
    private String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "SurchargeListVo [serviceName=" + serviceName + "]";
    }
}
