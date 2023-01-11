package com.example.safari;

import javafx.scene.paint.Color;

import java.util.concurrent.ThreadLocalRandom;

public class Predator extends Animal {

    private final int attack;

    /**
     * @param x    coordinate X of the object
     * @param y    coordinate Y of the object
     * @param name Name of the Animal
     */
    public Predator(int x, int y, String name) {
        super(x, y, name);
        this.sprite.setFill(Color.PURPLE);

        this.max_health = ThreadLocalRandom.current().nextInt(70, 100);
        this.attack = ThreadLocalRandom.current().nextInt(10, 20);
        this.current_health = this.max_health;
        this.max_energy = ThreadLocalRandom.current().nextInt(20, 30);
        this.current_energy = this.max_energy;
        this.speed = ThreadLocalRandom.current().nextInt(15, 40);
        AnimalController.getInstance().addPredator(this);

    }

    private void walk() {
        int x = this.getX() + (ThreadLocalRandom.current().nextInt(-1, 1));
        int y = this.getY() + (ThreadLocalRandom.current().nextInt(-1, 1));
        if (x > 0 && x < 100) {
            this.setX(x);
        }
        if (y > 0 && y < 100) {
            this.setY(y);
        }

    }

    private void hunt() {
        Prey closestAnimal = AnimalController.getInstance().getClosestPrey(this);
        if (closestAnimal == null) {
            this.walk();
            return;
        }
        if (closestAnimal.getX() == this.getX() && closestAnimal.getY() == this.getY()) {
            closestAnimal.takeDamage(this.attack);
            this.current_energy = this.max_energy;
        }
        if (closestAnimal.getX() > this.getX()) {
            this.setX(this.getX() + 1);
        } else if (closestAnimal.getX() < this.getX()) {
            this.setX(this.getX() - 1);
        }
        if (closestAnimal.getY() > this.getY()) {
            this.setX(this.getY() + 1);
        } else if (closestAnimal.getY() < this.getY()) {
            this.setX(this.getY() - 1);
        }

    }

    private void dumpInfo() {

        System.out.println("Animal " + this.name + "\n\t"
                        + "Coordinates ( " + this.getX() + ", " + this.getY() + ")\n\t"
                        + "Current health is: " + this.current_health + "/" + this.max_health + "\n\t"
                        + "Current energy level is: " + this.current_energy + "/" + this.max_energy + "\n\t"
//                + "The closest animal is: " + this.closestAnimal.name
        );
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (this.current_health <= 0) {
                    System.out.println(this.name + " has died of Natural Causes");
                    break;
                }
                if (this.current_energy <= 0) {
                    this.current_health--;
                }
                if (this.current_energy > 0) {
                    this.current_energy--;
                }

                if (this.current_energy < 5) {
                    this.hungry = true;
                }
                if (this.hungry) {
                    this.hunt();
                } else {
                    this.walk();

                }
//                this.dumpInfo();
                Thread.sleep(500 - this.speed * 10L);
            } catch (InterruptedException e) {
                System.out.println(this.name + " has died");
                break;
            }
        }
        AnimalController.getInstance().removePredator(this);


    }
}
