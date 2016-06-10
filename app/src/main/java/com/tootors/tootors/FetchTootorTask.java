package com.tootors.tootors;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.tootors.tootors.client.ApiException;
import com.tootors.tootors.client.api.DefaultApi;
import com.tootors.tootors.client.model.InlineResponse200;
import com.tootors.tootors.client.model.Tootor;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by JordanTon on 6/8/2016.
 */
public class FetchTootorTask extends AsyncTask<String, Void, String[]> {

    private final String LOG_TAG = FetchTootorTask.class.getSimpleName();

    private ArrayAdapter<String> mTootorsAdapter;

    private List<Tootor> tootors;

    public FetchTootorTask() {

    }

    private String[] getTootorFromList(List<Tootor> tootors, int numTootor) {

        String[] resultStrs = new String[numTootor];

        for (int i = 0; i < tootors.size(); i++) {

            String name;
            String city;
            String focus;

            name = tootors.get(i).getName();
            city = tootors.get(i).getCity();
            focus = tootors.get(i).getFocus();

            resultStrs[i] = "Name: " + name + " - City: " + city + " - Focus: " + focus;
        }

        return resultStrs;
    }

    @Override
    protected String[] doInBackground(String... params) {

        int numTootor = 10;

        DefaultApi api = new DefaultApi();

        try {

            InlineResponse200 response = api.getTootors("", null, null, null, "true");

            tootors = response.getResults();

            return getTootorFromList(tootors, numTootor);

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
    protected void onPostExecute(String[] result) {

        super.onPostExecute(result);

        if (result != null) {

            mTootorsAdapter.clear();

            mTootorsAdapter.addAll(result);
        }
    }
}