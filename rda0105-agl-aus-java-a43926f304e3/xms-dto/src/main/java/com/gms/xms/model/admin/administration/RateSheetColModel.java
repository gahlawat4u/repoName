package com.gms.xms.model.admin.administration;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.CustomerBaseRateModel;
import com.gms.xms.model.RateSheetColumnModel;
import com.gms.xms.model.webship.ShipmentTypeModel;

import java.util.List;

/**
 * Posted from RateSheetColModel
 * <p>
 * Author TANDT 28-10-2015
 */
public class RateSheetColModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;
    protected List<RateSheetColumnModel> carrierRateSheetCols;
    protected List<RateSheetColumnModel> nonCarrierRateSheetCols;
    protected ShipmentTypeModel shipmentType;
    protected List<CustomerBaseRateModel> profileBaseRateDetail;

    public List<CustomerBaseRateModel> getProfileBaseRateDetail() {
        return profileBaseRateDetail;
    }

    public void setProfileBaseRateDetail(List<CustomerBaseRateModel> profileBaseRateDetail) {
        this.profileBaseRateDetail = profileBaseRateDetail;
    }

    public ShipmentTypeModel getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentTypeModel shipmentType) {
        this.shipmentType = shipmentType;
    }

    public List<RateSheetColumnModel> getCarrierRateSheetCols() {
        return carrierRateSheetCols;
    }

    public void setCarrierRateSheetCols(List<RateSheetColumnModel> carrierRateSheetCols) {
        this.carrierRateSheetCols = carrierRateSheetCols;
    }

    public List<RateSheetColumnModel> getNonCarrierRateSheetCols() {
        return nonCarrierRateSheetCols;
    }

    public void setNonCarrierRateSheetCols(List<RateSheetColumnModel> nonCarrierRateSheetCols) {
        this.nonCarrierRateSheetCols = nonCarrierRateSheetCols;
    }

    @Override
    public String toString() {
        return "RateSheetColModel [carrierRateSheetCols=" + carrierRateSheetCols + ", nonCarrierRateSheetCols=" + nonCarrierRateSheetCols + ", shipmentType=" + shipmentType + ", profileBaseRateDetail=" + profileBaseRateDetail + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carrierRateSheetCols == null) ? 0 : carrierRateSheetCols.hashCode());
        result = prime * result + ((nonCarrierRateSheetCols == null) ? 0 : nonCarrierRateSheetCols.hashCode());
        result = prime * result + ((profileBaseRateDetail == null) ? 0 : profileBaseRateDetail.hashCode());
        result = prime * result + ((shipmentType == null) ? 0 : shipmentType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RateSheetColModel other = (RateSheetColModel) obj;
        if (carrierRateSheetCols == null) {
            if (other.carrierRateSheetCols != null)
                return false;
        } else if (!carrierRateSheetCols.equals(other.carrierRateSheetCols))
            return false;
        if (nonCarrierRateSheetCols == null) {
            if (other.nonCarrierRateSheetCols != null)
                return false;
        } else if (!nonCarrierRateSheetCols.equals(other.nonCarrierRateSheetCols))
            return false;
        if (profileBaseRateDetail == null) {
            if (other.profileBaseRateDetail != null)
                return false;
        } else if (!profileBaseRateDetail.equals(other.profileBaseRateDetail))
            return false;
        if (shipmentType == null) {
            if (other.shipmentType != null)
                return false;
        } else if (!shipmentType.equals(other.shipmentType))
            return false;
        return true;
    }

}
