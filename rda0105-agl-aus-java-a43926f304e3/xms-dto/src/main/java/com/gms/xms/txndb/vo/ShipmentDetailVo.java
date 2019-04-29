package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;

public class ShipmentDetailVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -3459300258171864539L;

    private Long shipmentId;

    private Long accessorialId;

    private BigDecimal amount;

    private AccessorialVo accessorial;

    private Integer type;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(Long accessorialId) {
        this.accessorialId = accessorialId;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessorial == null) ? 0 : accessorial.hashCode());
        result = prime * result + ((accessorialId == null) ? 0 : accessorialId.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        return result;
    }

    public AccessorialVo getAccessorial() {
        return accessorial;
    }

    public void setAccessorial(AccessorialVo accessorial) {
        this.accessorial = accessorial;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShipmentDetailVo other = (ShipmentDetailVo) obj;
        if (accessorial == null) {
            if (other.accessorial != null)
                return false;
        } else if (!accessorial.equals(other.accessorial))
            return false;
        if (accessorialId == null) {
            if (other.accessorialId != null)
                return false;
        } else if (!accessorialId.equals(other.accessorialId))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ShipmentDetailVo [shipmentId=" + shipmentId + ", accessorialId=" + accessorialId + ", amount=" + amount + ", accessorial=" + accessorial + "]";
    }

}