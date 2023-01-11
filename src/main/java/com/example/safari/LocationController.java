package com.example.safari;

import java.util.HashSet;
import java.util.Set;

public class LocationController {
    private final Set<Location> locations = new HashSet<>();
    private static LocationController instance = null;
    private final Set<Hideout> hideouts = new HashSet<>();
    private final Set<WaterSource> waterSources = new HashSet<>();
    private final Set<FoodSource> foodSources = new HashSet<>();
    private final Set<Intersection> intersections = new HashSet<>();


    private LocationController() {
    }

    public static LocationController getInstance() {
        if (instance == null) {
            instance = new LocationController();
        }
        return instance;
    }

}
