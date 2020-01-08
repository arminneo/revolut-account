/*
 * This file is generated by jOOQ.
 */
package com.armin.revolut.models.tables.pojos;


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
public class Account implements Serializable {

    private static final long serialVersionUID = -364425600;

    private final Long          id;
    private final String        code;
    private final Integer       userId;
    private final BigDecimal    balance;
    private final LocalDateTime createdOn;
    private final LocalDateTime updatedOn;

    public Account(Account value) {
        this.id = value.id;
        this.code = value.code;
        this.userId = value.userId;
        this.balance = value.balance;
        this.createdOn = value.createdOn;
        this.updatedOn = value.updatedOn;
    }

    public Account(
        Long          id,
        String        code,
        Integer       userId,
        BigDecimal    balance,
        LocalDateTime createdOn,
        LocalDateTime updatedOn
    ) {
        this.id = id;
        this.code = code;
        this.userId = userId;
        this.balance = balance;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Long getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public LocalDateTime getCreatedOn() {
        return this.createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Account (");

        sb.append(id);
        sb.append(", ").append(code);
        sb.append(", ").append(userId);
        sb.append(", ").append(balance);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(updatedOn);

        sb.append(")");
        return sb.toString();
    }
}