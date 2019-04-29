package com.gms.xms.txndb.vo.webship;

import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.txndb.vo.RateSheetColumnVo;
import com.gms.xms.txndb.vo.RateSheetDetailVo;
import com.gms.xms.txndb.vo.RateSheetRowVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from WebshipRateSheetDetailVo
 * <p>
 * Author HungNT Date Aug 11, 2015
 */
public class WebshipRateSheetDetailVo extends RateSheetDetailVo {

    private static final long serialVersionUID = 397716381035588398L;

    private Double maxWeight;

    private Double maxValue;

    private Double minWeight;

    private Double minValue;

    private String charRowName;

    private RateSheetRowVo rateSheetRow;

    private RateSheetColumnVo rateSheetColumn;

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(Double minWeight) {
        this.minWeight = minWeight;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public RateSheetRowVo getRateSheetRow() {
        return rateSheetRow;
    }

    public void setRateSheetRow(RateSheetRowVo rateSheetRow) {
        this.rateSheetRow = rateSheetRow;
    }

    public RateSheetColumnVo getRateSheetColumn() {
        return rateSheetColumn;
    }

    public void setRateSheetColumn(RateSheetColumnVo rateSheetColumn) {
        this.rateSheetColumn = rateSheetColumn;
    }

    public String getCharRowName() {
        return charRowName;
    }

    public void setCharRowName(String charRowName) {
        this.charRowName = charRowName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((charRowName == null) ? 0 : charRowName.hashCode());
        result = prime * result + ((maxValue == null) ? 0 : maxValue.hashCode());
        result = prime * result + ((maxWeight == null) ? 0 : maxWeight.hashCode());
        result = prime * result + ((minValue == null) ? 0 : minValue.hashCode());
        result = prime * result + ((minWeight == null) ? 0 : minWeight.hashCode());
        result = prime * result + ((rateSheetColumn == null) ? 0 : rateSheetColumn.hashCode());
        result = prime * result + ((rateSheetRow == null) ? 0 : rateSheetRow.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        WebshipRateSheetDetailVo other = (WebshipRateSheetDetailVo) obj;
        if (charRowName == null) {
            if (other.charRowName != null)
                return false;
        } else if (!charRowName.equals(other.charRowName))
            return false;
        if (maxValue == null) {
            if (other.maxValue != null)
                return false;
        } else if (!maxValue.equals(other.maxValue))
            return false;
        if (maxWeight == null) {
            if (other.maxWeight != null)
                return false;
        } else if (!maxWeight.equals(other.maxWeight))
            return false;
        if (minValue == null) {
            if (other.minValue != null)
                return false;
        } else if (!minValue.equals(other.minValue))
            return false;
        if (minWeight == null) {
            if (other.minWeight != null)
                return false;
        } else if (!minWeight.equals(other.minWeight))
            return false;
        if (rateSheetColumn == null) {
            if (other.rateSheetColumn != null)
                return false;
        } else if (!rateSheetColumn.equals(other.rateSheetColumn))
            return false;
        if (rateSheetRow == null) {
            if (other.rateSheetRow != null)
                return false;
        } else if (!rateSheetRow.equals(other.rateSheetRow))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "WebshipRateSheetDetailVo [maxWeight=" + maxWeight + ", maxValue=" + maxValue + ", minWeight=" + minWeight + ", minValue=" + minValue + ", charRowName=" + charRowName + ", rateSheetRow=" + rateSheetRow + ", rateSheetColumn=" + rateSheetColumn + "]";
    }
}
