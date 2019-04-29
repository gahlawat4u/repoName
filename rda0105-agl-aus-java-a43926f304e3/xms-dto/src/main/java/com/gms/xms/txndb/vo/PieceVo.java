package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBoolean2StringSerializer;
import com.gms.xms.common.json.JsonDecimalString2DoubleDeserializer;
import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.common.json.JsonString2BooleanDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from PieceVo
 * <p>
 * Author TanDT Date Mar 27, 2015
 */
public class PieceVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -8581386113366390285L;

    private Long pieceId;

    private Long shipmentId;

    private Double weight;

    private Double dimensionL;

    private Double dimensionW;

    private Double dimensionH;

    private Double customValue;

    private String licensePlate;

    private String licensePlateBarcode;

    private String dataIdentifier;

    private Double deadWeight;

    private Integer quantity;

    private String dimension;

    private Boolean nonStandardPackage;


    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Long getPieceId() {
        return pieceId;
    }

    public void setPieceId(Long pieceId) {
        this.pieceId = pieceId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getWeight() {
        return weight;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getDimensionL() {
        return dimensionL;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setDimensionL(Double dimensionL) {
        this.dimensionL = dimensionL;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getDimensionW() {
        return dimensionW;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setDimensionW(Double dimensionW) {
        this.dimensionW = dimensionW;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getDimensionH() {
        return dimensionH;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setDimensionH(Double dimensionH) {
        this.dimensionH = dimensionH;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getCustomValue() {
        return customValue;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setCustomValue(Double customValue) {
        this.customValue = customValue;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate == null ? null : licensePlate.trim();
    }

    public String getLicensePlateBarcode() {
        return licensePlateBarcode;
    }

    public void setLicensePlateBarcode(String licensePlateBarcode) {
        this.licensePlateBarcode = licensePlateBarcode == null ? null : licensePlateBarcode.trim();
    }

    public String getDataIdentifier() {
        return dataIdentifier;
    }

    public void setDataIdentifier(String dataIdentifier) {
        this.dataIdentifier = dataIdentifier == null ? null : dataIdentifier.trim();
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getDeadWeight() {
        return deadWeight;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setDeadWeight(Double deadWeight) {
        this.deadWeight = deadWeight;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @JsonSerialize(using = JsonBoolean2StringSerializer.class)
    public Boolean getNonStandardPackage() {
        return nonStandardPackage;
    }

    @JsonDeserialize(using = JsonString2BooleanDeserializer.class)
    public void setNonStandardPackage(Boolean nonStandardPackage) {
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
        PieceVo other = (PieceVo) obj;
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
        return "PieceVo [pieceId=" + pieceId + ", shipmentId=" + shipmentId + ", weight=" + weight + ", dimensionL=" + dimensionL + ", dimensionW=" + dimensionW + ", dimensionH=" + dimensionH + ", customValue=" + customValue + ", licensePlate=" + licensePlate + ", licensePlateBarcode=" + licensePlateBarcode + ", dataIdentifier=" + dataIdentifier + ", deadWeight=" + deadWeight + ", quantity=" + quantity + ", dimension=" + dimension +  ", nonStandardPackage=" + nonStandardPackage + "]";
    }

}