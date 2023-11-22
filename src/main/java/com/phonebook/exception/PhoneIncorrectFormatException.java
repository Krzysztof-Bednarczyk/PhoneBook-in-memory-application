package com.phonebook.exception;

public class PhoneIncorrectFormatException extends RuntimeException{
    public PhoneIncorrectFormatException() {
    }

    public PhoneIncorrectFormatException(String message) {
        super(message);
    }

    public PhoneIncorrectFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneIncorrectFormatException(Throwable cause) {
        super(cause);
    }

    public PhoneIncorrectFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
