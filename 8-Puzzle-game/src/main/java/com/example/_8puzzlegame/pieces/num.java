package com.example._8puzzlegame.pieces;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class num {

    private ImageView imageView;

    public num() {
        setPieceSprite();
    }


    public void setPieceSprite() {
        String location = "file:src/main/resources/" + "1.png";
        Image image = new Image(location);
        imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
    }


    public ImageView getPieceSprite() {
        return imageView;
    }


}
