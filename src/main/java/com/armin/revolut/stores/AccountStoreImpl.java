package com.armin.revolut.stores;

import com.armin.revolut.exceptions.AccountNotFoundException;
import com.armin.revolut.models.tables.pojos.Account;
import com.armin.revolut.models.tables.records.AccountRecord;
import com.google.inject.Singleton;

import java.time.LocalDateTime;
import java.util.Random;

import static com.armin.revolut.models.Tables.ACCOUNT;

@Singleton
public class AccountStoreImpl extends BaseStore implements AccountStore {

    public AccountStoreImpl() {

    }

    public Account create(int userId) {
        String code = makeAccountCode();
        AccountRecord newAccount = db.getDslContext()
                .insertInto(ACCOUNT, ACCOUNT.USER_ID, ACCOUNT.CODE)
                .values(userId, code)
                .returning()
                .fetchOne();

        return newAccount.into(Account.class);
    }

    @Override
    public Account getAccountByCode(String code) throws AccountNotFoundException {
        final Account[] account = {null};
        db.getDslContext().transaction(tx -> {
            AccountRecord record = getAccountRecordByCode(code);
            account[0] = record.into(Account.class);
        });

        return account[0];
    }

    @Override
    public AccountRecord getAccountRecordByCode(String code) throws AccountNotFoundException {
        AccountRecord account = db.getDslContext().selectFrom(ACCOUNT)
                .where(ACCOUNT.CODE.eq(code))
                .fetchOne();

        if (null == account) throw new AccountNotFoundException(code);

        return account;
    }

    public void update(AccountRecord accountRecord) {
        accountRecord.setUpdatedOn(LocalDateTime.now());
        db.getDslContext().executeUpdate(accountRecord);
    }

    public String makeAccountCode() {
        /**
         * Just a simple code generator
         * // TODO: Add a collision detection mechanism
         */
        Random rand = new java.util.Random();
        long x = (long) (rand.nextDouble() * 1000_000_000_000L);

        return String.format("%014d", x);
    }
}
