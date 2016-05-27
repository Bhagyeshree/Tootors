package com.tootors.tootors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText uname;
    private EditText pass1;
    private EditText pass2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }



    public  void onSignUpClick(View v)
    {
        if(v.getId()==R.id.Bsignupbutton)
        {
            name = (EditText)findViewById(R.id.TFname);
            email = (EditText)findViewById(R.id.TFemail);
            uname = (EditText)findViewById(R.id.TFuname);
            pass1 = (EditText)findViewById(R.id.TFpass1);
            pass2 = (EditText)findViewById(R.id.TFpass2);



            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(!pass1str.equals(pass2str))
            {
                //pop up message

                //Displaying pop up message we need TOST

                Toast pass = Toast.makeText(this, "Password Dont macth!", Toast.LENGTH_SHORT);
                pass.show();
            } else
            {
                //insert in details in datatbase

                Contact c = new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPass(pass1str);
                startActivity(new Intent(this, LoginActivity.class));
            }




        }
    }
}
