/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.sem3.safari;

/**
 *
 * @author konstanty
 */
public class Plant extends Location {
    private int energy;
    private int timeToRegrowth;

    /**
     * @return the energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * @param energy the energy to set
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * @return the timeToRegrowth
     */
    public int getTimeToRegrowth() {
        return timeToRegrowth;
    }

    /**
     * @param timeToRegrowth the timeToRegrowth to set
     */
    public void setTimeToRegrowth(int timeToRegrowth) {
        this.timeToRegrowth = timeToRegrowth;
    }
}
