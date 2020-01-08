package com.armin.revolut.exceptions;

public class AccountNotFoundException extends ApiException {
    private static final long serialVersionUID = 543235L;

    public String code;

    public AccountNotFoundException(String code) {
        setCode(code);
    }

    private String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

    @Override
    public String getMessage() {
        return "Account with code [" + getCode() + "] not found.";
    }
}
