package com.example.safari;

import java.util.HashSet;
import java.util.Set;

public class AnimalController {
    private final Set<Prey> preySet = new HashSet<>();
    private static AnimalController instance = null;
    private final Set<Predator> predatorSet = new HashSet<>();

    private AnimalController() {
    }

    public static AnimalController getInstance() {
        if (instance == null) {
            instance = new AnimalController();
        }
        return instance;
    }


    public void addPrey(Prey prey) {
        instance.preySet.add(prey);
    }

    public void addPredator(Predator predator) {
        instance.predatorSet.add(predator);
    }

    public Set<Prey> getPreySet() {
        return instance.preySet;
    }

    public Set<Predator> getPredatorSet() {
        return instance.predatorSet;
    }

    public void removePrey(Prey prey) {
        instance.preySet.remove(prey);
    }

    public void removePredator(Predator predator) {
        instance.predatorSet.remove(predator);
    }

    public Prey getClosestPrey(Predator predator) {
        int x = predator.getX();
        int y = predator.getY();
        int min_distance = 100000000;
        int distance;
        Prey closest = null;
        for (Prey prey : instance.preySet) {
            if (prey.isSafe()) {
                continue;
            }
            distance = Math.abs(prey.getX() - x) + Math.abs(prey.getY() - y);
            if (distance < min_distance) {
                closest = prey;
                min_distance = distance;
            }
        }
        return closest;
    }

    public Animal findAnimal(int x, int y) {
        for (Prey prey : instance.preySet) {
            if (prey.getY() == y && prey.getX() == x) {
                return prey;
            }
        }
        for (Predator predator : instance.predatorSet) {
            if (predator.getX() == x && predator.getY() == y) {
                return predator;
            }
        }
        return null;
    }
}
