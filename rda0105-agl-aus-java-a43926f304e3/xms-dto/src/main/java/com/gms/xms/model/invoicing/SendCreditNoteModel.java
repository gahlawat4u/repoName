package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;

/**
 * Posted from SendCreditNoteModel
 * <p>
 * Author HungNT Date May 20, 2015
 */
public class SendCreditNoteModel extends BaseModel {
    private static final long serialVersionUID = 6832824278180120620L;

    private String franchiseCode;
    private String createDate;
    private String creditNoteCode;

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreditNoteCode() {
        return creditNoteCode;
    }

    public void setCreditNoteCode(String creditNoteCode) {
        this.creditNoteCode = creditNoteCode;
    }

    @Override
    public String toString() {
        return "SendCreditNoteModel [franchiseCode=" + franchiseCode + ", createDate=" + createDate + ", creditNoteCode=" + creditNoteCode + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((creditNoteCode == null) ? 0 : creditNoteCode.hashCode());
        result = prime * result + ((franchiseCode == null) ? 0 : franchiseCode.hashCode());
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
        SendCreditNoteModel other = (SendCreditNoteModel) obj;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (creditNoteCode == null) {
            if (other.creditNoteCode != null)
                return false;
        } else if (!creditNoteCode.equals(other.creditNoteCode))
            return false;
        if (franchiseCode == null) {
            if (other.franchiseCode != null)
                return false;
        } else if (!franchiseCode.equals(other.franchiseCode))
            return false;
        return true;
    }
}
