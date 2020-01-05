package com.armin.revolut;


import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dependencies implements Module {
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
    public void configure(Binder binder) {
        //binder.bind(CustomerDao.class).to(CustomerDaoImpl.class);
        //binder.bind(UserService.class).to(UserServiceImpl.class);
    }

}