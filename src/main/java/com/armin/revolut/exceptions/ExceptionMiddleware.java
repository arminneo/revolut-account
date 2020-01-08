package com.armin.revolut.exceptions;

import com.armin.revolut.Dependencies;
import com.armin.revolut.core.EndPoint;
import com.armin.revolut.endpoints.vo.ExceptionResponse;
import com.armin.revolut.helpers.GsonResponseTransformer;
import com.google.gson.stream.MalformedJsonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class ExceptionMiddleware implements ExceptionHandler<Exception> {
    public static final Logger log = LoggerFactory.getLogger(ExceptionMiddleware.class);
    private final GsonResponseTransformer transform;

    public ExceptionMiddleware() {
        this.transform = Dependencies.getInjector().getInstance(GsonResponseTransformer.class);
    }

    @Override
    public void handle(Exception e, Request request, Response response) {
        response.type(EndPoint.APPLICATION_JSON);
        if (e instanceof MalformedJsonException) {
            response.status(400);
            ExceptionResponse ex = ExceptionResponse.from(new BadRequestException("Bad json provided"));
            response.body(transform.render(ex));
            return;
        }

        if (e instanceof ApiException) {
            ApiException ex = (ApiException) e;
            response.status(ex.getStatusCode());
            response.body(transform.render(ExceptionResponse.from(ex)));
            return;
        }
        response.status(500);
        response.body(transform.render(e.getMessage()));
        log.error("Internal Server Error: ", e);
    }
}
