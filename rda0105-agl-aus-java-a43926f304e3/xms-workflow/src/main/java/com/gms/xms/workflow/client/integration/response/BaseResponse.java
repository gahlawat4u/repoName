package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.workflow.client.integration.IResponse;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseResponse implements IResponse {
    private String errorCode;
    private String errorMessage;
    private String errorType;
    private String resultString;
    private Map<String, List<String>> fieldErrorMap = new HashMap<String, List<String>>();

    public final String getErrorCode() {
        return errorCode;
    }

    public final void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public final Map<String, List<String>> getFieldErrorMap() {
        return fieldErrorMap;
    }

    public final void setFieldErrorMap(Map<String, List<String>> fieldErrorMap) {
        this.fieldErrorMap = fieldErrorMap;
    }

    public final void addFieldError(String field, String message) {
        if (StringUtils.isBlank(field) || StringUtils.isBlank(message)) {
            return;
        }
        if (fieldErrorMap == null) {
            fieldErrorMap = new HashMap<String, List<String>>();
        }

        if (fieldErrorMap.get(field) == null) {
            fieldErrorMap.put(field, new ArrayList<String>());
        }

        fieldErrorMap.get(field).add(message);
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
}