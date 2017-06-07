/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.sushant.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.repackaged.org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.sushant.example.com",
                ownerName = "backend.myapplication.sushant.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name = "getLocation")
    public LocationBean sendLocation(){
        ArrayList<LocationBean.LocationObject> newList = new ArrayList<LocationBean.LocationObject>();
        LocationBean.LocationObject object1 = new LocationBean.LocationObject("Sushant",70.98,80);
        LocationBean.LocationObject object2 = new LocationBean.LocationObject("Kevin",80.14,82.34);
        newList.add(object1);
        newList.add(object2);

        LocationBean ourBean = new LocationBean();
        ourBean.setList(newList);
        return ourBean;
    }
}
