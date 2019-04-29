package com.gms.xms.weblib.controller.webship.login;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.CryptUtils;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.common.utils.PasswordUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.WebshipModel;
import com.gms.xms.model.webship.login.WebshipResetModel;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.dao.webship.WebshipDao;
import com.gms.xms.persistence.service.webship.IWebshipService;
import com.gms.xms.persistence.service.webship.WebshipServiceImp;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import com.gms.xms.txndb.vo.webship.login.WebshipResetFilter;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from WebshipResetController
 * <p>
 * Author DatTV Date Jul 9, 2015 10:10:40 AM
 */
public class WebshipResetController extends JsonBaseController {
    private static final long serialVersionUID = -3491572415649247950L;

    private WebshipResetModel user;
    private WebshipResetFilter filter;
    private WebshipModel webshipModel;
    private String msgSuccess;
    private Boolean validStatus;
    private String codeResetPassword;
    private String changePasswordStatus;
    private String passwordConfim;

    public String reset() {
        this.setPageTitle("Reset Password");
        try {
            if (this.getUser() != null) {
                // Validate input
                if (!validateUser()) {
                    return "success";
                }
                if (!checkInfoUser()) {
                    return "success";
                }
                String resetPasswordCode = PasswordUtils.generate(10);
                String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/change_password.ix?codeResetPassword=" + resetPasswordCode;
                String content = "Link confirm change Password: <a href=\"{resetURL}\">[Click here]</a> <br/> Or Enter Code: {codeReset}";
                Map<String, String> replaceMap = new HashMap<>();
                replaceMap.put("{resetURL}", url);
                replaceMap.put("{codeReset}", resetPasswordCode);
                content = AppUtils.replaceStringByMap(replaceMap, content);

                // Send URL to confirm reset code
                sendEmailResetPw(user.getEmail(), this.getLocalizationText("Request change password"), content);
                // Check user from the database and send email with new password
                this.setMsgSuccess("Request reset password had send to email " + user.getEmail());
                this.getWebshipModel().setResetPasswordCode(resetPasswordCode);
                updateWebshipInfo();
                return "redirect";
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "success";
        }

        return "success";
    }

    public String changePasswordWebship() {
        this.setPageTitle(this.getLocalizationText("Change Password"));
        try {
            if (StringUtils.isBlank(this.getCodeResetPassword())) {
                if (this.getValidStatus() != null && this.getValidStatus() && this.getWebshipModel() != null) {
                    if (!validatePassword()) {
                        return "success";
                    }
                    this.getWebshipModel().setPassword(CryptUtils.Encrypt(this.getWebshipModel().getPassword(), AppConstants.APP_SETTINGS.getEncryptionKey()));
                    this.setChangePasswordStatus(this.getLocalizationText("Change password success! You can login."));
                    this.getWebshipModel().setResetPasswordCode(null);
                    updateWebshipInfo();
                }
            } else {
                if (this.verifyCodeResetPassword()) {
                    this.setValidStatus(true);
                    WebshipDao webshipDao = new WebshipDao();
                    WebshipVo webshipVo = webshipDao.selectByResetCode(this.getCodeResetPassword());
                    WebshipModel webshipModel = ModelUtils.createModelFromVo(webshipVo, WebshipModel.class);
                    this.setWebshipModel(webshipModel);
                }
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "success";
        }
        return "success";
    }

    protected Boolean validatePassword() {
        if (StringUtils.isBlank(this.getWebshipModel().getPassword())) {
            addFieldError("webshipModel.password", "The password cannot leave blank");
        } else if (!PasswordUtils.isValid(this.getWebshipModel().getPassword())) {
            addFieldError("webshipModel.password", "The password is invalid");
        }
        if (StringUtils.isBlank(getPasswordConfim())) {
            addFieldError("passwordConfim", "The confirm password cannot leave blank");
        } else if (!this.getWebshipModel().getPassword().equals(this.getPasswordConfim())) {
            addFieldError("passwordConfim", "The confirm password does not match");
        }
        if (hasFieldErrors()) {
            setErrorCode(ErrorCode.FIELD_ERROR);
        }
        return !hasFieldErrors();
    }

    protected void updateWebshipInfo() throws Exception {
        IWebshipService service = new WebshipServiceImp();
        service.updateWebship(this.getAddInfoMap(), ModelUtils.createVoFromModel(webshipModel, WebshipVo.class));
    }

    protected void sendEmailResetPw(String toEmail, String subject, String content) throws CustomException {
        // Get email settings
        String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
        String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
        String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
        String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
        int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
        String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);

        try {
            AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, toEmail, "", null, subject, content, null);
        } catch (Exception e) {
            throw new CustomException("Cannot send email. Please try again later.");
        }
    }

