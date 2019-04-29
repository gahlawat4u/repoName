package com.gms.xms.weblib.controller;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from JsonController.java
 * <p>
 * Author Toantq Date Apr 4, 2015 Time: 2:08:02 AM
 */
public class JsonBaseController extends ActionBaseController {
    protected Log log = LogFactory.getLog(JsonBaseController.class);
    private static final long serialVersionUID = 1L;

    protected void setErrorCode(String errorCode) {
        request.setAttribute(Attributes.ERROR_CODE, errorCode);
    }

    protected String getErrorCode() {
        return (String) request.getAttribute(Attributes.ERROR_CODE);
    }

    protected void setErrorMessage(String errorMessage) {
        request.setAttribute(Attributes.ERROR_MESSAGE, errorMessage);
    }

    protected String getErrorMessage() {
        return (String) request.getAttribute(Attributes.ERROR_CODE);
    }

    protected void setErrorType(String errorType) {
        request.setAttribute(Attributes.ERROR_TYPE, errorType);
    }

    public void handleError(Exception e, String defaultMessage) {
        // Set error code.
        this.setErrorCode(ErrorCode.ACTION_ERROR);
        // Set error message.
        String errorMessage = defaultMessage;
        errorMessage = e.getClass() == CustomException.class ? e.getMessage() : errorMessage;
        this.setErrorMessage(this.getLocalizationText(errorMessage));
        // Set action error.
        this.addActionError(errorMessage);
        // Log error.
        log.error(e.getMessage(), e);
    }
}