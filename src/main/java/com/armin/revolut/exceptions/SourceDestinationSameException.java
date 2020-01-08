package com.armin.revolut.exceptions;

public class SourceDestinationSameException extends ApiException {
    private static final long serialVersionUID = 932L;

    public String code;

    public SourceDestinationSameException(String code) {
        setCode(code);
    }

    private String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String getMessage() {
        return "Source and destination accounts cannot be same code [" + getCode() + "].";
    }
}
