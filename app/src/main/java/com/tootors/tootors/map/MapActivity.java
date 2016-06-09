package com.tootors.tootors.map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tootors.tootors.R;

import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    String a1  = "5151 State University Dr, Los Angeles, CA 90032";
    String a2 = "5154 State University Drive, Los Angeles, CA 90032";
    String a3  = "5175 State University Drive, Los Angeles, CA 90032";
    String a4  = "5155 State University Dr, Los Angeles, CA 90032";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap map) {

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                getLocationFromAddress(this,a1), 16));



        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.smallicon))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(getLocationFromAddress(this, a1))
                .title("Math Tutor"));


        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.smallicon))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(getLocationFromAddress(this, a2))
                .title("Fitness Tutor"));


        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.smallicon))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(getLocationFromAddress(this, a3))
                .title("CS Tutor"));

        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.smallicon))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(getLocationFromAddress(this, a4))
                .title("Bio Tutor"));


    }


    public LatLng getLocationFromAddress(Context context, String strAddress)
    {
        Geocoder coder= new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try
        {
            address = coder.getFromLocationName(strAddress, 5);
            if(address==null)
            {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p1;

    }
}