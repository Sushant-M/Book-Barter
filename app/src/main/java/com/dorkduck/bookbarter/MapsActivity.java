package com.dorkduck.bookbarter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String MYPREF = "myPreferences";
    SharedPreferences sharedPreferences;
    String User_Latitude;
    String User_Longitude;
    Float User_Lat;
    Float User_Lon;
    String username;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        User_Latitude = sharedPreferences.getString("Latitude","0");
        User_Longitude = sharedPreferences.getString("Longitude","0");
        User_Lat = Float.parseFloat(User_Latitude);
        User_Lon = Float.parseFloat(User_Longitude);
        username = sharedPreferences.getString("name","unknown");

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        LatLng user_location = new LatLng(User_Lat,User_Lon);
        mMap.addMarker(new MarkerOptions().position(user_location).title(username));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(user_location));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));


    }
}
