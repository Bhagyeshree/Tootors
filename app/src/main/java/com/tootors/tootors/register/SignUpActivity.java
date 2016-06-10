package com.tootors.tootors.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.tootors.tootors.R;
import com.tootors.tootors.TootorActivity;
import com.tootors.tootors.client.model.Tootor;
import com.tootors.tootors.map.MapActivity;

public class SignUpActivity extends AppCompatActivity {


    private class SignUpView {
        private Switch isTootor;
        private EditText username;
        private EditText email;
        private EditText email2;
        private EditText name;
        private EditText pass1;
        private EditText pass2;
        private EditText phone;
        private EditText price;
        private EditText street;
        private EditText city;
        private EditText state;
        private EditText zip;
        private EditText focus;
        private EditText description;
        private EditText picture;
        private EditText video;

        public SignUpView() {
            isTootor = (Switch) findViewById(R.id.is_tootor_input_signup);

            username = (EditText) findViewById(R.id.username_input_signup);
            email = (EditText) findViewById(R.id.email_input_signup);
            email2 = (EditText) findViewById(R.id.email2_input_signup);
            name = (EditText) findViewById(R.id.fullname_input_signup);

            pass1 = (EditText) findViewById(R.id.password_input_signup);
            pass2 = (EditText) findViewById(R.id.password2_input_signup);

            phone = (EditText) findViewById(R.id.phone_input_signup);
            price = (EditText) findViewById(R.id.price_input_signup);
            street = (EditText) findViewById(R.id.streetaddress_input_signup);
            city = (EditText) findViewById(R.id.city_input_signup);
            state = (EditText) findViewById(R.id.state_input_signup);
            zip = (EditText) findViewById(R.id.zip_input_signup);
            focus = (EditText) findViewById(R.id.focus_input_signup);
            description = (EditText) findViewById(R.id.description_input_signup);
//            picture = (EditText) findViewById(R.id.picture_input_signup);
            video = (EditText) findViewById(R.id.video_input_signup);
        }

        public boolean passwordsMatch() {
            return pass1.getText().toString().equals(pass2.getText().toString());
        }

        public boolean emailsMatch() {
            return email.getText().toString().equals(email2.getText().toString());
        }

        /** Generates a Tootor from inputs */
        public Tootor generateTootor() {
            Tootor t = new Tootor();

            t.setIsTootor(isTootor.isActivated());
            t.setUsername(username.getText().toString());
            t.setEmail(email.getText().toString());
            t.setPassword(pass1.getText().toString());
            t.setName(name.getText().toString());
            t.setPhone(phone.getText().toString());

            try {
                t.setPrice(Double.parseDouble(price.getText().toString()));
            } catch (NumberFormatException e) {
                t.setPrice(0d);
            }

            t.setStreet(street.getText().toString());
            t.setCity(city.getText().toString());
            t.setState(state.getText().toString());
            t.setZip(zip.getText().toString());
            t.setFocus(focus.getText().toString());
            t.setDescription(description.getText().toString());

//            t.setPicture(picture.getText().toString());
            t.setPicture("No Picture");

            String videoLink = video.getText().toString();
            if (videoLink.startsWith("http"))
                t.setVideo(videoLink);
            else
                t.setVideo("No Video");

            return t;
        }
    }

    private void toast(String message) {
        Toast pass = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        pass.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onSignUpClick(View v) {
        if (v.getId() == R.id.button_signup) {

            SignUpView sv = new SignUpView();

            if (!sv.passwordsMatch()) toast("Passwords Don't Match!");
            else if (!sv.emailsMatch()) toast("Emails Don't Match!");
            else {
                // send details to DB
                Tootor tootor = sv.generateTootor();
                new SignUpTask(SignUpActivity.this, tootor).execute();
            }
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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

            startActivity(new Intent(this, TootorActivity.class));
            return true;
        }
        //It will return item

        return super.onOptionsItemSelected(item);
    }

}
