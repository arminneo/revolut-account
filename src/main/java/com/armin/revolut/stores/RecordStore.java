package com.armin.revolut.stores;

import com.armin.revolut.models.enums.RecordsReason;
import com.armin.revolut.models.tables.pojos.Records;

import java.math.BigDecimal;
import java.util.List;

public interface RecordStore {
    Records insert(long accountId, BigDecimal amount, RecordsReason reason, boolean isDebit);

    List<Records> getRecords(long accountId);
}
