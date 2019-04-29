package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from WebshipModel
 * <p>
 * Author DatTV Date Apr 2, 2015
 */
public class WebshipModel extends BaseModel {

    private static final long serialVersionUID = -5804663275886305570L;

    private String webshipId;

    private String customerCode;

    private String name;

    private String password;

    private String createDate;

    private String language;

    private String allowExportAddressBook;

    private String isRequireChangePassword;

    private String resetPasswordStatus;

    private String resetPasswordCode;
    
    private String profileCustomerCode;

    public String getResetPasswordStatus() {
        return resetPasswordStatus;
    }

    public void setResetPasswordStatus(String resetPasswordStatus) {
        this.resetPasswordStatus = resetPasswordStatus;
    }

    public String getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(String webshipId) {
        this.webshipId = webshipId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAllowExportAddressBook() {
        return allowExportAddressBook;
    }

    public void setAllowExportAddressBook(String allowExportAddressBook) {
        this.allowExportAddressBook = allowExportAddressBook;
    }

    public String getIsRequireChangePassword() {
        return isRequireChangePassword;
    }

    public void setIsRequireChangePassword(String isRequireChangePassword) {
        this.isRequireChangePassword = isRequireChangePassword;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }

    public String getProfileCustomerCode() {
		return profileCustomerCode;
	}

	public void setProfileCustomerCode(String profileCustomerCode) {
		this.profileCustomerCode = profileCustomerCode;
	}

    @Override
    public String toString() {
        return "WebshipModel [webshipId=" + webshipId + ", customerCode=" + customerCode + ", name=" + name + ", password=" + password + ", createDate=" + createDate + ", language=" + language + ", allowExportAddressBook=" + allowExportAddressBook + ", isRequireChangePassword=" + isRequireChangePassword + ", resetPasswordStatus=" + resetPasswordStatus + ", resetPasswordCode=" + resetPasswordCode + "]";
    }
}