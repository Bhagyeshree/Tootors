package com.tootors.tootors.register;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tootors.tootors.MainTootorsActivity;
import com.tootors.tootors.TootorActivity;
import com.tootors.tootors.client.ApiException;
import com.tootors.tootors.client.api.MockTootorApi;
import com.tootors.tootors.client.api.TootorApi;
import com.tootors.tootors.client.model.Tootor;


import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class LoginTask extends AsyncTask<String, Void, Tootor> {

    WeakReference<Activity> activity;

    public LoginTask(Activity activity) {
        this.activity = new WeakReference<>(activity);
    }

    @Override
    protected Tootor doInBackground(String... params) {
        if (params.length != 2) return null;

        TootorApi api = new TootorApi();
//        MockTootorApi api = new MockTootorApi();
        try {

            return api.loginPost(params[0], params[1]);

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

        if (act == null) return;

        if (tootor != null) {
            Intent profile = new Intent(act, MainTootorsActivity.class);
            act.startActivity(profile);
        } else {
            Toast.makeText(act, "Incorrect Username/Password", Toast.LENGTH_LONG).show();
        }

    }
}
