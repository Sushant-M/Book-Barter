package com.dorkduck.bookbarter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import com.example.sushant.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by sushant on 31/5/17.
 */

public class LocationRetrieval extends AsyncTask<Context,Void,List<com.example.sushant.myapplication.backend.myApi.model.Location>> {

    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected List<com.example.sushant.myapplication.backend.myApi.model.Location> doInBackground(Context... params) {
        context = params[0];

        if (myApiService == null){

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
            try{
                return myApiService.sayHi().execute().getList();
            }
            catch (IOException ie){
                ie.getMessage();
            }
        }

        return null;
    }
}
