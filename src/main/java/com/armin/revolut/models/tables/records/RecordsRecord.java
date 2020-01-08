/*
 * This file is generated by jOOQ.
 */
package com.armin.revolut.models.tables.records;


import com.armin.revolut.models.enums.RecordsReason;
import com.armin.revolut.models.tables.Records;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


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
public class RecordsRecord extends UpdatableRecordImpl<RecordsRecord> implements Record6<Long, Long, BigDecimal, BigDecimal, RecordsReason, LocalDateTime> {

    private static final long serialVersionUID = -1437922150;

    /**
     * Setter for <code>RECORDS.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>RECORDS.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>RECORDS.ACCOUNT_ID</code>.
     */
    public void setAccountId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>RECORDS.ACCOUNT_ID</code>.
     */
    public Long getAccountId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>RECORDS.CREDIT</code>.
     */
    public void setCredit(BigDecimal value) {
        set(2, value);
    }

    /**
     * Getter for <code>RECORDS.CREDIT</code>.
     */
    public BigDecimal getCredit() {
        return (BigDecimal) get(2);
    }

    /**
     * Setter for <code>RECORDS.DEBIT</code>.
     */
    public void setDebit(BigDecimal value) {
        set(3, value);
    }

    /**
     * Getter for <code>RECORDS.DEBIT</code>.
     */
    public BigDecimal getDebit() {
        return (BigDecimal) get(3);
    }

    /**
     * Setter for <code>RECORDS.REASON</code>.
     */
    public void setReason(RecordsReason value) {
        set(4, value);
    }

    /**
     * Getter for <code>RECORDS.REASON</code>.
     */
    public RecordsReason getReason() {
        return (RecordsReason) get(4);
    }

    /**
     * Setter for <code>RECORDS.CREATED_ON</code>.
     */
    public void setCreatedOn(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>RECORDS.CREATED_ON</code>.
     */
    public LocalDateTime getCreatedOn() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, Long, BigDecimal, BigDecimal, RecordsReason, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Long, Long, BigDecimal, BigDecimal, RecordsReason, LocalDateTime> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Records.RECORDS.ID;
    }

    @Override
    public Field<Long> field2() {
        return Records.RECORDS.ACCOUNT_ID;
    }

    @Override
    public Field<BigDecimal> field3() {
        return Records.RECORDS.CREDIT;
    }

    @Override
    public Field<BigDecimal> field4() {
        return Records.RECORDS.DEBIT;
    }

    @Override
    public Field<RecordsReason> field5() {
        return Records.RECORDS.REASON;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return Records.RECORDS.CREATED_ON;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getAccountId();
    }

    @Override
    public BigDecimal component3() {
        return getCredit();
    }

    @Override
    public BigDecimal component4() {
        return getDebit();
    }

    @Override
    public RecordsReason component5() {
        return getReason();
    }

    @Override
    public LocalDateTime component6() {
        return getCreatedOn();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getAccountId();
    }

    @Override
    public BigDecimal value3() {
        return getCredit();
    }

    @Override
    public BigDecimal value4() {
        return getDebit();
    }

    @Override
    public RecordsReason value5() {
        return getReason();
    }

    @Override
    public LocalDateTime value6() {
        return getCreatedOn();
    }

    @Override
    public RecordsRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public RecordsRecord value2(Long value) {
        setAccountId(value);
        return this;
    }

    @Override
    public RecordsRecord value3(BigDecimal value) {
        setCredit(value);
        return this;
    }

    @Override
    public RecordsRecord value4(BigDecimal value) {
        setDebit(value);
        return this;
    }

    @Override
    public RecordsRecord value5(RecordsReason value) {
        setReason(value);
        return this;
    }

    @Override
    public RecordsRecord value6(LocalDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    public RecordsRecord values(Long value1, Long value2, BigDecimal value3, BigDecimal value4, RecordsReason value5, LocalDateTime value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RecordsRecord
     */
    public RecordsRecord() {
        super(Records.RECORDS);
    }

    /**
     * Create a detached, initialised RecordsRecord
     */
    public RecordsRecord(Long id, Long accountId, BigDecimal credit, BigDecimal debit, RecordsReason reason, LocalDateTime createdOn) {
        super(Records.RECORDS);

        set(0, id);
        set(1, accountId);
        set(2, credit);
        set(3, debit);
        set(4, reason);
        set(5, createdOn);
    }
}
