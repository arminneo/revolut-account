package com.armin.revolut.endpoints;

import com.armin.revolut.Dependencies;
import com.armin.revolut.core.EndPoint;
import com.armin.revolut.endpoints.vo.*;
import com.armin.revolut.exceptions.InsufficientFundsException;
import com.armin.revolut.helpers.ModelValidator;
import com.armin.revolut.models.tables.pojos.Account;
import com.armin.revolut.models.tables.pojos.Records;
import com.armin.revolut.services.TransferQueueServer;
import com.armin.revolut.stores.AccountStore;
import com.armin.revolut.stores.RecordStore;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class AccountEndPoint extends EndPoint {

    private TransferQueueServer transferQueue;
    private AccountStore accountStore;
    private RecordStore recordStore;

    public AccountEndPoint(TransferQueueServer transferQueue) {
        this.transferQueue = transferQueue;
        this.accountStore = Dependencies.getInjector().getInstance(AccountStore.class);
        this.recordStore = Dependencies.getInjector().getInstance(RecordStore.class);
    }

    @Override
    public void route() {
        path("/account", () -> {
            get("/:id", this::getAccount, transform);
            post("/:id", APPLICATION_JSON, this::postCreate, transform);
            post("/transfer", APPLICATION_JSON, this::postTransfer, transform);
            post("/deposit", APPLICATION_JSON, this::postDeposit, transform);
        });
    }

    private AccountResponse postCreate(Request request, Response response) {
        AccountCreateRequest req = new AccountCreateRequest(request.params(":id"));
        ModelValidator.validate(req);
        int userId = Integer.parseInt(req.getUserId());

        response.type(APPLICATION_JSON);
        response.status(HttpStatus.CREATED_201);
        return AccountResponse.transform(accountStore.create(userId), new ArrayList<>());
    }

    public AccountResponse getAccount(Request request, Response response) throws Exception {
        AccountRequest req = new AccountRequest(request.params(":id"));
        ModelValidator.validate(req);

        Account account = accountStore.getAccountByCode(req.getCode());
        List<Records> records = recordStore.getRecords(account.getId());

        response.type(APPLICATION_JSON);
        return AccountResponse.transform(account, records);
    }

    public String postTransfer(Request request, Response response) throws Exception {
        TransferRequest transfer = transform.fromJson(request.body(), TransferRequest.class);
        ModelValidator.validate(transfer);

        Account sourceAccount = accountStore.getAccountByCode(transfer.getSource());
        accountStore.getAccountRecordByCode(transfer.getDestination());
        validateBalance(sourceAccount, transfer.getAmount());

        transferQueue.transfer(transfer.getSource(), transfer.getDestination(), transfer.getAmount());

        response.status(HttpStatus.ACCEPTED_202);
        response.type(APPLICATION_JSON);
        return String.format("We received your transfer request from %s to %s with the amount of %s",
                transfer.getSource(), transfer.getDestination(), transfer.getAmount());
    }

    public String postDeposit(Request request, Response response) throws Exception {
        DepositRequest deposit = transform.fromJson(request.body(), DepositRequest.class);
        ModelValidator.validate(deposit);

        accountStore.getAccountRecordByCode(deposit.getDestination());

        transferQueue.deposit(deposit.getDestination(), deposit.getAmount());

        response.status(HttpStatus.ACCEPTED_202);
        response.type(APPLICATION_JSON);
        return String.format("We received your deposit request to %s with the amount of %s",
                deposit.getDestination(), deposit.getAmount());
    }

    private void validateBalance(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(account.getCode(), amount);
        }
    }
}
