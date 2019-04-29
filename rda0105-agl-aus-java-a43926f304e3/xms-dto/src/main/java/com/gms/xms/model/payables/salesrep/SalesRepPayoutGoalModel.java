package com.gms.xms.model.payables.salesrep;

import com.gms.xms.model.SalesRepServiceModel;
import com.gms.xms.model.ServiceModel;

/**
 * Posted from SalesRepPayoutGoalModel
 * <p>
 * Author dattrinh Mar 21, 2016 1:57:32 PM
 */
public class SalesRepPayoutGoalModel extends SalesRepServiceModel {

    private static final long serialVersionUID = 1L;

    private ServiceModel service;

    public ServiceModel getService() {
        return service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }
}
