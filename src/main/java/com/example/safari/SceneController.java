package com.example.safari;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {


    public void beginSimulation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("simulation-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Safari");
        stage.setScene(scene);
        stage.show();
    }
}
