package com.gms.xms.model.admin.administration;

import com.gms.xms.model.BaseModel;

/**
 * @author TANDT
 */
public class PermissionResultModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private String setting;
    private String adminPermission;
    private String franchisePermission;
    private String accountPermission;
    private String salePermission;
    private String saleRepPermission;
    private String telePermission;
    private String carrierPermission;
    private String carrierServicePermission;
    private String perId;

    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getAdminPermission() {
        return adminPermission;
    }

    public void setAdminPermission(String adminPermission) {
        this.adminPermission = adminPermission;
    }

    public String getFranchisePermission() {
        return franchisePermission;
    }

    public void setFranchisePermission(String franchisePermission) {
        this.franchisePermission = franchisePermission;
    }

    public String getAccountPermission() {
        return accountPermission;
    }

    public void setAccountPermission(String accountPermission) {
        this.accountPermission = accountPermission;
    }

    public String getSalePermission() {
        return salePermission;
    }

    public void setSalePermission(String salePermission) {
        this.salePermission = salePermission;
    }

    public String getSaleRepPermission() {
        return saleRepPermission;
    }

    public void setSaleRepPermission(String saleRepPermission) {
        this.saleRepPermission = saleRepPermission;
    }

    public String getTelePermission() {
        return telePermission;
    }

    public void setTelePermission(String telePermission) {
        this.telePermission = telePermission;
    }

    public String getCarrierPermission() {
        return carrierPermission;
    }

    public void setCarrierPermission(String carrierPermission) {
        this.carrierPermission = carrierPermission;
    }

    public String getCarrierServicePermission() {
        return carrierServicePermission;
    }

    public void setCarrierServicePermission(String carrierServicePermission) {
        this.carrierServicePermission = carrierServicePermission;
    }

    @Override
    public String toString() {
        return "PermissionResultModel [setting=" + setting + ", adminPermission=" + adminPermission + ", franchisePermission=" + franchisePermission + ", accountPermission=" + accountPermission + ", salePermission=" + salePermission + ", saleRepPermission=" + saleRepPermission + ", telePermission=" + telePermission + ", carrierPermission=" + carrierPermission + ", carrierServicePermission=" + carrierServicePermission + ", perId=" + perId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountPermission == null) ? 0 : accountPermission.hashCode());
        result = prime * result + ((adminPermission == null) ? 0 : adminPermission.hashCode());
        result = prime * result + ((carrierPermission == null) ? 0 : carrierPermission.hashCode());
        result = prime * result + ((carrierServicePermission == null) ? 0 : carrierServicePermission.hashCode());
        result = prime * result + ((franchisePermission == null) ? 0 : franchisePermission.hashCode());
        result = prime * result + ((perId == null) ? 0 : perId.hashCode());
        result = prime * result + ((salePermission == null) ? 0 : salePermission.hashCode());
        result = prime * result + ((saleRepPermission == null) ? 0 : saleRepPermission.hashCode());
        result = prime * result + ((setting == null) ? 0 : setting.hashCode());
        result = prime * result + ((telePermission == null) ? 0 : telePermission.hashCode());
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
        PermissionResultModel other = (PermissionResultModel) obj;
        if (accountPermission == null) {
            if (other.accountPermission != null)
                return false;
        } else if (!accountPermission.equals(other.accountPermission))
            return false;
        if (adminPermission == null) {
            if (other.adminPermission != null)
                return false;
        } else if (!adminPermission.equals(other.adminPermission))
            return false;
        if (carrierPermission == null) {
            if (other.carrierPermission != null)
                return false;
        } else if (!carrierPermission.equals(other.carrierPermission))
            return false;
        if (carrierServicePermission == null) {
            if (other.carrierServicePermission != null)
                return false;
        } else if (!carrierServicePermission.equals(other.carrierServicePermission))
            return false;
        if (franchisePermission == null) {
            if (other.franchisePermission != null)
                return false;
        } else if (!franchisePermission.equals(other.franchisePermission))
            return false;
        if (perId == null) {
            if (other.perId != null)
                return false;
        } else if (!perId.equals(other.perId))
            return false;
        if (salePermission == null) {
            if (other.salePermission != null)
                return false;
        } else if (!salePermission.equals(other.salePermission))
            return false;
        if (saleRepPermission == null) {
            if (other.saleRepPermission != null)
                return false;
        } else if (!saleRepPermission.equals(other.saleRepPermission))
            return false;
        if (setting == null) {
            if (other.setting != null)
                return false;
        } else if (!setting.equals(other.setting))
            return false;
        if (telePermission == null) {
            if (other.telePermission != null)
                return false;
        } else if (!telePermission.equals(other.telePermission))
            return false;
        return true;
    }

}
