package com.tootors.tootors.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.tootors.tootors.MainTootorsActivity;
import com.tootors.tootors.R;
import com.tootors.tootors.TootorActivity;
import com.tootors.tootors.TootorsFragment;
import com.tootors.tootors.client.model.Tootor;
import com.tootors.tootors.register.LoginActivity;
import com.tootors.tootors.register.ProfileActivity;
import com.tootors.tootors.register.SignUpActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, LocationListener {


    ArrayList<Long> ids;
    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    private GoogleMap map;
    private boolean permissionAllowed;
    private static final String TAG = MapActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment =
           (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this,"Give Permission for Location", Toast.LENGTH_LONG).show();
            permissionAllowed = false;

            return;
        }else
        {
            permissionAllowed = true;

        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
        ids = new ArrayList<Long>();
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this,"Give Permission for Location", Toast.LENGTH_LONG).show();
            permissionAllowed = false;

            return;
        }else
        {
            permissionAllowed = true;
        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        if (TootorsFragment.tootors != null && permissionAllowed == true) {

            for (int i = 0; i < TootorsFragment.tootors.size(); i++)
                if (TootorsFragment.tootors.get(i) != null) {
                    String address =
                            TootorsFragment.tootors.get(i).getStreet().replaceAll(" ", "+") + "+" +
                                    TootorsFragment.tootors.get(i).getCity().replaceAll(" ", "+") + "+" +
                                    TootorsFragment.tootors.get(i).getState().replaceAll(" ", "+") + "+" +
                                    TootorsFragment.tootors.get(i).getZip().replaceAll(" ", "+");


                    try {
                        map.addMarker(new MarkerOptions()
                                    .snippet(TootorsFragment.tootors.get(i).getName())
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.smallicon))
                                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                                    .position(getLatLng(address))
                                    .title(TootorsFragment.tootors.get(i).getFocus() + ""));
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    ids.add(TootorsFragment.tootors.get(i).getId());

                }

        }


        map.setOnInfoWindowClickListener(this);

    }



    public LatLng getLatLng(String address) throws ExecutionException, InterruptedException {

        FetchLocationTask f = new FetchLocationTask();
        f.execute(address);

        return  f.get();
    }



    @Override
    public void onInfoWindowClick(Marker marker) {

        String str = marker.getId();
        String s2 = str.replaceAll("m", "");

        int index = Integer.parseInt(s2);

        Tootor toot = new Tootor();

        for(int i =0; i < TootorsFragment.tootors.size(); i++)
        {
            if(ids.get(index) == TootorsFragment.tootors.get(i).getId())
            {
                toot = TootorsFragment.tootors.get(i);
            }
        }

        if(toot != null)
        {
            Intent profile = new Intent(this, ProfileActivity.class);
            String json = new Gson().toJson(toot);
            profile.putExtra(ProfileActivity.EXTRA, json);
            startActivity(profile);
        }

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public  class FetchLocationTask extends AsyncTask<String, Void, LatLng> {


        protected LatLng doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String key = "AIzaSyAHiwPCgDaf-AYm4oLfr04oGj0h373jfHM";
            String address = params[0];


            // Will contain the raw JSON response as a string.
            String list_JsonStr = null;

            try {

                final String MOVIE_BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json?";
                final String ADDRESS_PRAM = "address";
                final String API_KEY_PRAM = "key";

                Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(ADDRESS_PRAM, address)
                        .appendQueryParameter(API_KEY_PRAM, key)
                        .build();


                URL url = new URL(builtUri.toString());

                Log.v(TAG, "Build URI " + builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                list_JsonStr = buffer.toString();


            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }



            try {

                return setLatLng(list_JsonStr);

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        }


        protected LatLng setLatLng(String list_JsonStr) throws JSONException {

            LatLng p1 = null;

            // These are the names of the JSON objects that need to be extracted.
            final String OWM_RESULTS = "results";
            final String OWM_GEOMETRY = "geometry";
            final String OWM_LOCATION = "location";
            final String OWM_LAT = "lat";
            final String OWM_LNG = "lng";


            JSONObject list_Json = new JSONObject(list_JsonStr);
            JSONArray list_Array = list_Json.getJSONArray(OWM_RESULTS);

            String lat = "";
            String lng = "";

            for(int i = 0; i < list_Array.length(); i++) {

                JSONObject iteam = list_Array.getJSONObject(i);
                JSONObject geo = iteam.getJSONObject(OWM_GEOMETRY);
                JSONObject loc = geo.getJSONObject(OWM_LOCATION);

                lat = loc.getString(OWM_LAT);
                lng = loc.getString(OWM_LNG);

            }



            double  latDouble = Double.parseDouble(lat);
            double  lngDouble = Double.parseDouble(lng);


            p1 = new LatLng(latDouble, lngDouble);

            Log.v(TAG, "P1 entry: " + p1.toString());


            return  p1;

        }


        @Override
        protected void onPostExecute(LatLng result) {
            super.onPostExecute(result);
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