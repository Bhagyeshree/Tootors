package com.tootors.tootors;

import android.os.AsyncTask;

import com.tootors.tootors.client.ApiException;
import com.tootors.tootors.client.api.DefaultApi;
import com.tootors.tootors.client.model.InlineResponse200;
import com.tootors.tootors.client.model.Tootor;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public class FetchTootorTask extends AsyncTask<String, Void, List<Tootor>> {

    private final String LOG_TAG = FetchTootorTask.class.getSimpleName();

    WeakReference<TootorsFragment> fragment;

    public FetchTootorTask(TootorsFragment fragment) {
        this.fragment = new WeakReference<>(fragment);
    }

    @Override
    protected List<Tootor> doInBackground(String... params) {

        DefaultApi api = new DefaultApi();
        String zip = null;

        if (params.length > 0) zip = params[0];

        try {

            InlineResponse200 response = api.getTootors("", null, zip, null, "true");

            List<Tootor> tootors = response.getResults();

            return tootors;

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Tootor> result) {

        super.onPostExecute(result);

        TootorsFragment frag = fragment.get();

        if (frag == null) {
            return;
        }

        frag.setTootors(result);
        frag.updateListView();
    }
}