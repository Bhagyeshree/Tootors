package com.tootors.tootors;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TootorsFragment extends Fragment {

    private ArrayAdapter<String> mTootorsAdapter;

    public TootorsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mTootorsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_tootors,
                R.id.list_item_tooters_textview, new ArrayList<String>());

        View rootView = inflater.inflate(R.layout.fragment_main_tootors, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_tootors);

        listView.setAdapter(mTootorsAdapter);

        return rootView;
    }

    private void updateTootors() {

        FetchTootorTask tootorTask = new FetchTootorTask();

        tootorTask.execute();
    }

    @Override
    public void onStart() {
        super.onStart();

        updateTootors();
    }

    public class FetchTootorTask extends AsyncTask<Void, Void, String[]> {

        private final String LOG_TAG = FetchTootorTask.class.getSimpleName();

        private String[] getTootorsDataFromJson(String individualJsonStr, int numTutors) throws JSONException {

            final String T_RESULTS = "items";
//            final String IS_TUTOR = "is_tootor";
            final String T_NAME = "name";
            final String T_PHONE = "phone";
            final String T_EMAIL = "email";
            final String T_City = "city";
            final String T_FOCUS = "focus";

            // Turn a Json string into a Json object
            JSONObject individualJson = new JSONObject(individualJsonStr);

            String[] resultStrs = new String[numTutors];

            // Look for the results array
            JSONArray individualArray = individualJson.getJSONArray(T_RESULTS);

            for (int i = 0; i < individualArray.length(); i++) {

//                String is_tutor;
                String name;
                String phone;
                String email;
                String city;
                String focus;

                // Get the Json object representing a tutor
                JSONObject tootor = individualArray.getJSONObject(i);

//                    is_tutor = tootor.getString(IS_TUTOR);

//                    if (is_tutor.equalsIgnoreCase("t")) {

                name = tootor.getString(T_NAME);
                phone = tootor.getString(T_PHONE);
                email = tootor.getString(T_EMAIL);
                city = tootor.getString(T_City);
                focus = tootor.getString(T_FOCUS);


                resultStrs[i] = "Name: " + name + " - Phone: " + phone + " - Email: " +
                        email + " - City: " + city + " - Focus: " + focus;
            }

            return resultStrs;
        }

        @Override
        protected String[] doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String individualJsonStr = null;

            int numTutors = 5;

            try {

                // Construct the URL for the server
                String baseUrl = "http://162.243.155.134/api/tootor?is_tootor=true";
                URL url = new URL(baseUrl);

                Log.v(LOG_TAG, "Build URL: " + url.toString());

                // Create the request to the server and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");

                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();

                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }

                individualJsonStr = buffer.toString();

                Log.v(LOG_TAG, "Tooters' JSON String: " + individualJsonStr);

            } catch (IOException e) {

                Log.e(LOG_TAG, "Error ", e);
                return null;

            } finally {

                if (urlConnection != null) {
                    urlConnection.disconnect();
                }

                if (reader != null) {

                    try {
                        reader.close();

                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {

                return getTootorsDataFromJson(individualJsonStr, numTutors);

            } catch (JSONException e) {

                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {

            super.onPreExecute();

            if (result != null) {

                mTootorsAdapter.clear();

                mTootorsAdapter.addAll(result);
            }
        }
    }
}
