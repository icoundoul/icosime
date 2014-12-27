package com.icoundoul.icosime.dao.exception;


public abstract class ApplicationException extends Exception {

    protected ApplicationException() {
    }

    protected ApplicationException(final String message) {
        super(message);
    }
}
