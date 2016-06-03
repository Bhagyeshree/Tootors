package com.tootors.tootors.register;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tootors.tootors.ImageTool;
import com.tootors.tootors.R;
import com.tootors.tootors.client.model.Tootor;

import android.text.util.Linkify;

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
            if (extras != null) {
                user = extras.getString(EXTRA);
            }
        } else {
            user = (String) savedInstanceState.getSerializable(EXTRA);
        }

        setContentView(R.layout.activity_profile);

        if (user != null) {
            final Tootor t = new Gson().fromJson(user, Tootor.class);

            pv = new ProfileView();


            pv.tootorInfo.setVisibility(t.getIsTootor() ? LinearLayout.VISIBLE : LinearLayout.GONE);
            pv.price.setText(String.format(getResources().getString(R.string.hourly_rate), t.getPrice()));

            pv.username.setText(String.format(getResources().getString(R.string.at_username), t.getUsername()));
            pv.name.setText(t.getName());
            pv.loadEmail(t.getEmail());

            pv.loadPhone(t.getPhone());
            Linkify.addLinks(pv.phone, Linkify.PHONE_NUMBERS);

            pv.focus.setText(t.getFocus());
            pv.description.setText(t.getDescription());
            pv.loadPicture(t.getPicture());

            pv.video.setText(Html.fromHtml(String.format("<a href=\"%s\">YouTube</a>", t.getVideo())));
            pv.video.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private class ProfileView {
        LinearLayout tootorInfo = (LinearLayout) findViewById(R.id.profile_tootor_info);

        TextView isTootor = (TextView) findViewById(R.id.profile_is_tootor);
        TextView username = (TextView) findViewById(R.id.profile_username);
        Button email = (Button) findViewById(R.id.profile_email);
        TextView name = (TextView) findViewById(R.id.profile_name);
        Button phone = (Button) findViewById(R.id.profile_phone);
        TextView price = (TextView) findViewById(R.id.profile_price);
        //        TextView street = (TextView) findViewById(R.id.profile_street);
//        TextView city = (TextView) findViewById(R.id.profile_city);
//        TextView state = (TextView) findViewById(R.id.profile_state);
//        TextView zip = (TextView) findViewById(R.id.profile_zip);
        TextView focus = (TextView) findViewById(R.id.profile_focus);
        TextView description = (TextView) findViewById(R.id.profile_description);
        ImageView picture = (ImageView) findViewById(R.id.profile_picture);
        Button video = (Button) findViewById(R.id.profile_video);
//        TextView created_at = (TextView) findViewById(R.id.profile_created_at);
//        TextView updated_at = (TextView) findViewById(R.id.profile_updated_at);
//        TextView visited_at = (TextView) findViewById(R.id.profile_visited_at);

        private void loadEmail(final String email) {
            pv.email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", email, null));
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Tootor Mail!");
                    intent.putExtra(Intent.EXTRA_TEXT, "Hi, I'd like to talk to you about Tootoring.");
                    startActivity(Intent.createChooser(intent, "Send Email"));
                }
            });
        }

        private void loadPhone(final String phone) {
            pv.phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phone, null));
                    intent.putExtra("sms_body", "Hi, I'd like to talk to you about Tootoring.");
                    startActivity(Intent.createChooser(intent, "Send Text"));
                }
            });
        }

        private void loadPicture(String base64) {
            if (base64 != null && base64.startsWith("data:image/")) {
                Bitmap image = ImageTool.base64ToImage(base64);
                picture.setImageBitmap(image);
            }
        }
    }

}
