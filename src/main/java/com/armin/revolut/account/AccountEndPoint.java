package com.armin.revolut.account;

import com.armin.revolut.core.EndPoint;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class AccountEndPoint extends EndPoint {

    public AccountEndPoint() {
        path("/account", () -> {
            get("/", this::getAccounts);
        });

    }

    public Object getAccounts(Request request, Response response) throws Exception {
        return "Accounts here";
    }


}
