package com.armin.revolut.services;

import java.math.BigDecimal;

public class TransferOrder {
    private BigDecimal amount;

    public TransferOrder() {

    }

    public TransferOrder(BigDecimal amount) {
        this.setAmount(amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public static class StopServe extends TransferSourceOrder {
        private StopServe() {
        }

        public static StopServe command() {
            return new StopServe();
        }
    }
}
