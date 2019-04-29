package com.gms.xms.weblib.controller.webship.login;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.ErrorType;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.CryptUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.login.WebshipLoginModel;
import com.gms.xms.persistence.dao.admin.WebshipLogDao;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.webship.login.ILoginService;
import com.gms.xms.persistence.service.webship.login.LoginServiceImp;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.FranchiseVo;
import com.gms.xms.txndb.vo.admin.WebshipLogVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Posted from LoginController
 * <p>
 * Author DatTV Date Jul 6, 2015 2:53:54 PM
 */
public class LoginController extends JsonBaseController {
    private static final long serialVersionUID = -3491572415649247950L;
    private WebshipLoginModel user;

    public String login() {
        String reqType = request.getParameter("reqType");
        String errorParam = request.getParameter("errorId");
        if ("json".equalsIgnoreCase(reqType) && "10".equalsIgnoreCase(errorParam)) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText("Session timeout, please login to webship!"));
            this.setErrorType(ErrorType.SESSION_TIMEOUT);
            return "json_error";
        }
        // Validate user information
        if (user == null || !validateUser()) {
            return "input";
        }
        // Get customer code if it's entered
        Long customerCode = null;
        try {
            customerCode = Long.parseLong(user.getName());
        } catch (Exception ex) {
            customerCode = null;
        }
        // Check login
        try {
            WebshipLoginVo webshipVo = ModelUtils.createVoFromModel(user, WebshipLoginVo.class);
            webshipVo.setCustomerCode(customerCode);
            webshipVo.setPassword(CryptUtils.Encrypt(user.getPassword(), AppConstants.APP_SETTINGS.getEncryptionKey()));
            ILoginService loginService = new LoginServiceImp();
            WebshipLoginVo loggedWebshipVo = loginService.checkLogin(webshipVo);
             
            FranchiseServiceImp franchiseServiceImp = new FranchiseServiceImp();
            CustomerServiceImp customerServiceImp = new CustomerServiceImp();
      
            String substr = loggedWebshipVo.getCustomerCode().toString().substring(loggedWebshipVo.getCustomerCode().toString().length() - 3);
           if(substr.equalsIgnoreCase("001")){
        	   try{
        	 FranchiseVo franchiseVo =  franchiseServiceImp.selectByFranchiseCode(loggedWebshipVo.getCustomerCode().toString());
        	 byte[] arryaFileFranchise = franchiseVo.getProfileImage();
        	 if(arryaFileFranchise.length > 0){
       	  String pathname = WebUtils.getWebLogoPath(request);
       	  File file = new File(pathname+loggedWebshipVo.getCustomerCode().toString().substring(0,3)+".png");
       	  writeBytesToFile(file, arryaFileFranchise);}
        	   loggedWebshipVo.setProfileCustomerCode(loggedWebshipVo.getCustomerCode().toString().substring(0,3));}
        	   catch(Exception e){
        		   log.error(e);  
        	   }
           }else{
        	   try{
        	 CustomerVo  customerVo =   customerServiceImp.getCustomerProfileImage(loggedWebshipVo.getCustomerCode().toString());
        	  byte[] arryaFile = customerVo.getProfileImage();
        	  if(arryaFile.length > 0){
        	  String pathname = WebUtils.getWebLogoPath(request);
        	  File file = new File(pathname+loggedWebshipVo.getCustomerCode().toString()+".png");
        	  writeBytesToFile(file, arryaFile);}
        	   loggedWebshipVo.setProfileCustomerCode(loggedWebshipVo.getCustomerCode().toString());}
        	   catch(Exception e){
        		   log.error(e);  
        	   }
           }
            if (loggedWebshipVo != null) {
                // Log to Webship Log (Login).
                Map<String, String> context = this.getAddInfoMap();
                WebshipLogVo webshipLogVo = new WebshipLogVo();
                webshipLogVo.setActionDate(new Date());
                webshipLogVo.setActionTable("Webship");
                webshipLogVo.setActionType("Login");
                webshipLogVo.setChangesMode("");
                webshipLogVo.setFilter("webshipid = '" + context.get(Attributes.ADD_INFO_EXT_USER_ID) + "'");
                webshipLogVo.setIpAddress(context.get(Attributes.ADD_INFO_EXT_IP) == null ? "" : context.get(Attributes.ADD_INFO_EXT_IP));
                webshipLogVo.setUserCode(loggedWebshipVo.getCustomerCode());
                webshipLogVo.setUserId(loggedWebshipVo.getWebshipId());
                webshipLogVo.setUserName(loggedWebshipVo.getName());
                webshipLogVo.setUserType(0);
                WebshipLogDao webshipLogDao = new WebshipLogDao();
                webshipLogDao.insert(context, webshipLogVo);
                // Check inactive account
                if (loggedWebshipVo.getInactive()) {
                    addActionError("Account is currently inactive, please contact your AGL Sales Consultant if you would like to re-activate your account.");
                    return "input";
                } else {
                    this.setSession(Attributes.SESS_XMS_WEBSHIP_LOGIN_INFO, loggedWebshipVo);
                    // Check require password
                    if (loggedWebshipVo.getIsRequireChangePassword()!= null && loggedWebshipVo.getIsRequireChangePassword()) {
                        return "changepass";
                    }
                }
            } else {
                addActionError("Invalid Username or Password");
                return "input";
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
            return "input";
        }
        return "success";
    }

    public static void writeBytesToFile(File theFile, byte[] bytes) throws IOException {
        BufferedOutputStream bos = null;
        
      try {
        FileOutputStream fos = new FileOutputStream(theFile);
        bos = new BufferedOutputStream(fos); 
        bos.write(bytes);
      }finally {
        if(bos != null) {
          try  {
            //flush and close the BufferedOutputStream
            bos.flush();
            bos.close();
          } catch(Exception e){}
        }
      }
      }
    
    public void loginAs() {
        // If it's the first request then go to the input page
        if (user == null) {
            return;
        }
        // Check permission to login as
        WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
        if (!canLoginAs(webshipLoginVo)) {
            setErrorMessage("You don't have permission to do this action.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return;
        }
        // Get login as webship login vo by webshipid
        ILoginService loginService = new LoginServiceImp();
        try {
            WebshipLoginVo loginAsVo = loginService.getWebshipLoginById(Long.valueOf(user.getWebshipId()));
            if (webshipLoginVo.getParentCustomerCode() == null) {
                loginAsVo.setParentWebshipId(webshipLoginVo.getWebshipId());
                loginAsVo.setParentCustomerCode(webshipLoginVo.getCustomerCode());
            } else {
                loginAsVo.setParentWebshipId(webshipLoginVo.getParentWebshipId());
                loginAsVo.setParentCustomerCode(webshipLoginVo.getParentCustomerCode());
            }
            this.setSession(Attributes.SESS_XMS_WEBSHIP_LOGIN_INFO, loginAsVo);
        } catch (NumberFormatException e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e);
        } catch (DaoException e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
    }

    public String logout() {
        WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
        // No need remove session if the customer haven't been logged
        if (webshipLoginVo == null) {
            return "success";
        }
        // Check login as
        ILoginService loginService = new LoginServiceImp();
        try {
            if (webshipLoginVo.getParentCustomerCode() == null) {
                this.setSession(Attributes.SESS_XMS_WEBSHIP_LOGIN_INFO, null);
                return "success";
            } else {
                WebshipLoginVo loginAsVo = loginService.getWebshipLoginById(webshipLoginVo.getParentWebshipId());
                this.setSession(Attributes.SESS_XMS_WEBSHIP_LOGIN_INFO, loginAsVo);
                return "home";
            }
        } catch (NumberFormatException e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e);
        } catch (DaoException e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "error";
    }

    protected boolean validateUser() {
        if (StringUtils.isBlank(user.getName())) {
            addFieldError("user.name", "Username cannot leave blank");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            addFieldError("user.password", "Password cannot leave blank");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private boolean canLoginAs(WebshipLoginVo webshipLoginVo) {
        if (webshipLoginVo == null)
            return false;
        Long customerCode = webshipLoginVo.getParentCustomerCode() == null ? webshipLoginVo.getCustomerCode() : webshipLoginVo.getParentCustomerCode();
        return "00001".equalsIgnoreCase(StringUtils.right(String.valueOf(customerCode), 5));
    }

    public WebshipLoginModel getUser() {
        return user;
    }

    public void setUser(WebshipLoginModel user) {
        this.user = user;
    }
}