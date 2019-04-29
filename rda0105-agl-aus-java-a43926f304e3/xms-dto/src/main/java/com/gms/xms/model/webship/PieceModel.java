package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;


/**
 * Posted from PieceModel
 * <p>
 * Author TanDT Date Mar 27, 2015
 */
public class PieceModel extends BaseModel {


    /**
     *
     */
    private static final long serialVersionUID = -6962118997792926977L;

    private String pieceId;

    private String shipmentId;

    private String weight;

    private String dimensionL;

    private String dimensionW;

    private String dimensionH;

    private String customValue = "0.00";

    private String licensePlate;

    private String licensePlateBarcode;

    private String dataIdentifier;

    private String deadWeight;

    private String quantity = "1";

    private String dimension;

    private String nonStandardPackage;

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getPieceId() {
        return pieceId;
    }

    public void setPieceId(String pieceId) {
        this.pieceId = pieceId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {

        this.weight = weight;
    }

    public String getDimensionL() {
        return dimensionL;
    }

    public void setDimensionL(String dimensionL) {
        this.dimensionL = dimensionL;
    }

    public String getDimensionW() {
        return dimensionW;
    }

    public void setDimensionW(String dimensionW) {
        this.dimensionW = dimensionW;
    }

    public String getDimensionH() {
        return dimensionH;
    }

    public void setDimensionH(String dimensionH) {
        this.dimensionH = dimensionH;
    }

    public String getCustomValue() {
        return customValue;
    }

    public void setCustomValue(String customValue) {
        this.customValue = customValue;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlateBarcode() {
        return licensePlateBarcode;
    }

    public void setLicensePlateBarcode(String licensePlateBarcode) {
        this.licensePlateBarcode = licensePlateBarcode;
    }

    public String getDataIdentifier() {
        return dataIdentifier;
    }

    public void setDataIdentifier(String dataIdentifier) {
        this.dataIdentifier = dataIdentifier;
    }

    public String getDeadWeight() {
        return deadWeight;
    }

    public void setDeadWeight(String deadWeight) {
        this.deadWeight = deadWeight;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNonStandardPackage() {
        return nonStandardPackage;
    }

    public void setNonStandardPackage(String nonStandardPackage) {
        this.nonStandardPackage = nonStandardPackage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customValue == null) ? 0 : customValue.hashCode());
        result = prime * result + ((dataIdentifier == null) ? 0 : dataIdentifier.hashCode());
        result = prime * result + ((deadWeight == null) ? 0 : deadWeight.hashCode());
        result = prime * result + ((dimension == null) ? 0 : dimension.hashCode());
        result = prime * result + ((dimensionH == null) ? 0 : dimensionH.hashCode());
        result = prime * result + ((dimensionL == null) ? 0 : dimensionL.hashCode());
        result = prime * result + ((dimensionW == null) ? 0 : dimensionW.hashCode());
        result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
        result = prime * result + ((licensePlateBarcode == null) ? 0 : licensePlateBarcode.hashCode());
        result = prime * result + ((pieceId == null) ? 0 : pieceId.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((nonStandardPackage == null) ? 0 : nonStandardPackage.hashCode());
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
        PieceModel other = (PieceModel) obj;
        if (customValue == null) {
            if (other.customValue != null)
                return false;
        } else if (!customValue.equals(other.customValue))
            return false;
        if (dataIdentifier == null) {
            if (other.dataIdentifier != null)
                return false;
        } else if (!dataIdentifier.equals(other.dataIdentifier))
            return false;
        if (deadWeight == null) {
            if (other.deadWeight != null)
                return false;
        } else if (!deadWeight.equals(other.deadWeight))
            return false;
        if (dimension == null) {
            if (other.dimension != null)
                return false;
        } else if (!dimension.equals(other.dimension))
            return false;
        if (dimensionH == null) {
            if (other.dimensionH != null)
                return false;
        } else if (!dimensionH.equals(other.dimensionH))
            return false;
        if (dimensionL == null) {
            if (other.dimensionL != null)
                return false;
        } else if (!dimensionL.equals(other.dimensionL))
            return false;
        if (dimensionW == null) {
            if (other.dimensionW != null)
                return false;
        } else if (!dimensionW.equals(other.dimensionW))
            return false;
        if (licensePlate == null) {
            if (other.licensePlate != null)
                return false;
        } else if (!licensePlate.equals(other.licensePlate))
            return false;
        if (licensePlateBarcode == null) {
            if (other.licensePlateBarcode != null)
                return false;
        } else if (!licensePlateBarcode.equals(other.licensePlateBarcode))
            return false;
        if (pieceId == null) {
            if (other.pieceId != null)
                return false;
        } else if (!pieceId.equals(other.pieceId))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        if (nonStandardPackage == null) {
            if (other.nonStandardPackage != null)
                return false;
        } else if (!nonStandardPackage.equals(other.nonStandardPackage))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PieceModel [pieceId=" + pieceId + ", shipmentId=" + shipmentId + ", weight=" + weight + ", dimensionL=" + dimensionL + ", dimensionW=" + dimensionW + ", dimensionH=" + dimensionH + ", customValue=" + customValue + ", licensePlate=" + licensePlate + ", licensePlateBarcode=" + licensePlateBarcode + ", dataIdentifier=" + dataIdentifier + ", deadWeight=" + deadWeight + ", quantity=" + quantity + ", dimension=" + dimension + ", nonStandardPackage=" + nonStandardPackage + "]";
    }


}
