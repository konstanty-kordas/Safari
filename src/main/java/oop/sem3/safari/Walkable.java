/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.sem3.safari;

/**
 *
 * @author konstanty
 */
public class Walkable extends Location {
    private int direction;
    private Walkable start;
    private Walkable stop;

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return the start
     */
    public Walkable getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Walkable start) {
        this.start = start;
    }

    /**
     * @return the stop
     */
    public Walkable getStop() {
        return stop;
    }

    /**
     * @param stop the stop to set
     */
    public void setStop(Walkable stop) {
        this.stop = stop;
    }
}
