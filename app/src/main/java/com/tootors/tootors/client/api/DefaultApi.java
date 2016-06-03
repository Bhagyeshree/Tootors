package com.tootors.tootors.client.api;

import java.util.*;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;
import com.tootors.tootors.client.ApiException;
import com.tootors.tootors.client.ApiInvoker;
import com.tootors.tootors.client.Pair;
import com.tootors.tootors.client.model.InlineResponse200;
import com.tootors.tootors.client.model.Tootor;


import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class DefaultApi {
    String basePath = "http://162.243.155.134/api/tootor/";
    ApiInvoker apiInvoker = ApiInvoker.getInstance();

    public void addHeader(String key, String value) {
        getInvoker().addDefaultHeader(key, value);
    }

    public ApiInvoker getInvoker() {
        return apiInvoker;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getBasePath() {
        return basePath;
    }

    /**
     * Create Tootor
     * Update Tootor information
     *
     * @param apiKey API Key
     * @return Tootor
     */
    public Tootor createTootorById(String apiKey) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'apiKey' is set
        if (apiKey == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'apiKey' when calling createTootorById",
                    new ApiException(400, "Missing the required parameter 'apiKey' when calling createTootorById"));
        }


        // create path and map variables
        String path = "/tootor".replaceAll("\\{format\\}", "json");

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();

        queryParams.addAll(ApiInvoker.parameterToPairs("", "api-key", apiKey));


        String[] contentTypes = {

        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

        if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }

        String[] authNames = new String[]{};

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, postBody, headerParams, formParams, contentType);
            if (localVarResponse != null) {
                return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(localVarResponse, Tootor.class);
            } else {
                return null;
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }

    /**
     * Gets all Tootors
     * Search Tootors
     *
     * @param apiKey   Your API key
     * @param name     Search Tootors by name
     * @param location Search Tootors by location
     * @param focus    Search Tootors by focus
     * @param isTootor Search Tootors or non-Tootors only
     * @return InlineResponse200
     */
    public InlineResponse200 getTootors(String apiKey, String name, String location, String focus, String isTootor) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'apiKey' is set
        if (apiKey == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'apiKey' when calling getTootors",
                    new ApiException(400, "Missing the required parameter 'apiKey' when calling getTootors"));
        }


        // create path and map variables
        String path = "/tootor".replaceAll("\\{format\\}", "json");

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();

        queryParams.addAll(ApiInvoker.parameterToPairs("", "name", name));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "location", location));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "focus", focus));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "is_tootor", isTootor));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "api-key", apiKey));


        String[] contentTypes = {

        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

        if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }

        String[] authNames = new String[]{};

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType);
            if (localVarResponse != null) {
//                return (InlineResponse200) ApiInvoker.deserialize(localVarResponse, "", InlineResponse200.class);
                return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(localVarResponse, InlineResponse200.class);
            } else {
                return null;
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }

}
