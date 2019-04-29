package com.gms.xms.model.webship.settings;

import com.gms.xms.model.BaseModel;

/**
 * Posted from WebshipPasswordModel
 * <p>
 * Author DatTV Date Jul 14, 2015 9:54:10 AM
 */
public class WebshipPasswordModel extends BaseModel {

    private static final long serialVersionUID = 6976653697852080316L;

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    @Override
    public String toString() {
        return "WebshipPasswordModel [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", confirmPassword=" + confirmPassword + "]";
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