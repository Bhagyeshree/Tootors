package com.tootors.tootors.client.model;


import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * Parses JSON data to Objects
 */
public class Parser {

    public static Tootor tootor(String json) {
        return new GsonBuilder().registerTypeAdapter(Date.class, new DateFormat()).
                create().fromJson(json, Tootor.class);
    }

    public static InlineResponse200 inlineResponse(String json) {
        return new GsonBuilder().registerTypeAdapter(Date.class, new DateFormat()).
                create().fromJson(json, InlineResponse200.class);
    }

}
