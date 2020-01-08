package com.armin.revolut.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import spark.ResponseTransformer;

public interface GsonResponseTransformer extends ResponseTransformer {
    @Override
    String render(Object model);

    <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException;

    Gson getEngine();
}
