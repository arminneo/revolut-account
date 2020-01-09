package com.armin.revolut.core;

import com.armin.revolut.Dependencies;
import com.armin.revolut.helpers.GsonResponseTransformer;
import com.google.gson.Gson;

public abstract class EndPoint {
    public static final String APPLICATION_JSON = "application/json";
    protected GsonResponseTransformer transform;
    protected Gson json;

    public EndPoint() {
        this.transform = Dependencies.getInjector().getInstance(GsonResponseTransformer.class);
        this.json = transform.getEngine();
    }

    public abstract void route();
}
