package com.example._8puzzlegame.puzzle;
import com.example._8puzzlegame.CommonUX;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class puzzleGame {

    private final GridPane gridPane;
    private final DropShadow shadow;
    public static final Background SKY_BLUE = new Background(new BackgroundFill(Color.SKYBLUE, null, null));
    private puzzleBoard board;
    public puzzleGame() {
        this.gridPane = new GridPane();
        this.shadow = new DropShadow();
        this.board = new puzzleBoard();
    }

    public void startPlaying(Stage stage) {
        drawMainWindowForXO(stage);
    }
    private void drawMainWindowForXO(Stage stage) {
        AnchorPane pane = new AnchorPane();
        int h = 60 * 2 + 220 * 3 + 3 * 2;
        int w = 60 * 2 +220 * 3 + 3 * 2;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                updateUX(i+1,j+1,i);
            }
        }

        drawGridPane();
        int yPosFooter = h - 60;
        Button startNewGame = CommonUX.getButton("Start", w - 120, yPosFooter);
        startNewGame.setOnMouseClicked(e -> startNew());
        Button returnToMain = CommonUX.getButton("Menu", 40, yPosFooter);

        pane.getChildren().addAll(gridPane, returnToMain, startNewGame);

        Scene scene = new Scene(pane, w, h);
        stage.setScene(scene);
        stage.show();
    }

    private void startNew() {
        drawGridPane();
        board = new puzzleBoard();
    }

    private void drawGridPane() {
        // Clear all
        gridPane.getChildren().clear();
        gridPane.setDisable(false);
        gridPane.setLayoutY(60);
        // Properties for the GridPane
        gridPane.setPadding(new Insets(40,40,40,40));
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // For the Background Colors
                StackPane field = new StackPane();
                field.setMinWidth(120);
                field.setMinHeight(120);
                field.setBackground(SKY_BLUE);
                gridPane.add(field, i, j);
            }
        }

        gridPane.setOnMouseClicked(e -> {
            int i = (int) ((e.getY() - 40) / 120);
            int j = (int) ((e.getX() - 40) / 120);

        });
    }


    private void updateUX(int i, int j,int index) {
        ImageView imageView = getSpirit(index);
        gridPane.add(imageView, j, i);
    }
    private ImageView getSpirit(int i) {
        String location = String.format("file:src/main/resources/" + "%s.png", "img");
        Image image = new Image(location);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        return imageView;
    }
}
