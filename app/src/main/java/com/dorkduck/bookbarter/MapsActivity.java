package com.dorkduck.bookbarter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sushant.myapplication.backend.myApi.MyApi;
import com.example.sushant.myapplication.backend.myApi.model.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        new LocationRetrieval().execute(this,null,null);
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
    public class LocationRetrieval extends AsyncTask<Context, Void, List<Location>> {

        private MyApi myApiService = null;
        private Context context;

        @Override
        protected List<com.example.sushant.myapplication.backend.myApi.model.Location> doInBackground(Context... params) {
            context = params[0];

            if (myApiService == null) {

                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null).setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                request.setDisableGZipContent(true);
                            }
                        });
                myApiService = builder.build();

                context = params[0];
                try {
                    return myApiService.sayHi().execute().getList();

                } catch (IOException ie) {
                    ie.getMessage();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Location> locations) {
            super.onPostExecute(locations);
            //Log.d("FUCK",String.valueOf(locations.size()));
            Location location1 = locations.get(0);
            Location location2 = locations.get(1);
            String user1 = location1.getNamePrivate();
            String user2 = location2.getNamePrivate();
            Double lat1 = Double.valueOf(location1.getLatitude());
            Double lat2 = Double.valueOf(location1.getLongitude());

            LatLng user1_plot = new LatLng(lat1, lat2);
            LatLng user2_plot = new LatLng(Double.valueOf(location2.getLatitude()), Double.valueOf(location2.getLongitude()));
            mMap.addMarker(new MarkerOptions().position(user1_plot).title(location1.getNamePrivate()));
            mMap.addMarker(new MarkerOptions().position(user2_plot).title(location2.getNamePrivate()));

        }
    }
}
