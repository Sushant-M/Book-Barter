package com.example.sushant.myapplication.backend;

/**
 * Created by sushant on 31/5/17.
 */

public class Location {
    private String name_private;
    private String longitude;
    private String latitude;

    public Location(String lat, String lon,String name){
        longitude = lon;
        latitude = lat;
        name_private = name;
    }

    public String getLongitude(){return longitude;}
    public String getLatitude(){return latitude;}
    public String getName_private(){return name_private;}
}
