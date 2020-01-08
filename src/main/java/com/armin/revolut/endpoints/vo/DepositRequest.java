package com.armin.revolut.endpoints.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class DepositRequest {
    @NotNull(message = "destination cannot be null")
    @Pattern(regexp = "\\d{5,20}", message = "destination account code is 5~20 digits")
    private String destination;

    @NotNull(message = "amount cannot be null")
    @Min(value = 1, message = "amount should more than 1€")
    @Max(value = 9999999, message = "amount should lass than 9.999.999€")
    private BigDecimal amount;

    public DepositRequest() {
    }

    public DepositRequest(String destination, BigDecimal amount) {
        this.destination = destination;
        this.amount = amount;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


}
