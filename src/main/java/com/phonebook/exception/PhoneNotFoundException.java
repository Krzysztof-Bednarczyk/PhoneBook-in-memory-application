package com.phonebook.exception;

public class PhoneNotFoundException extends RuntimeException{

    public PhoneNotFoundException() {
    }

    public PhoneNotFoundException(String message) {
        super(message);
    }

    public PhoneNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneNotFoundException(Throwable cause) {
        super(cause);
    }

    public PhoneNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
