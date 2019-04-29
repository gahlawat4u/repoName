package com.gms.xms.txndb.vo.admin.administration;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;
import com.gms.xms.txndb.vo.RateSheetColumnVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;

import java.util.List;

/**
 * Posted from RateSheetColVo
 * <p>
 * Author TANDT 28-10-2015
 */
public class RateSheetColVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;
    protected List<RateSheetColumnVo> carrierRateSheetCols;
    protected List<RateSheetColumnVo> nonCarrierRateSheetCols;
    protected ShipmentTypeVo shipmentType;
    protected List<CustomerBaseRateVo> profileBaseRateDetail;

    public List<CustomerBaseRateVo> getProfileBaseRateDetail() {
        return profileBaseRateDetail;
    }

    public void setProfileBaseRateDetail(List<CustomerBaseRateVo> profileBaseRateDetail) {
        this.profileBaseRateDetail = profileBaseRateDetail;
    }

    public ShipmentTypeVo getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentTypeVo shipmentType) {
        this.shipmentType = shipmentType;
    }

    public List<RateSheetColumnVo> getCarrierRateSheetCols() {
        return carrierRateSheetCols;
    }

    public void setCarrierRateSheetCols(List<RateSheetColumnVo> carrierRateSheetCols) {
        this.carrierRateSheetCols = carrierRateSheetCols;
    }

    public List<RateSheetColumnVo> getNonCarrierRateSheetCols() {
        return nonCarrierRateSheetCols;
    }

    public void setNonCarrierRateSheetCols(List<RateSheetColumnVo> nonCarrierRateSheetCols) {
        this.nonCarrierRateSheetCols = nonCarrierRateSheetCols;
    }

    @Override
    public String toString() {
        return "RateSheetColVo [carrierRateSheetCols=" + carrierRateSheetCols + ", nonCarrierRateSheetCols=" + nonCarrierRateSheetCols + ", shipmentType=" + shipmentType + ", profileBaseRateDetail=" + profileBaseRateDetail + "]";
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
        RateSheetColVo other = (RateSheetColVo) obj;
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
