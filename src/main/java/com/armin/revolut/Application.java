package com.armin.revolut;

import com.armin.revolut.account.AccountEndPoint;
import com.google.inject.Injector;
import com.google.inject.Guice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static spark.Spark.*;


public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        port(4000);

        Injector injector = Dependencies.getInjector();

        path("/api/v1", () -> {
            new AccountEndPoint();
        });
        get("/health", (req, res) -> String.format("Working... Time: %s", new Date()));

        initExceptionHandler((e) -> {
            log.error("Error", e);
        });
    }
}
