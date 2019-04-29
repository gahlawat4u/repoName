package com.gms.xms.weblib.controller.webship.settings;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.CryptUtils;
import com.gms.xms.common.utils.PasswordUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.settings.WebshipPasswordModel;
import com.gms.xms.persistence.service.webship.settings.IWebshipPasswordService;
import com.gms.xms.persistence.service.webship.settings.WebshipPasswordServiceImp;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.settings.WebshipPasswordVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

/**
 * Posted from ChangePasswordController
 * <p>
 * Author DatTV Date Jul 14, 2015 9:49:47 AM
 */
public class ChangePasswordController extends JsonBaseController {
    private static final long serialVersionUID = -2662141902109505297L;

    private WebshipPasswordModel user;

    public String changePassword() {
        // Validate user information
        if (user == null || !validateUser()) {
            return "success";
        }

        // Check user login
        WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
        if (webshipLoginVo == null) {
            addActionError("You don't have permission to do this action");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return "success";
        }

        // Check input information
        try {
            WebshipPasswordVo passwordVo = ModelUtils.createVoFromModel(user, WebshipPasswordVo.class);
            String password = CryptUtils.Encrypt(passwordVo.getOldPassword(), AppConstants.APP_SETTINGS.getEncryptionKey());
            if (!password.equals(webshipLoginVo.getPassword())) {
                addFieldError("user.oldPassword", "The old password is incorrect");
                setErrorCode(ErrorCode.FIELD_ERROR);
            } else {
                password = CryptUtils.Encrypt(passwordVo.getNewPassword(), AppConstants.APP_SETTINGS.getEncryptionKey());
                WebshipVo webshipVo = new WebshipVo();
                webshipVo.setWebshipId(webshipLoginVo.getWebshipId());
                webshipVo.setPassword(password);
                IWebshipPasswordService passwordService = new WebshipPasswordServiceImp();
                passwordService.changePassword(this.getAddInfoMap(), webshipVo);
                addActionMessage("The password changed successfull");
                user = null;
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
        }

        return "success";
    }

    protected boolean validateUser() {
        if (StringUtils.isBlank(user.getOldPassword())) {
            addFieldError("user.oldPassword", "The old password cannot leave blank");
        }
        if (StringUtils.isBlank(user.getNewPassword())) {
            addFieldError("user.newPassword", "The new password cannot leave blank");
        } else if (!PasswordUtils.isValid(user.getNewPassword())) {
            addFieldError("user.newPassword", "The new password is invalid");
        }
        if (StringUtils.isBlank(user.getConfirmPassword())) {
            addFieldError("user.confirmPassword", "The confirm password cannot leave blank");
        } else if (!user.getNewPassword().equals(user.getConfirmPassword())) {
            addFieldError("user.confirmPassword", "The confirm password does not match");
        }
        if (hasFieldErrors()) {
            setErrorCode(ErrorCode.FIELD_ERROR);
        }
        return !hasFieldErrors();
    }

    public WebshipPasswordModel getUser() {
        return user;
    }

    public void setUser(WebshipPasswordModel user) {
        this.user = user;
    }
}