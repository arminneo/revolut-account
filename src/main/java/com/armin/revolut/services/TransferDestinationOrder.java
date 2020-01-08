package com.armin.revolut.services;

import java.math.BigDecimal;

public class TransferDestinationOrder extends TransferOrder {
    private String to;

    public TransferDestinationOrder() {
    }

    public TransferDestinationOrder(String to, BigDecimal amount) {
        super(amount);
        this.setTo(to);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
