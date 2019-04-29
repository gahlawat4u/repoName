package com.gms.xms.model;


/**
 * Posted from QuotePieceVo
 * <p>
 * Author HungNT Date Mar 31, 2015
 */
public class QuotePieceModel extends BaseModel {
    private static final long serialVersionUID = -5300567787012345670L;

    private String quotePieceId;

    private String quoteId;

    private String weight;

    private String cubicWeight;

    private String dimensionL;

    private String dimensionW;

    private String dimensionH;

    private String customValue;

    private String quantity;

    public String getQuotePieceId() {
        return quotePieceId;
    }

    public void setQuotePieceId(String quotePieceId) {
        this.quotePieceId = quotePieceId;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCubicWeight() {
        return cubicWeight;
    }

    public void setCubicWeight(String cubicWeight) {
        this.cubicWeight = cubicWeight;
    }

    @Override
    public String toString() {
        return "QuotePieceModel [quotePieceId=" + quotePieceId + ", quoteId=" + quoteId + ", weight=" + weight + ", cubicWeight=" + cubicWeight + ", dimensionL=" + dimensionL + ", dimensionW=" + dimensionW + ", dimensionH=" + dimensionH + ", customValue=" + customValue + ", quantity=" + quantity + "]";
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
        QuotePieceModel other = (QuotePieceModel) obj;
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