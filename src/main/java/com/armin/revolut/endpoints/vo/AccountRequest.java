package com.armin.revolut.endpoints.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountRequest {
    @NotNull(message = "account code cannot be null")
    @Pattern(regexp = "\\d{5,20}", message = "account code is 5~20 digits")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AccountRequest() {
    }

    public AccountRequest(String code) {
        this.code = code;
    }
}
