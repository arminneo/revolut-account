package com.armin.revolut.exceptions;

import java.math.BigDecimal;

public class InsufficientFundsException extends ApiException {
    private String code;
    private BigDecimal amount;

    public InsufficientFundsException(String code, BigDecimal amount) {
        this.setCode(code);
        this.setAmount(amount);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int getStatusCode() {
        return 402;
    }

    @Override
    public String getMessage() {
        return String.format("Account number [%s] does not have balance for [%s]", getCode(), getAmount());
    }
}
