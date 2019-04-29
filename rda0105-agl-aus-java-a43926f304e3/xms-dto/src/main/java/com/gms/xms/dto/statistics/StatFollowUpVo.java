package com.gms.xms.dto.statistics;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from Aug 19, 2016 2:36:30 PM
 * <p>
 * Author dattrinh
 */
public class StatFollowUpVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long noteId;
    private Date modifyDate;
    private Date followUpDate;
    private String note;
    private Integer check;
    private Integer current;
    private String customerCode;
    private String customerName;
    private String saleRepName;

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSaleRepName() {
        return saleRepName;
    }

    public void setSaleRepName(String saleRepName) {
        this.saleRepName = saleRepName;
    }

    @Override
    public String toString() {
        return "StatFollowUpVo [noteId=" + noteId + ", modifyDate=" + modifyDate + ", followUpDate=" + followUpDate + ", note=" + note + ", check=" + check + ", current=" + current + ", customerCode=" + customerCode + ", customerName=" + customerName + ", saleRepName=" + saleRepName + "]";
    }
}
