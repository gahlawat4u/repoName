package com.gms.xms.txndb.vo.surchargelist;

import com.gms.xms.txndb.vo.AccessorialVo;

/**
 * Posted from SurchargeListVo
 * <p>
 * Author HungNT Date Sep 15, 2015
 */
public class SurchargeListVo extends AccessorialVo {
    private static final long serialVersionUID = -1525855884749499484L;
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
