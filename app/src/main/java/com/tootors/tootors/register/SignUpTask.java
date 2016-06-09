package com.tootors.tootors.register;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tootors.tootors.client.ApiException;
import com.tootors.tootors.client.api.DefaultApi;
import com.tootors.tootors.client.model.Tootor;
import com.tootors.tootors.client.model.Error;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class SignUpTask extends AsyncTask<Void, Void, Tootor> {

    WeakReference<Activity> activity;
    WeakReference<Tootor> tootor;

    public SignUpTask(Activity activity, Tootor tootor) {
        this.activity = new WeakReference<Activity>(activity);
        this.tootor = new WeakReference<Tootor>(tootor);
    }

    @Override
    protected Tootor doInBackground(Void... params) {

        Tootor t = null;
        Tootor t2 = tootor.get();

        try {
            DefaultApi api = new DefaultApi();
            t = api.createTootor("", t2);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return t;
    }

    @Override
    protected void onPostExecute(Tootor tootor) {
        Activity act = activity.get();

        if (act == null) return;

        if (tootor != null && tootor.getId() != null && tootor.getId() > 0) {
            Intent profile = new Intent(act, ProfileActivity.class);
            String json = new Gson().toJson(tootor);
            profile.putExtra(ProfileActivity.EXTRA, json);
            act.startActivity(profile);
        } else {
            Toast.makeText(act, "Unable to create new user. Please try again.",
                    Toast.LENGTH_LONG).show();
        }

    }
}