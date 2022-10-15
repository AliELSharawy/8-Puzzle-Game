package com.example._8puzzlegame.puzzle;
import com.example._8puzzlegame.StateNode.Node;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedList;


public class puzzleGame {

    private final GridPane gridPane;
    private final DropShadow shadow;
    public static final Background SKY_BLUE = new Background(new BackgroundFill(Color.BURLYWOOD, null, null));
    public static final Background White = new Background(new BackgroundFill(Color.WHITE, null, null));

    private puzzleBoard board;
    public puzzleGame() {
        this.gridPane = new GridPane();
        this.shadow = new DropShadow();
        this.board = new puzzleBoard();
    }

    public void startPlaying(Stage stage, Node res) {
        drawMainWindowForXO(stage,res);
    }
    private void drawMainWindowForXO(Stage stage, Node res) {
        AnchorPane pane = new AnchorPane();
        int h = 500;
        int w = 500;


        drawGridPane(res);

        pane.getChildren().addAll(gridPane);

        pane.setBackground(SKY_BLUE);
        Scene scene = new Scene(pane, w, h);
        stage.setScene(scene);
        stage.show();
    }

    private void startNew() {
        Node res= new Node(new int[9]);
        drawGridPane(res);
        board = new puzzleBoard();
    }

    private void drawGridPane(Node res) {
        // Clear all
        gridPane.getChildren().clear();
        gridPane.setDisable(false);
        gridPane.setLayoutY(60);
        // Properties for the GridPane
        gridPane.setPadding(new Insets(40,40,40,70));
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        gridPane.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0), new CornerRadii(1), new Insets(30,30,30,60))));


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // For the Background Colors
                StackPane field = new StackPane();
                field.setMinWidth(120);
                field.setMinHeight(120);
                field.setBackground(White);
                gridPane.add(field, i, j);
            }
        }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (res.puzzle[j + 3 * i] != 0) {
                        updateUX(i, j, res.puzzle[j + 3 * i]);
                    }
                }


//            if(k!=0) {
//                gridPane.getChildren().clear();
//                gridPane.setDisable(false);
//                gridPane.setLayoutY(60);
//                // Properties for the GridPane
//                gridPane.setPadding(new Insets(40, 40, 40, 70));
//                gridPane.setHgap(1);
//                gridPane.setVgap(1);
//                for (int i = 0; i < 3; i++) {
//                    for (int j = 0; j < 3; j++) {
//                        // For the Background Colors
//                        StackPane field = new StackPane();
//                        field.setMinWidth(120);
//                        field.setMinHeight(120);
//                        field.setBackground(White);
//                        gridPane.add(field, i, j);
//                    }
//                }
//            }
        }

    }


    private void updateUX(int i, int j,int index) {
        ImageView imageView = getSpirit(index);
        System.out.println("dddd");
        gridPane.add(imageView, j, i);
    }
    private ImageView getSpirit(int i) {

        String location = String.format("file:src/main/resources/" + "%s.png", i);
        Image image = new Image(location);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(120);
        imageView.setFitHeight(100);
        return imageView;
    }
}
