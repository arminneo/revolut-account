package com.armin.revolut;

import com.armin.revolut.data.ContextManager;
import com.armin.revolut.stores.AccountStore;
import com.armin.revolut.stores.RecordStore;
import spark.Spark;

public class BaseAppTest {
    protected static final int PORT = 4000;
    protected Application app;
    protected ContextManager db;
    protected AccountStore accountStore;
    protected RecordStore recordStore;

    protected void init(){
        db = Dependencies.getInjector().getInstance(ContextManager.class);
        app = new Application();
        app.startServer();
        Spark.awaitInitialization();
        accountStore = Dependencies.getInjector().getInstance(AccountStore.class);
        recordStore = Dependencies.getInjector().getInstance(RecordStore.class);

    }

    protected void tearDown(){
        app.stopServer();
    }
}
