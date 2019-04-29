package com.gms.xms.txndb.vo.webship.settings;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from WebshipPasswordVo
 * <p>
 * Author DatTV Date Jul 14, 2015 9:51:11 AM
 */
public class WebshipPasswordVo extends BaseVo {

    private static final long serialVersionUID = 3855898784710713699L;

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    @Override
    public String toString() {
        return "WebshipPasswordVo [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", confirmPassword=" + confirmPassword + "]";
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}