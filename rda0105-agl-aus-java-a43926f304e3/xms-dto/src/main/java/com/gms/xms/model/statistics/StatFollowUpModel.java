package com.gms.xms.model.statistics;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Aug 19, 2016 2:36:30 PM
 * <p>
 * Author dattrinh
 */
public class StatFollowUpModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String noteId;
    private String modifyDate;
    private String followUpDate;
    private String note;
    private String check;
    private String current;
    private String customerCode;
    private String customerName;
    private String saleRepName;

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
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
        return "StatFollowUpModel [noteId=" + noteId + ", modifyDate=" + modifyDate + ", followUpDate=" + followUpDate + ", note=" + note + ", check=" + check + ", current=" + current + ", customerCode=" + customerCode + ", customerName=" + customerName + ", saleRepName=" + saleRepName + "]";
    }
}
