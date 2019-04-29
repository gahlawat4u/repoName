package com.gms.xms.common.exception;

/**
 * Posted from DaoException.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:35:59 PM
 */
public class DaoException extends Exception {

    private static final long serialVersionUID = 3423419481598875397L;

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable ex) {
        super(message, ex);
    }

    public DaoException(Throwable ex) {
        super(ex);
    }
}
