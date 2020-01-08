package com.armin.revolut.data;

import org.jooq.DSLContext;

public interface ContextManager {
    DSLContext getDslContext();
}
