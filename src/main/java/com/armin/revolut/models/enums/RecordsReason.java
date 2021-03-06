/*
 * This file is generated by jOOQ.
 */
package com.armin.revolut.models.enums;


import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


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
public enum RecordsReason implements EnumType {

    deposit("deposit"),

    transfer("transfer"),

    payout("payout"),

    etc("etc");

    private final String literal;

    private RecordsReason(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return null;
    }

    @Override
    public Schema getSchema() {
        return null;
    }

    @Override
    public String getName() {
        return "RECORDS_REASON";
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}
