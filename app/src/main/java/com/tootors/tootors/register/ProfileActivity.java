package com.tootors.tootors.register;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.tootors.tootors.ImageTool;
import com.tootors.tootors.R;
import com.tootors.tootors.client.model.Tootor;

import java.util.Locale;


public class ProfileActivity extends AppCompatActivity {

    public static final String EXTRA = "user_json";

    private ProfileView pv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String user = null;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                user = extras.getString(EXTRA);
            }
        } else {
            user = (String) savedInstanceState.getSerializable(EXTRA);
        }

        setContentView(R.layout.activity_profile);

        if (user != null) {
            Tootor t = new Gson().fromJson(user, Tootor.class);

            pv = new ProfileView();

            pv.isTootor.setText(t.getIsTootor() ? "This person is a Tootor!" : "");
            pv.username.setText(t.getUsername());
            pv.email.setText(t.getEmail());
            pv.name.setText(t.getName());
            pv.phone.setText(t.getPhone());
            pv.price.setText(String.format(Locale.getDefault(), "$%.2f", t.getPrice()));
            pv.focus.setText(t.getPhone());
            pv.description.setText(t.getDescription());
            pv.loadPicture(t.getPicture());
            pv.loadVideo(t.getVideo());
        }

    }

    private class ProfileView {
        TextView isTootor = (TextView) findViewById(R.id.profile_is_tootor);
        TextView username = (TextView) findViewById(R.id.profile_username);
        TextView email = (TextView) findViewById(R.id.profile_email);
        TextView name = (TextView) findViewById(R.id.profile_name);
        TextView phone = (TextView) findViewById(R.id.profile_phone);
        TextView price = (TextView) findViewById(R.id.profile_price);
        //        TextView street = (TextView) findViewById(R.id.profile_stre12et);
//        TextView city = (TextView) findViewById(R.id.profile_city);
//        TextView state = (TextView) findViewById(R.id.profile_state);
//        TextView zip = (TextView) findViewById(R.id.profile_zip);
        TextView focus = (TextView) findViewById(R.id.profile_focus);
        TextView description = (TextView) findViewById(R.id.profile_description);
        ImageView picture = (ImageView) findViewById(R.id.profile_picture);
        VideoView video = (VideoView) findViewById(R.id.profile_video);
//        TextView created_at = (TextView) findViewById(R.id.profile_created_at);
//        TextView updated_at = (TextView) findViewById(R.id.profile_updated_at);
//        TextView visited_at = (TextView) findViewById(R.id.profile_visited_at);

        private void loadPicture(String base64) {
            if (picture != null) {
                Bitmap image = ImageTool.base64ToImage(base64);
                picture.setImageBitmap(image);
//                picture.setImageURI(Uri.parse(base64));
            }
        }

        private void loadVideo(String videoUri) {
            if (videoUri != null) {
                video.setVideoURI(Uri.parse(videoUri));
            }
        }
    }

}
