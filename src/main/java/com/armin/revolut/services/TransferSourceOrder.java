package com.armin.revolut.services;

import java.math.BigDecimal;

public class TransferSourceOrder extends TransferDestinationOrder {
    protected String from;

    public TransferSourceOrder() {

    }

    public TransferSourceOrder(String from, String to, BigDecimal amount)
    {
        super(to, amount);
        this.setFrom(from);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }



}
