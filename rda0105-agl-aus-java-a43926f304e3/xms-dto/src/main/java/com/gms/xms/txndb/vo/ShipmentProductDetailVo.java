package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonString2DoubleDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class ShipmentProductDetailVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -191263078387695545L;
    private Long shipmentId;
    private String description;
    private String code;
    private String countryOfOrigin;
    private Integer qty;
    private Double amount;
    private Integer noOfCarton;

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAmount() {
        return amount;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getNoOfCarton() {
        return noOfCarton;
    }

    public void setNoOfCarton(Integer noOfCarton) {
        this.noOfCarton = noOfCarton;
    }

    @Override
    public String toString() {
        return "ShipmentProductDetailVo [shipmentId=" + shipmentId + ", description=" + description + ", code=" + code + ", countryOfOrigin=" + countryOfOrigin + ", qty=" + qty + ", amount=" + amount + ", noOfCarton=" + noOfCarton + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((countryOfOrigin == null) ? 0 : countryOfOrigin.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((noOfCarton == null) ? 0 : noOfCarton.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
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
        ShipmentProductDetailVo other = (ShipmentProductDetailVo) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (countryOfOrigin == null) {
            if (other.countryOfOrigin != null)
                return false;
        } else if (!countryOfOrigin.equals(other.countryOfOrigin))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (noOfCarton == null) {
            if (other.noOfCarton != null)
                return false;
        } else if (!noOfCarton.equals(other.noOfCarton))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        return true;
    }

}