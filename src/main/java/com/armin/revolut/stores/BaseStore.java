package com.armin.revolut.stores;

import com.armin.revolut.Dependencies;
import com.armin.revolut.data.ContextManager;

public class BaseStore {
    protected ContextManager db;

    public BaseStore() {
        this.db = Dependencies.getInjector().getInstance(ContextManager.class);
    }
}
