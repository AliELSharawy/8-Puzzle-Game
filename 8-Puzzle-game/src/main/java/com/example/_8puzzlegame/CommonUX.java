package com.example._8puzzlegame;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;



public class CommonUX {

    public static DropShadow shadow = new DropShadow();

    public static Button getButton(String title, int xPos, int yPos) {

        Button button = new Button(title);
        button.setLayoutX(xPos);
        button.setLayoutY(yPos);
        button.setCursor(Cursor.HAND);
        button.setFont(new Font("sans-serif", 19));
        button.setStyle("-fx-background-radius: 25px; -fx-background-color: #D46C4E;");
        button.setOnMouseEntered(e -> button.setEffect(shadow));
        button.setOnMouseExited(e -> button.setEffect(null));

        return button;
    }





}
