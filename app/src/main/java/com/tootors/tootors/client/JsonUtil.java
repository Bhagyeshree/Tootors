package com.tootors.tootors.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import com.tootors.tootors.client.model.*;
import com.tootors.tootors.client.model.Error;

public class JsonUtil {
    public static GsonBuilder gsonBuilder;

    static {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    public static Gson getGson() {
        return gsonBuilder.create();
    }

    public static String serialize(Object obj) {
        return getGson().toJson(obj);
    }

    public static <T> T deserializeToList(String jsonString, Class cls) {
        return getGson().fromJson(jsonString, getListTypeForDeserialization(cls));
    }

    public static <T> T deserializeToObject(String jsonString, Class cls) {
        return getGson().fromJson(jsonString, getTypeForDeserialization(cls));
    }

    public static Type getListTypeForDeserialization(Class cls) {
        String className = cls.getSimpleName();

        if ("Error".equalsIgnoreCase(className)) {
            return new TypeToken<List<Error>>() {
            }.getType();
        }

        if ("InlineResponse200".equalsIgnoreCase(className)) {
            return new TypeToken<List<InlineResponse200>>() {
            }.getType();
        }

        if ("Tootor".equalsIgnoreCase(className)) {
            return new TypeToken<List<Tootor>>() {
            }.getType();
        }

        return new TypeToken<List<Object>>() {
        }.getType();
    }

    public static Type getTypeForDeserialization(Class cls) {
        String className = cls.getSimpleName();

        if ("Error".equalsIgnoreCase(className)) {
            return new TypeToken<Error>() {
            }.getType();
        }

        if ("InlineResponse200".equalsIgnoreCase(className)) {
            return new TypeToken<InlineResponse200>() {
            }.getType();
        }

        if ("Tootor".equalsIgnoreCase(className)) {
            return new TypeToken<Tootor>() {
            }.getType();
        }

        return new TypeToken<Object>() {
        }.getType();
    }

};
