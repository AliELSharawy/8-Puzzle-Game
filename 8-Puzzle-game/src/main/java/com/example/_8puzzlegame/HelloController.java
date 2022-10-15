package com.example._8puzzlegame;
import com.example._8puzzlegame.SearchAgent.Agent;
import com.example._8puzzlegame.SearchAgent.BFS;
import com.example._8puzzlegame.SearchAgent.DFS;
import com.example._8puzzlegame.StateNode.Node;
import com.example._8puzzlegame.puzzle.puzzleGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.List;

public class HelloController {

    private Stage stage;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to 8 Puzzle Game :)");
        int[] puzzle = {
                1, 2, 5,
                3, 4, 0,
                6, 7, 8
        };
        Node root = new Node(puzzle);

        Agent b = new BFS();
        Agent d = new DFS();
        List<Node> sol = b.solve(root.puzzle);
        puzzleGame x = new puzzleGame();

        for (int i=b.res.size()-1;i>=0;i--){
            x.startPlaying(stage, b.res.get(i));
        }
    }

    public void getMainStage(Stage stage) {
        this.stage = stage;
    }
}