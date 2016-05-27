package com.tootors.tootors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }



    public void onButtonClick(View v){


        if(v.getId() == R.id.login_button)
        {
            username = (EditText)findViewById(R.id.username_input);

            String str = username.getText().toString();


            Intent i = new Intent(this, Display.class);

            i.putExtra("Username", str);

            startActivity(i);
        }

        if(v.getId() == R.id.signup_button)
        {
            Intent i = new Intent(this, UserTypeActivity.class);

            startActivity(i);
        }

    }

}
