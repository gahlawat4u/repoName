package com.gms.xms.txndb.vo.admin.administration;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * @author TANDT
 */
public class PermissionVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 343780907764393629L;

    private Long permissionId;
    private String permissionName;
    private String localizationId;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }

    @Override
    public String toString() {
        return "PermissionVo [permissionId=" + permissionId + ", permissionName=" + permissionName + ", localizationId=" + localizationId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((localizationId == null) ? 0 : localizationId.hashCode());
        result = prime * result + ((permissionId == null) ? 0 : permissionId.hashCode());
        result = prime * result + ((permissionName == null) ? 0 : permissionName.hashCode());
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
        PermissionVo other = (PermissionVo) obj;
        if (localizationId == null) {
            if (other.localizationId != null)
                return false;
        } else if (!localizationId.equals(other.localizationId))
            return false;
        if (permissionId == null) {
            if (other.permissionId != null)
                return false;
        } else if (!permissionId.equals(other.permissionId))
            return false;
        if (permissionName == null) {
            if (other.permissionName != null)
                return false;
        } else if (!permissionName.equals(other.permissionName))
            return false;
        return true;
    }

}
