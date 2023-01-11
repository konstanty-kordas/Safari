package com.example.safari;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Base {
    private int x;
    private int y;
    Rectangle sprite;

    /**
     * @param x coordinate X of the object
     * @param y coordinate Y of the object
     */
    public Base(int x, int y) {
        this.x = x;
        this.y = y;
        dummy();
    }

    public int getX(){
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    private void dummy() {
        this.sprite = new Rectangle();
        this.sprite.setLayoutX(x*10);
        this.sprite.setLayoutY(y*10);
        this.sprite.setHeight(15);
        this.sprite.setWidth(15);
        this.sprite.setFill(Color.RED);
    }

    public Rectangle getSprite() {
        return this.sprite;
    }
}
