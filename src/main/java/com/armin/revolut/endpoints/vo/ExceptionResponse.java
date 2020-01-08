package com.armin.revolut.endpoints.vo;

import com.armin.revolut.core.BaseResponse;
import com.armin.revolut.exceptions.ApiException;

public class ExceptionResponse extends BaseResponse {
    private String type;
    private String message;

    public ExceptionResponse(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public ExceptionResponse() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ExceptionResponse from(ApiException ex) {
        return new ExceptionResponse(ex.getClass().getName(), ex.getMessage());

    }
}