    protected boolean verifyCodeResetPassword() throws Exception {
        if (!StringUtils.isBlank(this.getCodeResetPassword())) {
            WebshipDao webshipDao = new WebshipDao();
            WebshipVo webshipVo = webshipDao.selectByResetCode(this.getCodeResetPassword());
            if (webshipVo == null) {
                this.addActionError(this.getLocalizationText("Reset code is invalid or you haven't requested yet. Please try again."));
            }
        } else {
            if (request.getMethod().equalsIgnoreCase("post")) {
                this.addActionError("Code cannot be empty.");
            }
        }
        return !hasActionErrors();
    }

    protected boolean validateUser() {
        if (StringUtils.isBlank(user.getCustomerCode())) {
            addFieldError("user.customerCode", "Customer code cannot leave blank");
        }
        if (StringUtils.isBlank(user.getUserName())) {
            addFieldError("user.userName", "Customer Name cannot leave blank");
        }
        if (StringUtils.isBlank(user.getEmail())) {
            addFieldError("user.email", "Email cannot leave blank");
        } else if (!EmailUtils.isValidEmail(user.getEmail())) {
            addFieldError("user.email", "Invalid email");
        }
        return !hasFieldErrors();
    }

    protected boolean checkInfoUser() throws Exception {
        buildWebshipResetFilter();
        IWebshipService service = new WebshipServiceImp();
        if (service.checkInfoResetPassword(filter) == null) {
            FranchiseDao franchiseDao = new FranchiseDao();
            FranchiseInfoVo franchiseVo = franchiseDao.getFranchiseInfoByCode("100");
            String errMsg = this.getLocalizationText("Please contact your freight consultant {contactName} on Phone {phone} to have your password reset.");
            Map<String, String> replaceMap = new HashMap<>();
            replaceMap.put("{contactName}", franchiseVo.getContactName());
            replaceMap.put("{phone}", franchiseVo.getPhone());
            errMsg = AppUtils.replaceStringByMap(replaceMap, errMsg);
            addActionError(errMsg);
        } else {
            WebshipVo webshipVo = new WebshipVo();
            webshipVo = service.checkInfoResetPassword(filter);
            this.setWebshipModel(ModelUtils.createModelFromVo(webshipVo, WebshipModel.class));
        }

        return !hasActionErrors();
    }

    protected void buildWebshipResetFilter() throws Exception {
        WebshipResetFilter filterN = new WebshipResetFilter();
        filterN.setCustomerCode(user.getCustomerCode());
        filterN.setEmail(user.getEmail());
        filterN.setUserName(user.getUserName());
        this.setFilter(filterN);
    }

    public WebshipResetFilter getFilter() {
        return filter;
    }

    public void setFilter(WebshipResetFilter filter) {
        this.filter = filter;
    }

    public WebshipResetModel getUser() {
        return user;
    }

    public void setUser(WebshipResetModel user) {
        this.user = user;
    }

    public String getMsgSuccess() {
        return msgSuccess;
    }

    public void setMsgSuccess(String msgSuccess) {
        this.msgSuccess = msgSuccess;
    }

    public String getCodeResetPassword() {
        return codeResetPassword;
    }

    public void setCodeResetPassword(String codeResetPassword) {
        this.codeResetPassword = codeResetPassword;
    }

    public WebshipModel getWebshipModel() {
        return webshipModel;
    }

    public void setWebshipModel(WebshipModel webshipModel) {
        this.webshipModel = webshipModel;
    }

    public Boolean getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(Boolean validStatus) {
        this.validStatus = validStatus;
    }

    public String getChangePasswordStatus() {
        return changePasswordStatus;
    }

    public void setChangePasswordStatus(String changePasswordStatus) {
        this.changePasswordStatus = changePasswordStatus;
    }

    public String getPasswordConfim() {
        return passwordConfim;
    }

    public void setPasswordConfim(String passwordConfim) {
        this.passwordConfim = passwordConfim;
    }

}