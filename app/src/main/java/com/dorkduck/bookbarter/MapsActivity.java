package com.dorkduck.bookbarter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;

import com.example.sushant.myapplication.backend.myApi.MyApi;
import com.example.sushant.myapplication.backend.myApi.model.LocationObject;
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
import java.util.ArrayList;
import java.util.List;

import static com.dorkduck.bookbarter.DefaultFragment.MYPREF;

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
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        new LocationAsync().execute(new Pair<Context, String>(this, "Manfred"));


        SharedPreferences sharedPreferences = this.getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        String Lati = sharedPreferences.getString("Latitude","Latitude unknown");
        String Longi = sharedPreferences.getString("Longitude","Longitude unknown");

        try {
            plotSelf(Lati, Longi);
        }catch (NumberFormatException ne){
            ne.printStackTrace();
        }

    }

    void plotSelf(String Lati, String Longi){

        Double plotLat = Double.valueOf(Lati);
        Double plotLon = Double.valueOf(Longi);

        LatLng ourLocation = new LatLng(plotLat,plotLon);
        mMap.addMarker(new MarkerOptions().position(ourLocation).title("Our Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ourLocation));

    }


    class LocationAsync extends AsyncTask<Pair<Context, String>, Void, ArrayList<LocationObject>> {
        private MyApi myApiService = null;
        private Context context;

        @Override
        protected ArrayList<LocationObject> doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;
            String name = params[0].second;

            try {
                return (ArrayList<LocationObject>) myApiService.getLocation().execute().getList();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<LocationObject> result) {
            String show;
            LocationObject object1 = result.get(0);
            LocationObject object2 = result.get(0);


            LatLng location1 = new LatLng(object1.getLatitude(), object1.getLongitude());
            mMap.addMarker(new MarkerOptions().position(location1).title(object1.getName()));

            LatLng location2 = new LatLng(object2.getLatitude(),object1.getLongitude());
            mMap.addMarker(new MarkerOptions().position(location2).title(object2.getName()));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(location1));
        }
    }
}
