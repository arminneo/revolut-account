package com.armin.revolut.endpoints.vo;

import com.armin.revolut.core.BaseResponse;

import java.util.ArrayList;
import java.util.List;

public class BadRequestResponse extends BaseResponse {
    private List<String> errors;

    public BadRequestResponse(List<String> errors) {
        this.errors = errors;
    }

    public BadRequestResponse() {
        this.errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }
}
