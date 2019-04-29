package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * File Name: DuplicateAirbillModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 02-12-2015 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model.invoicing <br/>
 * Class: DuplicateAirbillModel
 */
public class DuplicateAirbillModel extends BaseModel {

    private static final long serialVersionUID = -1279524160933611001L;
    private String shipmentId;
    private String airbillNumber;
    private String carrier;
    private String importDate;
    @JsonIgnore
    private String note;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "DuplicateAirbillModel [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", carrier=" + carrier + ", importDate=" + importDate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
        result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
        result = prime * result + ((importDate == null) ? 0 : importDate.hashCode());
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
        DuplicateAirbillModel other = (DuplicateAirbillModel) obj;
        if (airbillNumber == null) {
            if (other.airbillNumber != null)
                return false;
        } else if (!airbillNumber.equals(other.airbillNumber))
            return false;
        if (carrier == null) {
            if (other.carrier != null)
                return false;
        } else if (!carrier.equals(other.carrier))
            return false;
        if (importDate == null) {
            if (other.importDate != null)
                return false;
        } else if (!importDate.equals(other.importDate))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        return true;
    }
}
