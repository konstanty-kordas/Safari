package com.example.safari;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Prey extends Animal {
    private int current_water;
    private final int max_water;
    private boolean thirsty;
    private List<Directions> currentPath;
    private Location currentLocation;
    private Location currentDestination;
    private Thread thread;

    private boolean walking;
    private boolean safe;

    /**
     * @param x    coordinate X of the object
     * @param y    coordinate Y of the object
     * @param name Name of the Animal
     */
    public Prey(int x, int y, String name, Location currentLocation) {
        super(currentLocation.getX(), currentLocation.getY(), name);
        this.sprite.setFill(Color.RED);
        this.max_health = ThreadLocalRandom.current().nextInt(70, 100);
        this.current_health = this.max_health;
        this.max_energy = ThreadLocalRandom.current().nextInt(20, 30);
        this.current_energy = this.max_energy;
        this.max_water = ThreadLocalRandom.current().nextInt(20, 30);
        this.current_water = this.max_water;
        this.speed = ThreadLocalRandom.current().nextInt(15, 40);
        this.currentPath = new ArrayList<>();
        this.currentLocation = currentLocation;
        AnimalController.getInstance().addPrey(this);


    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void die() {
        this.thread.interrupt();
    }

    private void walk() {
        this.currentLocation = null;
        Directions move = this.currentPath.get(0);
        this.currentPath.remove(0);
        switch (move) {
            case DOWN:
                this.setY((this.getY() - 1));
                break;
            case LEFT:
                this.setX(this.getX() - 1);
                break;
            case RIGHT:
                this.setX(this.getX() + 1);
                break;
            case UP:
                this.setY(this.getY() + 1);
                break;
            default:
                throw new AssertionError();
        }
        if (this.currentPath.isEmpty()) {
            this.currentLocation = this.currentDestination;
            this.walking = false;
            this.currentDestination = null;
        }
    }


    private void dumpInfo() {
        String where;
        if (this.currentDestination == null) {
            where = "Nothing";
        } else {
            where = this.currentDestination.getLocationType().toString();
        }
        System.out.println("Animal " + this.name + "\n\t"
                + "Coordinates ( " + this.getX() + ", " + this.getY() + ")\n\t"
                + "Current health is: " + this.current_health + "/" + this.max_health + "\n\t"
                + "Current water level is: " + this.current_water + "/" + this.max_water + "\n\t"
                + "Current energy level is: " + this.current_energy + "/" + this.max_energy + "\n\t"
                + "It is now looking for " + where + "\n"
        );
    }

    public void takeDamage(int dmg) {
        this.current_health -= dmg;
    }

    private void navigate(LocationTypes locationType) {
        HashMap<Location, List<Directions>> knownRoutes = new HashMap<>(this.currentLocation.getRoutes());
        Queue<Location> q = new LinkedList<>();
        q.add(knownRoutes.entrySet().iterator().next().getKey());
        while (!q.isEmpty()) {
            Location c = q.poll();
            List<Directions> current = new ArrayList<>(knownRoutes.get(c));
            if (c.locationType == locationType) {
                this.currentPath = new ArrayList<>(current);
                this.currentDestination = c;
                this.walking = true;
                break;
            }
            if (c.locationType == LocationTypes.INTERSECTION) {
                HashMap<Location, List<Directions>> possibleRoutes = c.getRoutes();
                for (java.util.Map.Entry<Location, List<Directions>> entry : possibleRoutes.entrySet()) {
                    Location key = entry.getKey();
                    List<Directions> val = new ArrayList<>(entry.getValue());
                    if (!knownRoutes.containsKey(key)) {
                        List<Directions> temp = new ArrayList<>(current);
                        temp.addAll(val);
                        knownRoutes.put(key, new ArrayList<>(temp));
                        q.add(key);
                        temp.clear();
                    }
                }

            }

        }
    }

    public boolean isSafe() {
        return this.safe;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (this.current_health <= 0) {
                    System.out.println(this.name + " has died of Natural Causes");
                    break;
                }
                if (this.currentLocation != null && this.currentLocation.getLocationType() == LocationTypes.HIDEOUT && this.current_health < this.max_health) {
                    this.current_health++;
                    this.safe = true;
                } else if (this.current_water <= 0 || this.current_energy <= 0) {
                    this.current_health--;
                }
                if (this.current_energy > 0) {
                    this.current_energy--;
                }
                if (this.current_water > 0) {
                    this.current_water--;
                }

                if (this.current_water < 5) {
                    this.thirsty = true;
                }
                if (this.current_energy < 5) {
                    this.hungry = true;
                }
                if (this.currentLocation != null && this.currentLocation.getLocationType() == LocationTypes.FOOD && this.hungry) {
                    this.currentLocation.use();
                    this.current_energy = this.max_energy;
                    this.hungry = false;
                } else if (this.currentLocation != null && this.currentLocation.getLocationType() == LocationTypes.WATER && this.thirsty) {
                    this.currentLocation.use();
                    this.current_water = this.max_water;
                    this.thirsty = false;

                } else if (!this.walking) {
                    if (this.thirsty) {
                        this.navigate(LocationTypes.WATER);
                    } else if (this.hungry) {
                        this.navigate(LocationTypes.FOOD);
                    } else {
                        if (this.currentLocation != null && this.currentLocation.getLocationType() != LocationTypes.HIDEOUT) {
                            this.navigate(LocationTypes.HIDEOUT);
                        }
                    }
                } else {
                    this.safe = false;
                    this.walk();
                }

//                this.dumpInfo();
                Thread.sleep(500 - this.speed * 10L);

            } catch (InterruptedException e) {
                System.out.println(this.name + " has died");
                break;
            }
        }
        AnimalController.getInstance().removePrey(this);

    }


}
