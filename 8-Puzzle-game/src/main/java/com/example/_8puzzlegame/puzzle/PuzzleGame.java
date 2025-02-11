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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;


public class PuzzleGame {

    public static LinkedList<Node> board = new LinkedList<>();//contains the result
    public String p = "";// store the input array of the user
    private final GridPane gridPane;
    public Label label2;//max depth
    public Label label3;//no nodes
    public Label label4;//depth/cost
    public Label label5;//invalid input
    public Label label6;
    public Label label7;
    public int noNodes = 0;
    public int visitNode = 0;

    public static final Background SKY_BLUE = new Background(new BackgroundImage(new Image("file:src/main/resources/img.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1000, 1000, false, false, true, true)));
    public static final Background White = new Background(new BackgroundFill(Color.WHITE, null, null));

    public int ctr = board.size() - 1;


    public PuzzleGame() {
        this.gridPane = new GridPane();

    }

    public void startPlaying(Stage stage) {
        drawMainWindow(stage);
    }

    private void drawMainWindow(Stage stage) {
        AnchorPane pane = new AnchorPane();
        int h = 600;
        int w = 820;


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

        label6 = new Label();
        label6.setLayoutX(500);
        label6.setLayoutY(500);
        label6.setTextFill(Color.web("#664d00"));
        label6.setFont(new Font("Cambria", 18));

        label7 = new Label();
        label7.setLayoutX(500);
        label7.setLayoutY(520);
        label7.setTextFill(Color.web("#664d00"));
        label7.setFont(new Font("Cambria", 18));

        label5 = new Label();
        label5.setLayoutX(500);
        label5.setLayoutY(190);
        label5.setTextFill(Color.RED);
        label5.setFont(new Font("Cambria", 18));


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

        pane.getChildren().addAll(gridPane, label, text, puzzleEnter, start, label1, label2, label3, label4, label5, label6, label7, combo_box, next, prev, hist);
        pane.setBackground(SKY_BLUE);
        Scene scene = new Scene(pane, w, h);
        stage.setTitle("8 Puzzle Game :)");
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

        for (int k = board.size() - 1; k >= 0; k--) {
            //GridPane grid = new GridPane();
            GridPane grid = createNewGridPane(60 + k * 130);
            for (int i = 0; i < 3; i++) {
                String puzzleRes = Node.puzzleConvertor(board.get(k).puzzle);
                for (int j = 0; j < 3; j++) {
                    char c = puzzleRes.charAt(j + 3 * i);
                    if (c != '0') {
                        Label label = new Label(String.valueOf(c));
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
        if (!Objects.equals(p, "")) {
            if (go == -1 && ctr >= 1) {
                ctr--;
                System.out.println(board.size());
                next(board.get(ctr));
            } else if (go == 1 && ctr <= board.size() - 2) {
                ctr++;
                next(board.get(ctr));
            }
        }
    }

    public void next(Node n) {
        drawGridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String s;
                if (Integer.toString(n.puzzle).length() == 8) {
                    s = "0" + n.puzzle;
                } else {
                    s = Integer.toString(n.puzzle);
                }
                char c = s.charAt(j + 3 * i);
                if (c != '0') {
                    updateUX(i, j, c - '0');
                }
            }
        }
    }

    public boolean validation(String arr) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < arr.length(); i++) {
            if (arr.charAt(i) - '0' <= 8 && arr.charAt(i) - '0' >= 0) {
                s.add(arr.charAt(i) - '0');
            } else {
                return false;
            }
        }
        return s.size() == 9;
    }

    public void solving(String arr) {
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        board = new LinkedList<>();
        p = "";
        int k = 12345678;
        System.out.println("0" + k);
        if (validation(arr)) {
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
        } else {
            drawGridPane();
            label5.setText("INVALID INPUT!!!");
        }
    }

    public void solveMethod(String arr1, String arr, String method) {
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        if (!Objects.equals(p, "")) {
            solving(arr1);
            System.out.println(method);
            label2.setTextFill(Color.web("#664d00"));
            Agent agent = AgentFactory.agentMaker(method);
//            Node n = new Node(Integer.parseInt(arr));
//            if (n.isSolvableState()) {
            agent.solve(Integer.parseInt(arr));
            board = agent.res;
            ctr = agent.res.size() - 1;
            noNodes = agent.getNodesExpanded();
            visitNode = agent.getVisitedNodes();
            System.out.println(method);
            if (board.size() != 0) {//in case we want to test without checking solvable first
                System.out.println(board.get(0).getDepth());
                label2.setText("Max depth = " + agent.getMaxDepth());
                label3.setText("#no of nodes expanded = " + visitNode);
                label4.setText("Cost of path = " + agent.getDepth());
                label6.setText("Search Depth = " + agent.getDepth());
                label7.setText("Time =" + agent.getTime() + " µs");
            } else {
                label2.setTextFill(Color.web("#ff0000"));
                label2.setText(" Not solvable Example !!!! ");
                label3.setText("#no of nodes expanded = " + visitNode);
                label4.setText("Max depth = " + agent.getMaxDepth());
                label6.setText("Time =" + agent.getTime() + " µs");
            }
//            } else {
//                label2.setTextFill(Color.web("#ff0000"));
//                label2.setText(" Not solvable Example !!!! ");
//            }
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