package com.gms.xms.txndb.vo.invoicing;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.common.json.JsonString2DateTimeDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * File Name: DuplicateAirbillVo.java <br/>
 * Author: TANDT <br/>
 * Create Date: 02-12-2015 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo.invoicing <br/>
 * Class: DuplicateAirbillVo
 */
public class DuplicateAirbillVo extends BaseVo {

    private static final long serialVersionUID = -3822510564174625908L;
    private Long shipmentId;
    private String airbillNumber;
    private Long carrier;
    private Date importDate;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public Long getCarrier() {
        return carrier;
    }

    public void setCarrier(Long carrier) {
        this.carrier = carrier;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getImportDate() {
        return importDate;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        return "DuplicateAirbillVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", carrier=" + carrier + ", importDate=" + importDate + "]";
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
        DuplicateAirbillVo other = (DuplicateAirbillVo) obj;
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
