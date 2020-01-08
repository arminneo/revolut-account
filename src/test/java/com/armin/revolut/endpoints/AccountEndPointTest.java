package com.armin.revolut.endpoints;

import com.armin.revolut.BaseAppTest;
import com.armin.revolut.Dependencies;
import com.armin.revolut.endpoints.vo.DepositRequest;
import com.armin.revolut.endpoints.vo.TransferRequest;
import com.armin.revolut.helpers.GsonResponseTransformer;
import com.armin.revolut.helpers.JsonResponseTransformerImpl;
import com.armin.revolut.models.enums.RecordsReason;
import com.armin.revolut.models.tables.pojos.Records;
import com.armin.revolut.models.tables.records.AccountRecord;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import spark.Spark;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.armin.revolut.models.Tables.ACCOUNT;
import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountEndPointTest extends BaseAppTest {
    public static final String ACCOUNT_SOURCE_CODE = "111111";
    public static final String ACCOUNT_DEST_CODE = "222222";

    final String PREFIX = "/api/v1/account/";
    JsonResponseTransformerImpl json;


    @BeforeAll
    @Override
    protected void init() {
        super.init();
        json = (JsonResponseTransformerImpl) Dependencies.getInjector().getInstance(GsonResponseTransformer.class);
    }

    @AfterAll
    @Override
    protected void tearDown() {
        super.tearDown();
    }

    @Test
    void getAccount() {
        AccountRecord account = db.getDslContext().selectFrom(ACCOUNT).fetchAny();

        given().port(PORT)
                .log().all()
                .get(PREFIX + account.getCode())
                .then()
                .log().body()
                .assertThat().body("userId", equalTo(account.getUserId()))
                .assertThat().body("balance", equalTo(account.getBalance().intValue()));
    }

    @Test
    void postCreate(){
        Integer userId = 1;
        given().port(PORT)
                .log().all()
                .post(PREFIX + userId)
                .then()
                .log().body()
                .assertThat().statusCode(201)
                .assertThat().body("code", notNullValue())
                .assertThat().body("userId", equalTo(userId));
    }

    @Test
    void postTransfer() {
        AccountRecord accountSource = accountStore.getAccountRecordByCode(ACCOUNT_SOURCE_CODE);
        AccountRecord accountDest = accountStore.getAccountRecordByCode(ACCOUNT_DEST_CODE);
        TransferRequest request = new TransferRequest();
        request.setSource(accountSource.getCode());
        request.setDestination(accountDest.getCode());
        request.setAmount(BigDecimal.valueOf(5));

        given().port(PORT)
                .log().all()
                .body(request)
                .contentType(ContentType.JSON)
                .post(PREFIX + "transfer")
                .then()
                .log().body()
                .assertThat().statusCode(202);

        waitForTransferQueue();

        List<Records> destRecords = recordStore.getRecords(accountDest.getId());
        final boolean hasCredit = destRecords.stream()
                .anyMatch(r -> r.getCredit().equals(request.getAmount()) && r.getReason() == RecordsReason.transfer);
        Assertions.assertTrue(hasCredit);

        List<Records> sourceRecords = recordStore.getRecords(accountSource.getId());
        final boolean hasDebit = sourceRecords.stream()
                .anyMatch(r -> r.getDebit().equals(request.getAmount()) && r.getReason() == RecordsReason.transfer);
        Assertions.assertTrue(hasDebit);

    }

    @Test
    public void postTransferValidation() {
        given().port(PORT)
                .log().all()
                .contentType(ContentType.JSON)
                .post(PREFIX + "transfer")
                .then()
                .log().body()
                .assertThat().statusCode(400);

        TransferRequest request = new TransferRequest(ACCOUNT_SOURCE_CODE, ACCOUNT_DEST_CODE, BigDecimal.valueOf(99999));
        given().port(PORT)
                .log().all()
                .body(request)
                .contentType(ContentType.JSON)
                .post(PREFIX + "transfer")
                .then()
                .log().body()
                .assertThat().statusCode(402);

        request.setSource("wrong-code");
        given().port(PORT)
                .log().all()
                .body(request)
                .contentType(ContentType.JSON)
                .post(PREFIX + "transfer")
                .then()
                .log().body()
                .assertThat().statusCode(400);
    }


    @Test
    void postDeposit() {
        AccountRecord acccount = accountStore.getAccountRecordByCode(ACCOUNT_SOURCE_CODE);

        DepositRequest request = new DepositRequest(ACCOUNT_SOURCE_CODE, BigDecimal.valueOf(45326));
        given().port(PORT)
                .log().all()
                .body(request)
                .contentType(ContentType.JSON)
                .post(PREFIX + "deposit")
                .then()
                .log().body()
                .assertThat().statusCode(202);

        waitForTransferQueue();

        List<Records> records = recordStore.getRecords(acccount.getId());
        final boolean hasDeposit = records.stream()
                .anyMatch(r -> r.getCredit().equals(request.getAmount()) && r.getReason() == RecordsReason.deposit);
        Assertions.assertTrue(hasDeposit);
    }

    private void waitForTransferQueue() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            // ignored
        }
        await().atMost(5, TimeUnit.SECONDS)
                .until(() -> app.getTransferServer().isEmpty());
    }
}