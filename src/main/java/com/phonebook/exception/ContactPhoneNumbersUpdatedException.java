package com.phonebook.exception;

public class ContactPhoneNumbersUpdatedException extends RuntimeException{
    public ContactPhoneNumbersUpdatedException() {
    }

    public ContactPhoneNumbersUpdatedException(String message) {
        super(message);
    }

    public ContactPhoneNumbersUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactPhoneNumbersUpdatedException(Throwable cause) {
        super(cause);
    }

    public ContactPhoneNumbersUpdatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
