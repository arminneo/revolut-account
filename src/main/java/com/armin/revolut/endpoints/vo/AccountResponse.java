package com.armin.revolut.endpoints.vo;

import com.armin.revolut.core.BaseResponse;
import com.armin.revolut.models.tables.pojos.Account;
import com.armin.revolut.models.tables.pojos.Records;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountResponse extends BaseResponse {
    private String code;
    private long userId;
    private BigDecimal balance;
    private List<RecordResponse> records;
    private LocalDateTime created;
    private LocalDateTime updated;

    public AccountResponse() {
    }

    public AccountResponse(long userId, String code, BigDecimal balance, List<RecordResponse> records, LocalDateTime created, LocalDateTime updated) {
        this.userId = userId;
        this.code = code;
        this.balance = balance;
        this.records = records;
        this.created = created;
        this.updated = updated;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<RecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<RecordResponse> records) {
        this.records = records;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public static AccountResponse transform(Account acc, List<Records> records) {
        List<RecordResponse> recordResponses = new ArrayList<>();
        if (records != null && records.size() > 0) {
            recordResponses = records.stream()
                    .map(RecordResponse::transform)
                    .collect(Collectors.toList());
        }

        return new AccountResponse(acc.getUserId(), acc.getCode(), acc.getBalance(), recordResponses,
                acc.getCreatedOn(), acc.getUpdatedOn());
    }

}
