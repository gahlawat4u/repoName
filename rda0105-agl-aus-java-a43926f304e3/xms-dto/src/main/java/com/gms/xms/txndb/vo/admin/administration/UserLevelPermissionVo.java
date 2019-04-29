package com.gms.xms.txndb.vo.admin.administration;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * @author TANDT
 */
public class UserLevelPermissionVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private Integer permissionId;
    private Integer userLevelId;
    private Long franchiseCode;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    @Override
    public String toString() {
        return "UserLevelPermissionVo [permissionId=" + permissionId + ", userLevelId=" + userLevelId + ", franchiseCode=" + franchiseCode + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((franchiseCode == null) ? 0 : franchiseCode.hashCode());
        result = prime * result + ((permissionId == null) ? 0 : permissionId.hashCode());
        result = prime * result + ((userLevelId == null) ? 0 : userLevelId.hashCode());
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
        UserLevelPermissionVo other = (UserLevelPermissionVo) obj;
        if (franchiseCode == null) {
            if (other.franchiseCode != null)
                return false;
        } else if (!franchiseCode.equals(other.franchiseCode))
            return false;
        if (permissionId == null) {
            if (other.permissionId != null)
                return false;
        } else if (!permissionId.equals(other.permissionId))
            return false;
        if (userLevelId == null) {
            if (other.userLevelId != null)
                return false;
        } else if (!userLevelId.equals(other.userLevelId))
            return false;
        return true;
    }

}
