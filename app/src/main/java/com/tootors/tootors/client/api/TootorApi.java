package com.tootors.tootors.client.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tootors.tootors.client.ApiInvoker;
import com.tootors.tootors.client.ApiException;
import com.tootors.tootors.client.Pair;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.tootors.tootors.client.model.Tootor;
import com.tootors.tootors.client.model.Error;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class TootorApi {
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
     * Delete Tootor
     * Delete Tootor by an ID
     *
     * @param id ID of Tootor to fetch
     * @return Tootor
     */
    public Tootor deleteTootorById(Long id) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling deleteTootorById",
                    new ApiException(400, "Missing the required parameter 'id' when calling deleteTootorById"));
        }


        // create path and map variables
        String path = "/tootor/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


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
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, postBody, headerParams, formParams, contentType);
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
     * Read Tootor
     * Find tootor by an ID
     *
     * @param id ID of Tootor to fetch
     * @return Tootor
     */
    public Tootor findTootorById(Long id) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling findTootorById",
                    new ApiException(400, "Missing the required parameter 'id' when calling findTootorById"));
        }


        // create path and map variables
        String path = "/tootor/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


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
                return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(localVarResponse, Tootor.class);
            } else {
                return null;
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }

    /**
     * Account access
     * Log in and out of Tootors app
     *
     * @param username
     * @param password
     * @return Tootor
     */
    public Tootor loginPost(String username, String password) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'username' is set
        if (username == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'username' when calling loginPost",
                    new ApiException(400, "Missing the required parameter 'username' when calling loginPost"));
        }

        // verify the required parameter 'password' is set
        if (password == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'password' when calling loginPost",
                    new ApiException(400, "Missing the required parameter 'password' when calling loginPost"));
        }


        // create path and map variables
        String path = "/login".replaceAll("\\{format\\}", "json");

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();

        queryParams.addAll(ApiInvoker.parameterToPairs("", "username", username));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "password", password));

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
     * Account access
     * Log in and out of Tootors app
     *
     * @return Tootor
     */
    public Tootor logoutPost() throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;


        // create path and map variables
        String path = "/logout".replaceAll("\\{format\\}", "json");

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


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
     * Update Tootor
     * Update tootor by an ID
     *
     * @param id ID of Tootor to fetch
     * @return Tootor
     */
    public Tootor updateTootorById(Long id) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling updateTootorById",
                    new ApiException(400, "Missing the required parameter 'id' when calling updateTootorById"));
        }


        // create path and map variables
        String path = "/tootor/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


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
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, postBody, headerParams, formParams, contentType);
            if (localVarResponse != null) {
                return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(localVarResponse, Tootor.class);
            } else {
                return null;
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }


}
