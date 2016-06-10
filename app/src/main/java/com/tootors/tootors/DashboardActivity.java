package com.tootors.tootors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tootors.tootors.map.MapActivity;
import com.tootors.tootors.register.ProfileActivity;

public class DashboardActivity extends AppCompatActivity {

    String user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                user = extras.getString(ProfileActivity.EXTRA);
            }
        } else {
            user = (String) savedInstanceState.getSerializable(ProfileActivity.EXTRA);
        }
    }

    public void onButtonClick(View v){

        if(v.getId() == R.id.my_account_button)
        {
            Intent i = new Intent(this, ProfileActivity.class);
            i.putExtra(ProfileActivity.EXTRA, user);
            startActivity(i);
        } else if(v.getId() == R.id.search_tootor_button)
        {
            Intent i = new Intent(this, MainTootorsActivity.class);
            startActivity(i);
        } else if(v.getId() == R.id.search_tootor_map_button)
        {
            Intent i = new Intent(this, MapActivity.class);
            startActivity(i);
        }
    }
}
