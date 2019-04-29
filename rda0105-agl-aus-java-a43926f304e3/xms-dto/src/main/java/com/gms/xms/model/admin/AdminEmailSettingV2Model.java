package com.gms.xms.model.admin;

import com.gms.xms.model.BaseModel;

/**
 * @author TANDT
 */
public class AdminEmailSettingV2Model extends BaseModel {
    private static final long serialVersionUID = 855159502850828307L;

    private String id;
    private String idSetting;
    private String email;
    private String emailInvoiceConfirm;
    private String ediImportNotify;
    private String supplyRequest;
    private String actionDate;

    public String getId() {
        return id;
    }

    public String getIdSetting() {
        return idSetting;
    }

    public void setIdSetting(String idSetting) {
        this.idSetting = idSetting;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailInvoiceConfirm() {
        return emailInvoiceConfirm;
    }

    public void setEmailInvoiceConfirm(String emailInvoiceConfirm) {
        this.emailInvoiceConfirm = emailInvoiceConfirm;
    }

    public String getEdiImportNotify() {
        return ediImportNotify;
    }

    public void setEdiImportNotify(String ediImportNotify) {
        this.ediImportNotify = ediImportNotify;
    }

    public String getSupplyRequest() {
        return supplyRequest;
    }

    public void setSupplyRequest(String supplyRequest) {
        this.supplyRequest = supplyRequest;
    }

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
    }

    @Override
    public String toString() {
        return "AdminEmailSettingV2Model [id=" + id + ", idSetting=" + idSetting + ", email=" + email + ", emailInvoiceConfirm=" + emailInvoiceConfirm + ", ediImportNotify=" + ediImportNotify + ", supplyRequest=" + supplyRequest + ", actionDate=" + actionDate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actionDate == null) ? 0 : actionDate.hashCode());
        result = prime * result + ((ediImportNotify == null) ? 0 : ediImportNotify.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((emailInvoiceConfirm == null) ? 0 : emailInvoiceConfirm.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((idSetting == null) ? 0 : idSetting.hashCode());
        result = prime * result + ((supplyRequest == null) ? 0 : supplyRequest.hashCode());
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
        AdminEmailSettingV2Model other = (AdminEmailSettingV2Model) obj;
        if (actionDate == null) {
            if (other.actionDate != null)
                return false;
        } else if (!actionDate.equals(other.actionDate))
            return false;
        if (ediImportNotify == null) {
            if (other.ediImportNotify != null)
                return false;
        } else if (!ediImportNotify.equals(other.ediImportNotify))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (emailInvoiceConfirm == null) {
            if (other.emailInvoiceConfirm != null)
                return false;
        } else if (!emailInvoiceConfirm.equals(other.emailInvoiceConfirm))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (idSetting == null) {
            if (other.idSetting != null)
                return false;
        } else if (!idSetting.equals(other.idSetting))
            return false;
        if (supplyRequest == null) {
            if (other.supplyRequest != null)
                return false;
        } else if (!supplyRequest.equals(other.supplyRequest))
            return false;
        return true;
    }

}
