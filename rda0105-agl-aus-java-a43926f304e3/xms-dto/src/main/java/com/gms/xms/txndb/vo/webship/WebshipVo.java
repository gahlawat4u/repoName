package com.gms.xms.txndb.vo.webship;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.common.json.JsonString2DateTimeDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from WebshipVo
 * <p>
 * Author DatTV Date Mar 26, 2015
 */
public class WebshipVo extends BaseVo {
    private static final long serialVersionUID = -667079916754298908L;

    private Long webshipId;

    private Long customerCode;

    private String name;

    private String password;

    private Date createDate;

    private Integer language;

    private Boolean allowExportAddressBook;

    private Boolean isRequireChangePassword;

    private Integer resetPasswordStatus;

    private String resetPasswordCode;

    private String profileCustomerCode;
    
    public Integer getResetPasswordStatus() {
        return resetPasswordStatus;
    }

    public void setResetPasswordStatus(Integer resetPasswordStatus) {
        this.resetPasswordStatus = resetPasswordStatus;
    }

    public Long getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(Long webshipId) {
        this.webshipId = webshipId;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
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

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Boolean getAllowExportAddressBook() {
        return allowExportAddressBook;
    }

    public void setAllowExportAddressBook(Boolean allowExportAddressBook) {
        this.allowExportAddressBook = allowExportAddressBook;
    }

    public Boolean getIsRequireChangePassword() {
        return isRequireChangePassword;
    }

    public void setIsRequireChangePassword(Boolean isRequireChangePassword) {
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
        return "WebshipVo [webshipId=" + webshipId + ", customerCode=" + customerCode + ", name=" + name + ", password=" + password + ", createDate=" + createDate + ", language=" + language + ", allowExportAddressBook=" + allowExportAddressBook + ", isRequireChangePassword=" + isRequireChangePassword + ", resetPasswordStatus=" + resetPasswordStatus + ", resetPasswordCode=" + resetPasswordCode + "]";
    }
}