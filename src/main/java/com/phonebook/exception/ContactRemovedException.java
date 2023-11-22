package com.phonebook.exception;

public class ContactRemovedException extends RuntimeException{
    public ContactRemovedException() {
    }

    public ContactRemovedException(String message) {
        super(message);
    }

    public ContactRemovedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactRemovedException(Throwable cause) {
        super(cause);
    }

    public ContactRemovedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
