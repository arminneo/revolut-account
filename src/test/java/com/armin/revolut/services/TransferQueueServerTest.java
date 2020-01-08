package com.armin.revolut.services;

import com.armin.revolut.BaseAppTest;
import com.armin.revolut.models.enums.RecordsReason;
import com.armin.revolut.models.tables.pojos.Records;
import com.armin.revolut.models.tables.records.AccountRecord;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransferQueueServerTest extends BaseAppTest {
    private TransferQueueServer queueServer;

    public static final String ACCOUNT_SOURCE_CODE = "111111";
    public static final String ACCOUNT_DEST_CODE = "222222";

    @BeforeAll
    @Override
    protected void init() {
        super.init();
        queueServer = app.getTransferServer();

    }

    @AfterAll
    @Override
    protected void tearDown() {
        super.tearDown();
    }

    @Test
    void transfer() {
        BigDecimal amount = BigDecimal.valueOf(11);
        queueServer.transfer(ACCOUNT_SOURCE_CODE, ACCOUNT_DEST_CODE, amount);

        waitForTransferQueue();

        AccountRecord account = accountStore.getAccountRecordByCode(ACCOUNT_DEST_CODE);
        List<Records> records = recordStore.getRecords(account.getId());
        final boolean hasCreditDeposit = records.stream()
                .anyMatch(r -> r.getCredit().equals(amount) && r.getReason() == RecordsReason.transfer);
        Assertions.assertTrue(hasCreditDeposit);
    }

    @Test
    void deposit() {
        BigDecimal amount = BigDecimal.valueOf(58);
        queueServer.deposit(ACCOUNT_SOURCE_CODE, amount);

        waitForTransferQueue();

        AccountRecord account = accountStore.getAccountRecordByCode(ACCOUNT_SOURCE_CODE);
        List<Records> records = recordStore.getRecords(account.getId());
        final boolean hasCreditDeposit = records.stream()
                .anyMatch(r -> r.getCredit().equals(amount) && r.getReason() == RecordsReason.deposit);
        Assertions.assertTrue(hasCreditDeposit);
    }

    @Test
    void concurrencyEdgeCase() {
        AccountRecord account = accountStore.getAccountRecordByCode(ACCOUNT_SOURCE_CODE);
        BigDecimal amount_1 = account.getBalance().subtract(BigDecimal.valueOf(1));
        BigDecimal amount_2 = account.getBalance().subtract(BigDecimal.valueOf(2));
        BigDecimal amount_3 = account.getBalance().subtract(BigDecimal.valueOf(3));

        queueServer.transfer(ACCOUNT_SOURCE_CODE, ACCOUNT_DEST_CODE, amount_1);
        queueServer.transfer(ACCOUNT_SOURCE_CODE, ACCOUNT_DEST_CODE, amount_2);
        queueServer.transfer(ACCOUNT_SOURCE_CODE, ACCOUNT_DEST_CODE, amount_3);

        waitForTransferQueue();

        AccountRecord accountFinal = accountStore.getAccountRecordByCode(ACCOUNT_SOURCE_CODE);
        assertTrue(accountFinal.getBalance().compareTo(BigDecimal.ZERO) > 0);

        List<Records> records = recordStore.getRecords(account.getId());
        final boolean hasDebit = records.stream()
                .anyMatch(r -> r.getDebit().equals(amount_3) && r.getReason() == RecordsReason.transfer);

        Assertions.assertFalse(hasDebit);
    }

    private void waitForTransferQueue() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            // ignored
        }
        await().atMost(5, TimeUnit.SECONDS)
                .until(() -> queueServer.isEmpty());
    }
}