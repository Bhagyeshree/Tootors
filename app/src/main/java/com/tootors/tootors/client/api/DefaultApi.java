package com.tootors.tootors.client.api;

import com.android.volley.VolleyError;
import com.tootors.tootors.client.ApiException;
import com.tootors.tootors.client.ApiInvoker;
import com.tootors.tootors.client.Pair;
import com.tootors.tootors.client.model.InlineResponse200;
import com.tootors.tootors.client.model.Parser;
import com.tootors.tootors.client.model.Tootor;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class DefaultApi {
    String basePath = "http://162.243.155.134/api";
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
    public Tootor createTootor(String apiKey, Tootor tootor) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
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

        queryParams.addAll(ApiInvoker.parameterToPairs("", "is_tootor", tootor.getIsTootor() ? "true" : "false"));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "username", tootor.getUsername()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "seo_name", tootor.getSeoName()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "email", tootor.getEmail()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "password", tootor.getPassword()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "name", tootor.getName()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "phone", tootor.getPhone()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "price", String.format(Locale.US, "%.2f", tootor.getPrice())));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "street", tootor.getStreet()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "city", tootor.getCity()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "state", tootor.getState()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "zip", tootor.getZip()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "focus", tootor.getFocus()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "description", tootor.getDescription()));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "picture", "No Picture"));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "video", tootor.getVideo().isEmpty() ? "No Video" : tootor.getVideo()));

        String[] contentTypes = {};

        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

        if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, postBody, headerParams, formParams, contentType);
            if (localVarResponse != null) {
                return Parser.tootor(localVarResponse);
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

        if (name != null)
            queryParams.addAll(ApiInvoker.parameterToPairs("", "name", name));
        else if (location != null)
            queryParams.addAll(ApiInvoker.parameterToPairs("", "location", location));
        else if (focus != null)
            queryParams.addAll(ApiInvoker.parameterToPairs("", "focus", focus));

        if (isTootor != null)
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
                return Parser.inlineResponse(localVarResponse);
            } else {
                return null;
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }

}
