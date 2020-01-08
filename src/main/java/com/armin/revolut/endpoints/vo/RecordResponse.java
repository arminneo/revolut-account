package com.armin.revolut.endpoints.vo;

import com.armin.revolut.models.enums.RecordsReason;
import com.armin.revolut.models.tables.pojos.Records;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RecordResponse {
    private BigDecimal credit = BigDecimal.ZERO;
    private BigDecimal debit = BigDecimal.ZERO;
    private RecordsReason reason;
    private LocalDateTime date;

    public RecordResponse() {
    }

    public RecordResponse(BigDecimal credit, BigDecimal debit, RecordsReason reason, LocalDateTime date) {
        this.credit = credit;
        this.debit = debit;
        this.reason = reason;
        this.date = date;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public RecordsReason getReason() {
        return reason;
    }

    public void setReason(RecordsReason reason) {
        this.reason = reason;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public static RecordResponse transform(Records rec){
        return new RecordResponse(rec.getCredit(), rec.getDebit(), rec.getReason(), rec.getCreatedOn());
    }

}
