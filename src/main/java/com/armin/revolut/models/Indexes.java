/*
 * This file is generated by jOOQ.
 */
package com.armin.revolut.models;


import com.armin.revolut.models.tables.Account;
import com.armin.revolut.models.tables.Records;

import javax.annotation.processing.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code></code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index CONSTRAINT_INDEX_E = Indexes0.CONSTRAINT_INDEX_E;
    public static final Index PRIMARY_KEY_E = Indexes0.PRIMARY_KEY_E;
    public static final Index FK_ACCOUNT_ID_INDEX_6 = Indexes0.FK_ACCOUNT_ID_INDEX_6;
    public static final Index PRIMARY_KEY_6 = Indexes0.PRIMARY_KEY_6;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index CONSTRAINT_INDEX_E = Internal.createIndex("CONSTRAINT_INDEX_E", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.CODE }, true);
        public static Index PRIMARY_KEY_E = Internal.createIndex("PRIMARY_KEY_E", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.ID }, true);
        public static Index FK_ACCOUNT_ID_INDEX_6 = Internal.createIndex("FK_ACCOUNT_ID_INDEX_6", Records.RECORDS, new OrderField[] { Records.RECORDS.ACCOUNT_ID }, false);
        public static Index PRIMARY_KEY_6 = Internal.createIndex("PRIMARY_KEY_6", Records.RECORDS, new OrderField[] { Records.RECORDS.ID }, true);
    }
}