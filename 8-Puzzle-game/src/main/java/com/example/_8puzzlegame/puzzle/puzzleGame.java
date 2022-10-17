package com.example._8puzzlegame.puzzle;

import com.example._8puzzlegame.SearchAgent.Agent;
import com.example._8puzzlegame.StateNode.Node;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

import java.awt.*;
import java.util.LinkedList;
import java.util.function.BiFunction;


public class puzzleGame {

    public static LinkedList<Node> puzz = new LinkedList<>();//contains the result
    public String p = "";// store the input array of the user
    private final GridPane gridPane;
    public Label label2;//max depth
    public Label label3;//no nodes
    public Label label4;//depth/cost
    public int noNodes = 0;


    public static final Background SKY_BLUE = new Background(new BackgroundFill(Color.BURLYWOOD, null, null));
    public static final Background White = new Background(new BackgroundFill(Color.WHITE, null, null));

    public int ctr = puzz.size() - 1;


    public static BiFunction<Point, Point, Double> euclideanDistance =
            (Point p1, Point p2) -> Math.hypot(p1.x - p2.x, p1.y - p2.y);
    public static BiFunction<Point, Point, Double> manhattanDistance =
            (Point p1, Point p2) -> Double.valueOf(Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y));



    public puzzleGame() {
        this.gridPane = new GridPane();

    }

    public void startPlaying(Stage stage) {
        drawMainWindow(stage);
    }

    private void drawMainWindow(Stage stage) {
        AnchorPane pane = new AnchorPane();
        int h = 600;
        int w = 700;


        drawGridPane();


        //text
        TextField text = new TextField("Enter puzzle");
        text.setLayoutX(500);
        text.setLayoutY(120);

        //this one
        Label label = new Label("Enter Your Puzzle in\nthat form 125340678 ");
        label.setLayoutY(75);
        label.setLayoutX(500);
        label.setTextFill(Color.web("#664d00"));
        label.setFont(new Font("Cambria", 15));


        //button of puzzle entrance
        Button puzzleEnter = new Button("Enter Puzzle :)");
        puzzleEnter.setLayoutX(530);
        puzzleEnter.setLayoutY(150);
        puzzleEnter.setMinSize(30, 30);
        puzzleEnter.setStyle("-fx-background-radius: 15px; -fx-background-color: #cc9900;");
        puzzleEnter.setOnMouseClicked(e -> solving(text.getText()));

        //
        Label label1 = new Label("Choose Search Method");
        label1.setLayoutX(500);
        label1.setLayoutY(280);
        label1.setTextFill(Color.web("#664d00"));
        label1.setFont(new Font("Cambria", 15));

        //dropdown list
        String[] algo = {"BFS", "DFS", "A* using Euclidean", "A* using Manhattan"};

        ComboBox<String> combo_box = new ComboBox<>(FXCollections.observableArrayList(algo));
        combo_box.setLayoutX(500);
        combo_box.setLayoutY(320);
        combo_box.getSelectionModel().selectFirst();
        //button of puzzle start solving
        Button start = new Button("Solve :)");
        start.setMinSize(30, 30);
        start.setStyle("-fx-background-radius: 15px; -fx-background-color: #cc9900;");
        start.setLayoutX(550);
        start.setLayoutY(360);

        start.setOnMouseClicked(e -> solveMethod(text.getText(), p, combo_box.getValue()));

        label2 = new Label();
        label2.setLayoutX(500);
        label2.setLayoutY(440);
        label2.setTextFill(Color.web("#664d00"));
        label2.setFont(new Font("Cambria", 18));

        label3 = new Label();
        label3.setLayoutX(500);
        label3.setLayoutY(460);
        label3.setTextFill(Color.web("#664d00"));
        label3.setFont(new Font("Cambria", 18));

        label4 = new Label();
        label4.setLayoutX(500);
        label4.setLayoutY(480);
        label4.setTextFill(Color.web("#664d00"));
        label4.setFont(new Font("Cambria", 18));

        //button of puzzle entrance
        Button next = new Button("Next");
        next.setLayoutX(370);
        next.setLayoutY(500);
        next.setMinSize(60, 60);
        next.setStyle("-fx-background-radius: 15px; -fx-background-color: #cc9900;");
        next.setOnMouseClicked(e -> updateCtr(-1));

        //button of puzzle entrance
        Button prev = new Button("Prev");
        prev.setLayoutX(70);
        prev.setLayoutY(500);
        prev.setMinSize(60, 60);
        prev.setStyle("-fx-background-radius: 15px; -fx-background-color: #cc9900;");
        prev.setOnMouseClicked(e -> updateCtr(1));

        //button of puzzle entrance
        Button hist = new Button("View Path");
        hist.setLayoutX(542);
        hist.setLayoutY(400);
        hist.setMinSize(30, 30);
        hist.setStyle("-fx-background-radius: 15px; -fx-background-color: #cc9900;");
        hist.setOnMouseClicked(e -> pathWindow());

        pane.getChildren().addAll(gridPane, label, text, puzzleEnter, start, label1, label2, label3, label4, combo_box, next, prev, hist);
        pane.setBackground(SKY_BLUE);
        Scene scene = new Scene(pane, w, h);
        stage.setScene(scene);
        stage.show();
    }

    public void pathWindow() {
        Stage stage = new Stage();
        ScrollPane pane = new ScrollPane();
        int h = 500;
        int w = 500;
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        for (int k = puzz.size() - 1; k >= 0; k--) {
            //GridPane grid = new GridPane();
            GridPane grid = createNewGridPane(60 + k * 130);
            for (int i = 0; i < 3; i++) {
                String puzzleRes = Node.puzzleConvertor(puzz.get(k).puzzle);
                for (int j = 0; j < 3; j++) {
                    if (puzzleRes.charAt(j + 3 * i) != '0') {
                        Label label = new Label(String.valueOf(puzzleRes.charAt(j + 3 * i)));
                        label.setTextFill(Color.ORANGE);
                        Font font = Font.font("Verdana", FontWeight.BOLD, 15);
                        label.setFont(font);
                        GridPane.setHalignment(label, HPos.CENTER);

                        grid.add(label, j, i);
                    }
                }
            }
            root.getChildren().add(grid);

        }
        pane.setContent(root);
        Scene scene = new Scene(pane, w, h);
        stage.setScene(scene);
        stage.show();
    }

    public GridPane createNewGridPane(int y) {
        GridPane grid = new GridPane();
        grid.getChildren().clear();
        grid.setDisable(false);
        grid.setLayoutY(y);
        // Properties for the GridPane
        grid.setPadding(new Insets(40, 40, 40, 70));
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), new CornerRadii(1), new Insets(30, 30, 30, 60))));


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // For the Background Colors
                StackPane field = new StackPane();
                field.setMinWidth(20);
                field.setMinHeight(20);
                field.setBackground(White);
                grid.add(field, i, j);
            }
        }
        return grid;
    }



    public void updateCtr(int go) {
        if (go == -1 && ctr >= 1) {
            ctr--;
            System.out.println(puzz.size());
            next(puzz.get(ctr));
        } else if (go == 1 && ctr <= puzz.size() - 2) {
            ctr++;
            next(puzz.get(ctr));
        }
    }

    public void next(Node n) {
        drawGridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String s="";
                if(Integer.toString(n.puzzle).length()==8){
                    s="0"+Integer.toString(n.puzzle);
                }else {
                    s=Integer.toString(n.puzzle);
                }
                if (s.charAt(j + 3 * i) != '0') {
                    updateUX(i, j, s.charAt(j + 3 * i));
                }
            }
        }
    }

    public void solving(String arr) {
        int[] arr1 = new int[9];
        p = arr;
        for (int i = 0; i < arr.length(); i++) {
            arr1[i] = (arr.charAt(i) - '0');
        }
        drawGridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr1[j + 3 * i] != 0) {
                    updateUX(i, j, arr1[j + 3 * i]);
                }
            }
        }
    }

    public void solveMethod(String arr1, String arr, String method) {
        solving(arr1);
        System.out.println(method);
        label2.setTextFill(Color.web("#664d00"));
        Agent agent = AgentFactory.agentMaker(method);
        agent.solve(Integer.parseInt(arr));
        puzz = agent.res;
        ctr = agent.res.size() - 1;
        noNodes = agent.getNodesExpanded();
        System.out.println(method);
        if (puzz.size() != 0) {
            System.out.println(puzz.get(0).getDepth());
            label2.setText("Max depth = " + agent.getMaxDepth());
            label3.setText("#no of nodes =" + noNodes);
            label4.setText("Cost/Depth =" + agent.getDepth());
        } else {
            label2.setTextFill(Color.web("#ff0000"));
            label2.setText(" Not solvable Example !!!! ");

        }

        noNodes = 0;
    }


    private void drawGridPane() {// clean and draw the grid again
        // Clear all
        gridPane.getChildren().clear();
        gridPane.setDisable(false);
        gridPane.setLayoutY(60);
        // Properties for the GridPane
        gridPane.setPadding(new Insets(40, 40, 40, 70));
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        gridPane.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), new CornerRadii(1), new Insets(30, 30, 30, 60))));


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


    }



    private void updateUX(int i, int j, int index) {
        ImageView imageView = getSpirit(index);
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