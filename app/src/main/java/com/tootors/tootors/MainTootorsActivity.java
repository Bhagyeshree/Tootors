package com.tootors.tootors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tootors.tootors.map.MapActivity;
import com.tootors.tootors.register.LoginActivity;
import com.tootors.tootors.register.SignUpActivity;

public class MainTootorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tootors);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_tootors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_signup) {
            startActivity(new Intent(this, SignUpActivity.class));
            return true;
        }

        if (id == R.id.action_login) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }

        if (id == R.id.action_open_map) {
            startActivity(new Intent(this, MapActivity.class));
            return true;
        }

        if (id == R.id.action_tootor) {

            startActivity(new Intent(this, MainTootorsActivity.class));
            return true;
        }
        //It will return item

        return super.onOptionsItemSelected(item);
    }
}
