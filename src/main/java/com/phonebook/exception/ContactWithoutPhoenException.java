package com.phonebook.exception;

public class ContactWithoutPhoenException extends RuntimeException{
    public ContactWithoutPhoenException() {
    }

    public ContactWithoutPhoenException(String message) {
        super(message);
    }

    public ContactWithoutPhoenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactWithoutPhoenException(Throwable cause) {
        super(cause);
    }

    public ContactWithoutPhoenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
