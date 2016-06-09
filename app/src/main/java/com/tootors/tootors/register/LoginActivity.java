package com.tootors.tootors.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.EditText;

import com.tootors.tootors.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }



    public void onButtonClick(View v){

        if(v.getId() == R.id.login_button)
        {
            username = (EditText)findViewById(R.id.username_input);
            password = (EditText)findViewById(R.id.password_input);

            String u = username.getText().toString();
            String p = password.getText().toString();

            new LoginTask(this).execute(u, p);
        } else if(v.getId() == R.id.signup_button)
        {
            Intent i = new Intent(this, UserTypeActivity.class);
            startActivity(i);
        }

    }

}
