package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.sql.Date;

public class ShipmentNoteVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -2302898635883481256L;

    private Integer id;

    private Long shipmentId;

    private Date createDate;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ShipmentNoteVo [id=" + id + ", shipmentId=" + shipmentId + ", createDate=" + createDate + ", note=" + note + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
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
        ShipmentNoteVo other = (ShipmentNoteVo) obj;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        return true;
    }

}
