package com.gms.xms.model;

/**
 * Posted from NoteModel
 * <p>
 * Author DatTV Date May 11, 2015 11:59:40 AM
 */
public class NoteModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String noteId;

    private String userId;

    private String accountNo;

    private String noteType;

    private String followUpDate;

    private String modifyDate;

    private String check;

    private String current;

    private String invoiceCode;

    private String paymentId;

    private String note;

    private UserModel user;

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
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

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "NoteModel [noteId=" + noteId + ", userId=" + userId + ", accountNo=" + accountNo + ", noteType=" + noteType + ", followUpDate=" + followUpDate + ", modifyDate=" + modifyDate + ", check=" + check + ", current=" + current + ", invoiceCode=" + invoiceCode + ", paymentId=" + paymentId + ", note=" + note + "]";
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}