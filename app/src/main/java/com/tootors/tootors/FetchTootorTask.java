package com.tootors.tootors;

import android.app.Activity;
import android.os.AsyncTask;

import com.tootors.tootors.client.ApiException;
import com.tootors.tootors.client.api.DefaultApi;
import com.tootors.tootors.client.model.InlineResponse200;
import com.tootors.tootors.client.model.Tootor;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by JordanTon on 6/8/2016.
 */
public class FetchTootorTask extends AsyncTask<String, Void, Tootor> {

    private final String LOG_TAG = FetchTootorTask.class.getSimpleName();

    WeakReference<Activity> activity;

    private List<Tootor> tootors = new ArrayList<Tootor>();

    public FetchTootorTask(Activity activity) {

        this.activity = new WeakReference<>(activity);
    }

    @Override
    protected Tootor doInBackground(String... params) {

        DefaultApi api = new DefaultApi();

        try {

            InlineResponse200 response = api.getTootors("", null, null, null, "true");

            tootors = response.getResults();

            String[] resultStrs = new String[10];

            for (int i = 0; i < tootors.size(); i++) {

                String name;
                String city;
                String focus;

                name = tootors.get(i).getName();
                city = tootors.get(i).getCity();
                focus = tootors.get(i).getFocus();

                resultStrs[i] = "Name: " + name + " - City: " + city + " - Focus: " + focus;
            }

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
    protected void onPostExecute(Tootor tootor) {

        Activity act = activity.get();

        if (act == null) {

            return;
        }

        if (tootor != null) {


        }
    }
}