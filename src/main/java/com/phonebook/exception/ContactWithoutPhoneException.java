package com.phonebook.exception;

public class ContactWithoutPhoneException extends RuntimeException{
    public ContactWithoutPhoneException() {
    }

    public ContactWithoutPhoneException(String message) {
        super(message);
    }

    public ContactWithoutPhoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactWithoutPhoneException(Throwable cause) {
        super(cause);
    }

    public ContactWithoutPhoneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
