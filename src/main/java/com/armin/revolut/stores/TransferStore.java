package com.armin.revolut.stores;

import com.armin.revolut.exceptions.AccountNotFoundException;
import com.armin.revolut.exceptions.InsufficientFundsException;
import com.armin.revolut.services.TransferSourceOrder;

import java.math.BigDecimal;

public interface TransferStore {

    void transfer(TransferSourceOrder order) throws AccountNotFoundException, InsufficientFundsException;

    void deposit(String codeTo, BigDecimal amount) throws AccountNotFoundException, InsufficientFundsException;
}
