package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDecimalString2DoubleDeserializer;
import com.gms.xms.common.json.JsonDoubleSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from QuotePieceVo
 * <p>
 * Author HungNT Date Mar 31, 2015
 */
public class QuotePieceVo extends BaseVo {
    private static final long serialVersionUID = 3891718188768390430L;

    private Long quotePieceId;

    private Long quoteId;

    private Double weight;

    private Double cubicWeight;

    private Double dimensionL;

    private Double dimensionW;

    private Double dimensionH;

    private Double customValue;

    private Integer quantity;

    public Long getQuotePieceId() {
        return quotePieceId;
    }

    public void setQuotePieceId(Long quotePieceId) {
        this.quotePieceId = quotePieceId;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getCubicWeight() {
        return cubicWeight;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setCubicWeight(Double cubicWeight) {
        this.cubicWeight = cubicWeight;
    }

    @Override
    public String toString() {
        return "QuotePieceVo [quotePieceId=" + quotePieceId + ", quoteId=" + quoteId + ", weight=" + weight + ", cubicWeight=" + cubicWeight + ", dimensionL=" + dimensionL + ", dimensionW=" + dimensionW + ", dimensionH=" + dimensionH + ", customValue=" + customValue + ", quantity=" + quantity + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cubicWeight == null) ? 0 : cubicWeight.hashCode());
        result = prime * result + ((customValue == null) ? 0 : customValue.hashCode());
        result = prime * result + ((dimensionH == null) ? 0 : dimensionH.hashCode());
        result = prime * result + ((dimensionL == null) ? 0 : dimensionL.hashCode());
        result = prime * result + ((dimensionW == null) ? 0 : dimensionW.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((quoteId == null) ? 0 : quoteId.hashCode());
        result = prime * result + ((quotePieceId == null) ? 0 : quotePieceId.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
        QuotePieceVo other = (QuotePieceVo) obj;
        if (cubicWeight == null) {
            if (other.cubicWeight != null)
                return false;
        } else if (!cubicWeight.equals(other.cubicWeight))
            return false;
        if (customValue == null) {
            if (other.customValue != null)
                return false;
        } else if (!customValue.equals(other.customValue))
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
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (quoteId == null) {
            if (other.quoteId != null)
                return false;
        } else if (!quoteId.equals(other.quoteId))
            return false;
        if (quotePieceId == null) {
            if (other.quotePieceId != null)
                return false;
        } else if (!quotePieceId.equals(other.quotePieceId))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        return true;
    }
}