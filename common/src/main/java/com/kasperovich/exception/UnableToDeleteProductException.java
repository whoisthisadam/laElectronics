package com.kasperovich.exception;

public class UnableToDeleteProductException extends Exception{

    public UnableToDeleteProductException() {
    }

    public UnableToDeleteProductException(String message) {
        super(message);
    }

    public UnableToDeleteProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToDeleteProductException(Throwable cause) {
        super(cause);
    }

    public UnableToDeleteProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
