package com.gms.xms.model.franchisepayable.sc;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayableSCTechFeeTotalModel
 * <p>
 * Author DatTV Dec 9, 2015
 */
public class FranchisePayableSCTechFeeTotalModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String intlShipmentFee;
    private String domShipmentFee;
    private String intlShipmentCount;
    private String domShipmentCount;

    @Override
    public String toString() {
        return "FranchisePayableSCTechFeeTotalModel [intlShipmentFee=" + intlShipmentFee + ", domShipmentFee=" + domShipmentFee + ", intlShipmentCount=" + intlShipmentCount + ", domShipmentCount=" + domShipmentCount + "]";
    }

    public String getIntlShipmentFee() {
        return intlShipmentFee;
    }

    public void setIntlShipmentFee(String intlShipmentFee) {
        this.intlShipmentFee = intlShipmentFee;
    }

    public String getDomShipmentFee() {
        return domShipmentFee;
    }

    public void setDomShipmentFee(String domShipmentFee) {
        this.domShipmentFee = domShipmentFee;
    }

    public String getIntlShipmentCount() {
        return intlShipmentCount;
    }

    public void setIntlShipmentCount(String intlShipmentCount) {
        this.intlShipmentCount = intlShipmentCount;
    }

    public String getDomShipmentCount() {
        return domShipmentCount;
    }

    public void setDomShipmentCount(String domShipmentCount) {
        this.domShipmentCount = domShipmentCount;
    }
}
