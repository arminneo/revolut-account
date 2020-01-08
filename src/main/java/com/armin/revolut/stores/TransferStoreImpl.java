package com.armin.revolut.stores;

import com.armin.revolut.Dependencies;
import com.armin.revolut.exceptions.AccountNotFoundException;
import com.armin.revolut.exceptions.InsufficientFundsException;
import com.armin.revolut.models.enums.RecordsReason;
import com.armin.revolut.models.tables.records.AccountRecord;
import com.armin.revolut.services.TransferSourceOrder;
import com.google.inject.Singleton;

import java.math.BigDecimal;

@Singleton
public class TransferStoreImpl extends BaseStore implements TransferStore {

    final RecordStore recordStore;
    final AccountStore accountStore;

    public TransferStoreImpl() {
        recordStore = Dependencies.getInjector().getInstance(RecordStore.class);
        accountStore = Dependencies.getInjector().getInstance(AccountStore.class);
    }

    @Override
    public void transfer(TransferSourceOrder order) throws AccountNotFoundException, InsufficientFundsException {
        transferAll(order.getFrom(), order.getTo(), order.getAmount(), Reason.TRANSFER);
    }

    @Override
    public void deposit(String codeTo, BigDecimal amount) throws AccountNotFoundException, InsufficientFundsException {
        transferAll("GATEWAY", codeTo, amount, Reason.DEPOSIT);
    }

    private synchronized void transferAll(String from, String to, BigDecimal amount, Reason reason)
            throws AccountNotFoundException, InsufficientFundsException {

        db.getDslContext().transaction(tx -> {

            AccountRecord accountDestination = accountStore.getAccountRecordByCode(to);
            RecordsReason reasonDestination = RecordsReason.deposit;

            if (reason == Reason.TRANSFER) {
                AccountRecord accountSource = accountStore.getAccountRecordByCode(from);

                if (accountSource.getBalance().compareTo(amount) < 0)
                    throw new InsufficientFundsException(from, amount);

                recordStore.insert(accountSource.getId(), amount, RecordsReason.transfer, true);
                reasonDestination = RecordsReason.transfer;

                BigDecimal sourceBalance = accountSource.getBalance();
                sourceBalance = sourceBalance.subtract(amount);
                accountSource.setBalance(sourceBalance);
                accountStore.update(accountSource);
            }

            recordStore.insert(accountDestination.getId(), amount, reasonDestination, false);
            BigDecimal destinationBalance = accountDestination.getBalance();
            destinationBalance = destinationBalance.add(amount);
            accountDestination.setBalance(destinationBalance);
            accountStore.update(accountDestination);
        });
    }

    private enum Reason {
        TRANSFER, DEPOSIT
    }
}
