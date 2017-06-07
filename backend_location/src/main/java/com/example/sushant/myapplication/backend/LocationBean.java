package com.example.sushant.myapplication.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushant on 7/6/17.
 */

public class LocationBean {

    private ArrayList<LocationObject> ourList;

    public ArrayList<LocationObject> getList(){
        return ourList;
    }

    public void setList(ArrayList<LocationObject> theList){
        ourList = theList;
    }


    public static class LocationObject{
        private String name;
        private double latitude;
        private double longitude;

        LocationObject(String a, double b, double c){
            name = a;
            latitude = b;
            longitude = c;
        }

        public String getName(){return name;}
        public double getLatitude(){return latitude;}
        public double getLongitude(){return longitude;}
    }
}
