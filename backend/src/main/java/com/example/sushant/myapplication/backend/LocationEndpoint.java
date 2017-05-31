package com.example.sushant.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "locationApi",
        version = "v1",
        resource = "location",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.sushant.example.com",
                ownerName = "backend.myapplication.sushant.example.com",
                packagePath = ""
        )
)
public class LocationEndpoint {

    private static final Logger logger = Logger.getLogger(LocationEndpoint.class.getName());

    /**
     * This method gets the <code>Location</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>Location</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getLocation")
    public Location getLocation(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getLocation method");
        return null;
    }

    /**
     * This inserts a new <code>Location</code> object.
     *
     * @param location The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertLocation")
    public Location insertLocation(Location location) {
        // TODO: Implement this function
        logger.info("Calling insertLocation method");
        return location;
    }
}