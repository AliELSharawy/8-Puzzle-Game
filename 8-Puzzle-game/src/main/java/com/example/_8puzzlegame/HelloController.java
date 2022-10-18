package com.example._8puzzlegame;

import com.example._8puzzlegame.puzzle.PuzzleGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class HelloController {

    private Stage stage;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to 8 Puzzle Game :)");
        PuzzleGame x = new PuzzleGame();
        x.startPlaying(stage);

    }

    public void getMainStage(Stage stage) {
        this.stage = stage;
    }
}