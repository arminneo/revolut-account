package com.armin.revolut.stores;

import com.armin.revolut.exceptions.AccountNotFoundException;
import com.armin.revolut.models.tables.pojos.Account;
import com.armin.revolut.models.tables.records.AccountRecord;

public interface AccountStore {
    Account getAccountByCode(String code) throws AccountNotFoundException;

    AccountRecord getAccountRecordByCode(String code) throws AccountNotFoundException;

    void update(AccountRecord accountRecord);
}
