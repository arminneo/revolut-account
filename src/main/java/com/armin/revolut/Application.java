package com.armin.revolut;

import com.armin.revolut.endpoints.AccountEndPoint;
import com.armin.revolut.exceptions.ExceptionMiddleware;
import com.armin.revolut.services.TransferQueueServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.util.Date;

import static spark.Spark.*;


public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private TransferQueueServer transferServer;
    private AccountEndPoint accountEndPoint;


    public Application() {
        System.getProperties().setProperty("org.jooq.no-logo", "true");
        transferServer = new TransferQueueServer();
        accountEndPoint = new AccountEndPoint(transferServer);
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.startServer();
    }

    public TransferQueueServer getTransferServer() {
        return transferServer;
    }

    public void startServer() {
        transferServer.start();

        port(4000);

        path("/api/v1", () -> {
            accountEndPoint.route();
        });
        get("/health", (req, res) -> String.format("Working... Time: %s", new Date()));

        exception(Exception.class, new ExceptionMiddleware());
    }

    public void stopServer() {
        transferServer.stop();
        Spark.stop();
        Spark.awaitStop();
    }
}