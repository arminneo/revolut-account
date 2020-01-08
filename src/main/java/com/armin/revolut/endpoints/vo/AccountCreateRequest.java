package com.armin.revolut.endpoints.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountCreateRequest {
    @NotNull(message = "user id cannot be null")
    @Pattern(regexp = "\\d{1,20}", message = "user id is 5~20 digits")
    private String userId;

    public AccountCreateRequest() {
    }

    public AccountCreateRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
