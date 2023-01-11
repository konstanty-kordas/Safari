package com.example.safari;

import javafx.scene.paint.Color;

public class WaterSource extends Location {
    private int water;

    /**
     * @param x coordinate X of the object
     * @param y coordinate Y of the object
     */
    public WaterSource(int x, int y) {
        super(x, y);
        this.locationType = LocationTypes.WATER;
        this.sprite.setFill(Color.DARKBLUE);
    }

    public void use() {
        this.water--;
    }
}
