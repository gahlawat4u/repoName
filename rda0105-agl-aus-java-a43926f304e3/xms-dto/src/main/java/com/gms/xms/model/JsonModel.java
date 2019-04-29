package com.gms.xms.model;

/**
 * Posted from JsonModel.java
 * <p>
 * Author Toantq Date Apr 4, 2015 Time: 1:13:17 AM
 */
public class JsonModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMsg;
    private String errorType;
    private String content;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

}
