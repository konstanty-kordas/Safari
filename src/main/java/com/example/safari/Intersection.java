package com.example.safari;

import javafx.scene.paint.Color;

public class Intersection extends Location {
    /**
     * @param x coordinate X of the object
     * @param y coordinate Y of the object
     */
    public Intersection(int x, int y) {
        super(x, y);
        this.locationType = LocationTypes.INTERSECTION;
        this.sprite.setFill(Color.GRAY);
    }

    public void log() {
        System.out.println(this.getRoutes());
    }
}
