package com.gms.xms.model.webship.history;

import com.gms.xms.model.BaseModel;

/**
 * Posted from HistoryDetailAccessorialModel
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailAccessorialModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 8770002259057982182L;

    private String shipmentId;
    private String accessorialId;
    private String amount;
    private String defaultCharge;
    private String description;
    private String code;
    private String typeId;
    private String carrierId;
    private Integer type;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(String accessorialId) {
        this.accessorialId = accessorialId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDefaultCharge() {
        return defaultCharge;
    }

    public void setDefaultCharge(String defaultCharge) {
        this.defaultCharge = defaultCharge;
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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HistoryDetailAccessorialModel [shipmentId=" + shipmentId + ", accessorialId=" + accessorialId + ", amount=" + amount + ", defaultCharge=" + defaultCharge + ", description=" + description + ", code=" + code + ", typeId=" + typeId + ", carrierId=" + carrierId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessorialId == null) ? 0 : accessorialId.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((carrierId == null) ? 0 : carrierId.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((defaultCharge == null) ? 0 : defaultCharge.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
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
        HistoryDetailAccessorialModel other = (HistoryDetailAccessorialModel) obj;
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
        if (carrierId == null) {
            if (other.carrierId != null)
                return false;
        } else if (!carrierId.equals(other.carrierId))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (defaultCharge == null) {
            if (other.defaultCharge != null)
                return false;
        } else if (!defaultCharge.equals(other.defaultCharge))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (typeId == null) {
            if (other.typeId != null)
                return false;
        } else if (!typeId.equals(other.typeId))
            return false;
        return true;
    }

}