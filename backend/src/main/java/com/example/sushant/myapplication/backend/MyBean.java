package com.example.sushant.myapplication.backend;

import java.util.List;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;
    private List<Location> location_list;

    public String getData() {
        return myData;
    }
    public List<Location> getList(){return location_list;}

    public void setLocation_list(List<Location> our_list){ location_list = our_list;}
    public void setData(String data) {
        myData = data;
    }
}