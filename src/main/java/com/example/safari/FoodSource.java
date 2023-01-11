package com.example.safari;

import javafx.scene.paint.Color;

public class FoodSource extends Location implements Runnable {

    private final int max_berries;
    private int current_berries;
    private final int timeToRegrowth;

    /**
     * @param x coordinate X of the object
     * @param y coordinate Y of the object
     * @param max_berries maximum number of times it can be eaten before regrowth
     * @param timeToRegrowth time to regrow (in seconds)
     */
    public FoodSource(int x, int y, int max_berries, int timeToRegrowth) {
        super(x, y);
        this.timeToRegrowth = timeToRegrowth;
        this.max_berries = max_berries;
        this.current_berries = max_berries;
        this.locationType = LocationTypes.FOOD;
        this.sprite.setFill(Color.GREEN);

    }

    public void use() {
        this.current_berries--;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.current_berries = this.max_berries;
                Thread.sleep(timeToRegrowth* 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
