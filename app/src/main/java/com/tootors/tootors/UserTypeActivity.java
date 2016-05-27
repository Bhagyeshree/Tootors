package com.tootors.tootors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class UserTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
    }

    public void onButtonClick(View v){

        if(v.getId() == R.id.student_button)
        {

            Intent i = new Intent(this, Display.class);
            startActivity(i);
        }

        if(v.getId() == R.id.tutor_button)
        {
            Intent i = new Intent(this, SignUpTutorActivity.class);
            startActivity(i);
        }

    }
}
