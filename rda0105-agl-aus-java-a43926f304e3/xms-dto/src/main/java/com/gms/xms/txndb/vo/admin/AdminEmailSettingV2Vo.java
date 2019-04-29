package com.gms.xms.txndb.vo.admin;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * @author TANDT
 */
public class AdminEmailSettingV2Vo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 855159502850828307L;

    private Integer id;
    private Integer idSetting;
    private String email;
    private Boolean emailInvoiceConfirm;
    private Boolean ediImportNotify;
    private Boolean supplyRequest;
    private Date actionDate;

    public Integer getId() {
        return id;
    }

    public Integer getIdSetting() {
        return idSetting;
    }

    public void setIdSetting(Integer idSetting) {
        this.idSetting = idSetting;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailInvoiceConfirm() {
        return emailInvoiceConfirm;
    }

    public void setEmailInvoiceConfirm(Boolean emailInvoiceConfirm) {
        this.emailInvoiceConfirm = emailInvoiceConfirm;
    }

    public Boolean getEdiImportNotify() {
        return ediImportNotify;
    }

    public void setEdiImportNotify(Boolean ediImportNotify) {
        this.ediImportNotify = ediImportNotify;
    }

    public Boolean getSupplyRequest() {
        return supplyRequest;
    }

    public void setSupplyRequest(Boolean supplyRequest) {
        this.supplyRequest = supplyRequest;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getActionDate() {
        return actionDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    @Override
    public String toString() {
        return "AdminEmailSettingV2Vo [id=" + id + ", idSetting=" + idSetting + ", email=" + email + ", emailInvoiceConfirm=" + emailInvoiceConfirm + ", ediImportNotify=" + ediImportNotify + ", supplyRequest=" + supplyRequest + ", actionDate=" + actionDate + "]";
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
        AdminEmailSettingV2Vo other = (AdminEmailSettingV2Vo) obj;
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
