package com.example._8puzzlegame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    //static Stage window;
    @Override
    public void start(Stage stage) {
        Main.begin(stage);
    }

    public static void main(String[] args) {
        // Launch? 🙂️
        launch();
    }
// example 412305768
    //125340678
    //768243105
    //432650871
    public static void begin(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
            Parent root = loader.load();

            // To pass Stage to the Controller :)
            HelloController controller = loader.getController();
            controller.getMainStage(stage);


            Scene scene = new Scene(root,500,500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}