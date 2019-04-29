package com.gms.xms.common.exception;

/**
 * Posted from CustomException
 * <p>
 * Author DatTV Dec 29, 2015
 */
public class CustomException extends Exception {

    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable ex) {
        super(message, ex);
    }

    public CustomException(Throwable ex) {
        super(ex);
    }
}
