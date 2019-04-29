package com.gms.xms.txndb.vo;

/**
 * Posted from AccessorialModel
 * <p>
 * Author HungNT Date Apr 7, 2015
 */
public class UserLoginInfoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long webshipId;

    private Long userId;

    private String userName;

    private Boolean allowExportAddress;

    private String webshipPassword;

    private Boolean webshipAuthenticated;

    public Long getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(Long webshipId) {
        this.webshipId = webshipId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getAllowExportAddress() {
        return allowExportAddress;
    }

    public void setAllowExportAddress(Boolean allowExportAddress) {
        this.allowExportAddress = allowExportAddress;
    }

    public String getWebshipPassword() {
        return webshipPassword;
    }

    public void setWebshipPassword(String webshipPassword) {
        this.webshipPassword = webshipPassword;
    }

    public Boolean getWebshipAuthenticated() {
        return webshipAuthenticated;
    }

    public void setWebshipAuthenticated(Boolean webshipAuthenticated) {
        this.webshipAuthenticated = webshipAuthenticated;
    }
}