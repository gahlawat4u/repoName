package com.gms.xms.weblib.controller;

import com.gms.xms.common.constants.ErrorCode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import java.util.UUID;

/**
 * Posted from JsonController.java
 * <p>
 * Author Toantq Date Apr 4, 2015 Time: 2:08:02 AM
 */
public class ErrorHandlerController extends JsonBaseController {
    private static final long serialVersionUID = 1L;

    /**
     * Finds exception object on the value stack
     *
     * @return the exception object on the value stack
     */
    private Object findException() {
        ActionContext ac = ActionContext.getContext();
        ValueStack vs = ac.getValueStack();
        Object exception = vs.findValue("exception");
        return exception;
    }

    public String errorHandler() {
        Exception exception = (Exception) findException();
        this.setErrorCode(ErrorCode.ACTION_ERROR);
        String errorId = String.valueOf(UUID.randomUUID());
        String reqType = request.getParameter("reqType");
        this.setErrorMessage("System internal error. ErrorId:" + errorId + " ErrorMessage :" + exception.getMessage());
        log.error("Fail to process action.|" + "reqType:" + request.getParameter("reqType") + ".|errorId:" + errorId, exception);
        if ("json".equalsIgnoreCase(reqType)) {
            return "error_handler_data";
        } else {
            return "error_handler";
        }
    }

    public String accessDenied() {
        String reqType = request.getParameter("reqType");
        String errorId = request.getParameter("errorId");
        if ("json".equalsIgnoreCase(reqType)) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            if ("1".equalsIgnoreCase(errorId)) {
                this.setErrorMessage(getLocalizationText("Please login to admin"));
            } else {
                this.setErrorMessage(getLocalizationText("You don't have permission to access this function, please contact Administrator."));
            }
            return "accessdenied_data";
        } else {
            return "accessdenied";
        }
    }
}