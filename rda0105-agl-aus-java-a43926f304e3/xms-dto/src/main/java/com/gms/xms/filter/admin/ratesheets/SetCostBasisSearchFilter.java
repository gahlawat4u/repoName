package com.gms.xms.filter.admin.ratesheets;

import com.gms.xms.filter.BaseFilter;

public class SetCostBasisSearchFilter extends BaseFilter {
    private String serviceDescription;
    private String carrier;
    private String currentValue;

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }
}
