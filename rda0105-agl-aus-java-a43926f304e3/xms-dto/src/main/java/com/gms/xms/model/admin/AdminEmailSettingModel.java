package com.gms.xms.model.admin;

import com.gms.xms.model.BaseModel;


/**
 * @author TANDT
 */
public class AdminEmailSettingModel extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = -4454085773964789965L;

    private String id;

    private String emailSettingId;

    private String adminEmailId;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailSettingId() {
        return emailSettingId;
    }

    public void setEmailSettingId(String emailSettingId) {
        this.emailSettingId = emailSettingId;
    }

    public String getAdminEmailId() {
        return adminEmailId;
    }

    public void setAdminEmailId(String adminEmailId) {
        this.adminEmailId = adminEmailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AdminEmailSettingVo [id=" + id + ", emailSettingId=" + emailSettingId + ", adminEmailId=" + adminEmailId + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adminEmailId == null) ? 0 : adminEmailId.hashCode());
        result = prime * result + ((emailSettingId == null) ? 0 : emailSettingId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        AdminEmailSettingModel other = (AdminEmailSettingModel) obj;
        if (adminEmailId == null) {
            if (other.adminEmailId != null)
                return false;
        } else if (!adminEmailId.equals(other.adminEmailId))
            return false;
        if (emailSettingId == null) {
            if (other.emailSettingId != null)
                return false;
        } else if (!emailSettingId.equals(other.emailSettingId))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }


}