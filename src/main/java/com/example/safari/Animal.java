package com.example.safari;

public class Animal extends Base implements Runnable {

    protected String name;
    int current_health;
    int max_health;
    int speed;
    int current_energy;
    int max_energy;

    boolean hungry;

    /**
     * @param x coordinate X of the object
     * @param y coordinate Y of the object
     * @param name Name of the Animal
     */
    public Animal(int x, int y, String name) {
        super(x, y);
        this.name = name;
    }

    private void walk() {
    }

    public void run() {
    }
}
