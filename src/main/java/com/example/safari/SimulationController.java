package com.example.safari;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {

    @FXML
    private Pane mySimulation;
    private final List<Base> locations = new ArrayList<>();

    private void begin() {
        Hideout h = new Hideout(10, 10);
        WaterSource w = new WaterSource(15, 15);
        FoodSource p = new FoodSource(5, 15, 10, 60);
        Intersection i = new Intersection(10, 15);
        List<Directions> d = new ArrayList<>();
        d.add(Directions.UP);
        d.add(Directions.UP);
        d.add(Directions.UP);
        d.add(Directions.UP);
        d.add(Directions.UP);
        h.addRoute(i, new ArrayList<>(d));
        List<Directions> d1 = new ArrayList<>();
        d1.add(Directions.DOWN);
        d1.add(Directions.DOWN);
        d1.add(Directions.DOWN);
        d1.add(Directions.DOWN);
        d1.add(Directions.DOWN);
        i.addRoute(h, new ArrayList<>(d1));
        List<Directions> d2 = new ArrayList<>();
        d2.add(Directions.LEFT);
        d2.add(Directions.LEFT);
        d2.add(Directions.LEFT);
        d2.add(Directions.LEFT);
        d2.add(Directions.LEFT);
        i.addRoute(p, new ArrayList<>(d2));
        w.addRoute(i, new ArrayList<>(d2));
        List<Directions> d3 = new ArrayList<>();
        d3.add(Directions.RIGHT);
        d3.add(Directions.RIGHT);
        d3.add(Directions.RIGHT);
        d3.add(Directions.RIGHT);
        d3.add(Directions.RIGHT);
        i.addRoute(w, new ArrayList<>(d3));
        p.addRoute(i, new ArrayList<>(d3));
//        LocationController.getInstance()
        locations.add(h);
        locations.add(i);
        locations.add(w);
        locations.add(p);
        addPrey(h);
        addPrey(h);
        addPredator();
        addPredator();
    }



    public void addPredator(){
        Random random = new Random();
        Predator predator = new Predator((random.nextInt(29)+1), (random.nextInt(29)+1), "Zdzisław");
        locations.add(predator);
        Thread t3 = new Thread(predator);
        t3.start();
    }
    public void addPrey(Hideout hideout){
        Prey prey = new Prey(0, 0, "Zenon", hideout);
        locations.add(prey);
        Thread thread = new Thread(prey);
        thread.start();
        prey.setThread(thread);

    }
    public void draw() {
        System.out.println(locations);
        for (Base location : locations) {
            mySimulation.getChildren().add(location.getSprite());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        begin();
        draw();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            for (int i = 0; i < locations.size(); i++) {
                if (AnimalController.getInstance().getPredatorSet().contains(locations.get(i)) || AnimalController.getInstance().getPreySet().contains(locations.get(i))) {
                    mySimulation.getChildren().get(i).setLayoutX(locations.get(i).getX()*10);
                    mySimulation.getChildren().get(i).setLayoutY(locations.get(i).getY()*10);
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void getAnim(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("EVENT");
        System.out.println((int) mouseEvent.getX()/10);
        System.out.println((int)mouseEvent.getY()/10);
        Animal a = AnimalController.getInstance().findAnimal((int) mouseEvent.getX()/10, (int) mouseEvent.getY()/10);
        if (a!=null){
            System.out.println(a);
        }
    }
}
