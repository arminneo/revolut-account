package com.armin.revolut;


import com.armin.revolut.helpers.GsonResponseTransformer;
import com.armin.revolut.helpers.JsonResponseTransformerImpl;
import com.armin.revolut.data.ContextManager;
import com.armin.revolut.data.ContextManagerImpl;
import com.armin.revolut.stores.*;
import com.google.inject.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dependencies extends AbstractModule {
    private static final Logger log = LoggerFactory.getLogger(Dependencies.class);

    private static Dependencies instance = new Dependencies();
    private static Injector injector = Guice.createInjector(instance);

    public static Dependencies getInstance() {
        return instance;
    }

    public static Injector getInjector() {
        return injector;
    }

    public Dependencies() {

    }

    @Override
    protected void configure() {
        bind(ContextManager.class).to(ContextManagerImpl.class);
        bind(AccountStore.class).to(AccountStoreImpl.class);
        bind(RecordStore.class).to(RecordStoreImpl.class);
        bind(TransferStore.class).to(TransferStoreImpl.class);
        bind(GsonResponseTransformer.class).to(JsonResponseTransformerImpl.class);
    }
}