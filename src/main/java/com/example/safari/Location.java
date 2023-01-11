package com.example.safari;

import java.util.HashMap;
import java.util.List;

public class Location extends Base {

    private String name;
    private int capacity;
    private final HashMap<Location, List<Directions>> routes;
    LocationTypes locationType = null;


    /**
     * @param x coordinate X of the object
     * @param y coordinate Y of the object
     */
    public Location(int x, int y) {
        super(x, y);
        routes = new HashMap<>();
    }

    /**
     * @param location
     * @param path
     */
    public void addRoute(Location location, List<Directions> path) {
        this.getRoutes().put(location, path);
    }

    public void use() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the locationType
     */
    public LocationTypes getLocationType() {
        return locationType;
    }

    /**
     * @return the routes
     */
    public HashMap<Location, List<Directions>> getRoutes() {
        return routes;
    }
}
