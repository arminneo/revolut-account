/*
 * This file is generated by jOOQ.
 */
package com.armin.revolut.models.tables.pojos;


import com.armin.revolut.models.enums.RecordsReason;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Records implements Serializable {

    private static final long serialVersionUID = -1186868188;

    private final Long          id;
    private final Long          accountId;
    private final BigDecimal    credit;
    private final BigDecimal    debit;
    private final RecordsReason reason;
    private final LocalDateTime createdOn;

    public Records(Records value) {
        this.id = value.id;
        this.accountId = value.accountId;
        this.credit = value.credit;
        this.debit = value.debit;
        this.reason = value.reason;
        this.createdOn = value.createdOn;
    }

    public Records(
        Long          id,
        Long          accountId,
        BigDecimal    credit,
        BigDecimal    debit,
        RecordsReason reason,
        LocalDateTime createdOn
    ) {
        this.id = id;
        this.accountId = accountId;
        this.credit = credit;
        this.debit = debit;
        this.reason = reason;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return this.id;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public BigDecimal getCredit() {
        return this.credit;
    }

    public BigDecimal getDebit() {
        return this.debit;
    }

    public RecordsReason getReason() {
        return this.reason;
    }

    public LocalDateTime getCreatedOn() {
        return this.createdOn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Records (");

        sb.append(id);
        sb.append(", ").append(accountId);
        sb.append(", ").append(credit);
        sb.append(", ").append(debit);
        sb.append(", ").append(reason);
        sb.append(", ").append(createdOn);

        sb.append(")");
        return sb.toString();
    }
}
