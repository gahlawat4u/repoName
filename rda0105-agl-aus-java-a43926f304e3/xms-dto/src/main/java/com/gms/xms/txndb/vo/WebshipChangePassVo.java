package com.gms.xms.txndb.vo;

/**
 * Posted from WebshipChangePassVo
 * <p>
 * Author DatTV Date Apr 2, 2015
 */
public class WebshipChangePassVo extends BaseVo {

    private static final long serialVersionUID = 5399129705650590359L;
    private long webshipId;
    private String oldPassword;
    private String newPassword;

    public long getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(long webshipId) {
        this.webshipId = webshipId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "WebshipChangePassVo [webshipId=" + webshipId + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
        result = prime * result + ((oldPassword == null) ? 0 : oldPassword.hashCode());
        result = prime * result + (int) (webshipId ^ (webshipId >>> 32));
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
        WebshipChangePassVo other = (WebshipChangePassVo) obj;
        if (newPassword == null) {
            if (other.newPassword != null)
                return false;
        } else if (!newPassword.equals(other.newPassword))
            return false;
        if (oldPassword == null) {
            if (other.oldPassword != null)
                return false;
        } else if (!oldPassword.equals(other.oldPassword))
            return false;
        if (webshipId != other.webshipId)
            return false;
        return true;
    }
}