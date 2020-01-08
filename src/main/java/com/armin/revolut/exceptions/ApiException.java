package com.armin.revolut.exceptions;

import com.google.gson.annotations.Expose;

public class ApiException extends RuntimeException {
    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getStatusCode(){
        return 400;
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return null;
    }
}
