package com.gms.xms.model.reports.customer.status;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustomerStatusModel
 * <p>
 * Author DatTV Nov 5, 2015
 */
public class CustomerStatusModel extends BaseModel {

    private static final long serialVersionUID = -4702846606050418312L;

    private String period;
    private String setups;
    private String activations;
    private String shipmentCount;
    private String revenueExcGst;
    private String revenueIncGst;
    private String marginExcGst;
    private String marginIncGst;

    @Override
    public String toString() {
        return "CustomerStatusModel [period=" + period + ", setups=" + setups + ", activations=" + activations + ", shipmentCount=" + shipmentCount + ", revenueExcGst=" + revenueExcGst + ", revenueIncGst=" + revenueIncGst + ", marginExcGst=" + marginExcGst + ", marginIncGst=" + marginIncGst + "]";
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSetups() {
        return setups;
    }

    public void setSetups(String setups) {
        this.setups = setups;
    }

    public String getActivations() {
        return activations;
    }

    public void setActivations(String activations) {
        this.activations = activations;
    }

    public String getShipmentCount() {
        return shipmentCount;
    }

    public void setShipmentCount(String shipmentCount) {
        this.shipmentCount = shipmentCount;
    }

    public String getRevenueExcGst() {
        return revenueExcGst;
    }

    public void setRevenueExcGst(String revenueExcGst) {
        this.revenueExcGst = revenueExcGst;
    }

    public String getRevenueIncGst() {
        return revenueIncGst;
    }

    public void setRevenueIncGst(String revenueIncGst) {
        this.revenueIncGst = revenueIncGst;
    }

    public String getMarginExcGst() {
        return marginExcGst;
    }

    public void setMarginExcGst(String marginExcGst) {
        this.marginExcGst = marginExcGst;
    }

    public String getMarginIncGst() {
        return marginIncGst;
    }

    public void setMarginIncGst(String marginIncGst) {
        this.marginIncGst = marginIncGst;
    }
}
