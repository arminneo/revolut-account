package com.armin.revolut.exceptions;

import java.util.Arrays;
import java.util.List;

public class BadRequestException extends ApiException {
    private List<String> errors;

    public BadRequestException(List<String> errors) {
        this.errors = errors;
    }

    public BadRequestException(String... errors) {
        this.errors = Arrays.asList(errors);
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

    @Override
    public String getMessage() {
        return errors.stream()
                .reduce("", (str, s) -> str + s + "\n");
    }
}
