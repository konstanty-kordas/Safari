package com.example.safari;

import javafx.scene.paint.Color;

public class Hideout extends Location{

    private int type;

    /**
     * @param x coordinate X of the object
     * @param y coordinate Y of the object
     */
    public Hideout(int x, int y) {
        super(x, y);
        this.locationType = LocationTypes.HIDEOUT;
        this.sprite.setFill(Color.BROWN);

    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

}
