package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.common.json.JsonString2DateTimeDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from NoteVo
 * <p>
 * Author DatTV Date May 11, 2015 11:44:11 AM
 */
public class NoteVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private Long noteId;

    private Long userId;

    private Long accountNo;

    private Byte noteType;

    private Date followUpDate;

    private Date modifyDate;

    private Boolean check;

    private Byte current;

    private String invoiceCode;

    private Long paymentId;

    private String note;

    private UserVo user;

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public Byte getNoteType() {
        return noteType;
    }

    public void setNoteType(Byte noteType) {
        this.noteType = noteType;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getFollowUpDate() {
        return followUpDate;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getModifyDate() {
        return modifyDate;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public Byte getCurrent() {
        return current;
    }

    public void setCurrent(Byte current) {
        this.current = current;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
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
        return "NoteVo [noteId=" + noteId + ", userId=" + userId + ", accountNo=" + accountNo + ", noteType=" + noteType + ", followUpDate=" + followUpDate + ", modifyDate=" + modifyDate + ", check=" + check + ", current=" + current + ", invoiceCode=" + invoiceCode + ", paymentId=" + paymentId + ", note=" + note + "]";
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }
}