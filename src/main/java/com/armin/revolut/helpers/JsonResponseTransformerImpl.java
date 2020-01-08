package com.armin.revolut.helpers;

import com.google.gson.*;
import spark.ResponseTransformer;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JsonResponseTransformerImpl implements GsonResponseTransformer {
    private Gson gson;

    public JsonResponseTransformerImpl() {
        GsonBuilder builder = new GsonBuilder();
        Set<String> excludes = new HashSet<>(Arrays.asList("suppressedexceptions", "stacktrace"));
        gson = builder
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        return excludes.contains(fieldAttributes.getName().toLowerCase());
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(LocalDateTime date, Type type, JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // "yyyy-mm-dd"
                    }
                })
                .create();
    }

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    @Override
    public Gson getEngine() {
        return gson;
    }

}
