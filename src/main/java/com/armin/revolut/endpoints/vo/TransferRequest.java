package com.armin.revolut.endpoints.vo;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class TransferRequest extends DepositRequest {

    @NotNull(message = "source cannot be null")
    @Pattern(regexp = "\\d{5,20}", message = "source account code is 5~20 digits")
    private String source;



    public TransferRequest() {
    }

    public TransferRequest(String source, String destination, BigDecimal amount) {
        this.setSource(source);
        this.setDestination(destination);
        this.setAmount(amount);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


}
