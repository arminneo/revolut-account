package com.armin.revolut.stores;

import com.armin.revolut.models.enums.RecordsReason;
import com.armin.revolut.models.tables.pojos.Records;
import com.armin.revolut.models.tables.records.RecordsRecord;
import com.google.inject.Singleton;
import org.jooq.Result;

import java.math.BigDecimal;
import java.util.List;

import static com.armin.revolut.models.Tables.RECORDS;

@Singleton
public class RecordStoreImpl extends BaseStore implements RecordStore {

    @Override
    public Records insert(long accountId, BigDecimal amount, RecordsReason reason, boolean isDebit) {
        BigDecimal credit = isDebit ? BigDecimal.ZERO : amount;
        BigDecimal debit = isDebit ? amount : BigDecimal.ZERO;

        Records records = db.getDslContext()
                .insertInto(RECORDS, RECORDS.ACCOUNT_ID, RECORDS.REASON, RECORDS.CREDIT, RECORDS.DEBIT)
                .values(accountId, reason, credit, debit)
                .returning()
                .fetchOne()
                .into(Records.class);

        return records;
    }

    @Override
    public List<Records> getRecords(long accountId) {
        return db.getDslContext().selectFrom(RECORDS)
                .where(RECORDS.ACCOUNT_ID.eq(accountId))
                .fetch()
                .into(Records.class);
    }
}
