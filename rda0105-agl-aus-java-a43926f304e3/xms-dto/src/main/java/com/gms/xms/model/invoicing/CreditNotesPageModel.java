package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CreditNotesPageModel
 * <p>
 * Author HungNT Date May 26, 2015
 */
public class CreditNotesPageModel extends BaseModel {
    private static final long serialVersionUID = 6392643823035273209L;

    private String creditNoteCode;
    private String creditNoteCodes;
    private String grandTotal;
    private String htmlExportContent;
    private String franchiseCode;
    private String customerCode;
    private String filterBy;
    private String dateSelect;

    public String getCreditNoteCode() {
        return creditNoteCode;
    }

    public void setCreditNoteCode(String creditNoteCode) {
        this.creditNoteCode = creditNoteCode;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getHtmlExportContent() {
        return htmlExportContent;
    }

    public void setHtmlExportContent(String htmlExportContent) {
        this.htmlExportContent = htmlExportContent;
    }

    public String getCreditNoteCodes() {
        return creditNoteCodes;
    }

    public void setCreditNoteCodes(String creditNoteCodes) {
        this.creditNoteCodes = creditNoteCodes;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    public String getDateSelect() {
        return dateSelect;
    }

    public void setDateSelect(String dateSelect) {
        this.dateSelect = dateSelect;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    @Override
    public String toString() {
        return "CreditNotesPageModel [creditNoteCode=" + creditNoteCode + ", creditNoteCodes=" + creditNoteCodes + ", grandTotal=" + grandTotal + ", htmlExportContent=" + htmlExportContent + ", franchiseCode=" + franchiseCode + ", customerCode=" + customerCode + ", filterBy=" + filterBy + ", dateSelect=" + dateSelect + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creditNoteCode == null) ? 0 : creditNoteCode.hashCode());
        result = prime * result + ((creditNoteCodes == null) ? 0 : creditNoteCodes.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((dateSelect == null) ? 0 : dateSelect.hashCode());
        result = prime * result + ((filterBy == null) ? 0 : filterBy.hashCode());
        result = prime * result + ((franchiseCode == null) ? 0 : franchiseCode.hashCode());
        result = prime * result + ((grandTotal == null) ? 0 : grandTotal.hashCode());
        result = prime * result + ((htmlExportContent == null) ? 0 : htmlExportContent.hashCode());
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
        CreditNotesPageModel other = (CreditNotesPageModel) obj;
        if (creditNoteCode == null) {
            if (other.creditNoteCode != null)
                return false;
        } else if (!creditNoteCode.equals(other.creditNoteCode))
            return false;
        if (creditNoteCodes == null) {
            if (other.creditNoteCodes != null)
                return false;
        } else if (!creditNoteCodes.equals(other.creditNoteCodes))
            return false;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (dateSelect == null) {
            if (other.dateSelect != null)
                return false;
        } else if (!dateSelect.equals(other.dateSelect))
            return false;
        if (filterBy == null) {
            if (other.filterBy != null)
                return false;
        } else if (!filterBy.equals(other.filterBy))
            return false;
        if (franchiseCode == null) {
            if (other.franchiseCode != null)
                return false;
        } else if (!franchiseCode.equals(other.franchiseCode))
            return false;
        if (grandTotal == null) {
            if (other.grandTotal != null)
                return false;
        } else if (!grandTotal.equals(other.grandTotal))
            return false;
        if (htmlExportContent == null) {
            if (other.htmlExportContent != null)
                return false;
        } else if (!htmlExportContent.equals(other.htmlExportContent))
            return false;
        return true;
    }
}
