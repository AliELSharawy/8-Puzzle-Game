import SearchAgent.AStar;
import SearchAgent.Agent;
import SearchAgent.BFS;
import StateNode.Node;

import java.awt.*;
import java.util.List;
import java.util.function.BiFunction;

public class Main {
    public static BiFunction<Point, Point, Double> euclideanDistance =
            (Point p1, Point p2) -> Math.hypot(p1.x - p2.x, p1.y - p2.y);
    public static BiFunction<Point, Point, Double> manhattanDistance =
            (Point p1, Point p2) -> Double.valueOf(Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y));

    public static void main(String[] args) {
        /*int[] puzzle = {
                1, 2, 3,
                8, 0, 4,
                7, 6, 5
        };*/


//        int[] puzzle = {
//                1, 2, 5,
//                3, 4, 0,
//                6, 7, 8
//        };
//
//         Agent b = new BFS();
//         List<Node> sol = b.solve(puzzle);

        int[] puzzle = {
                1, 0, 2,
                7, 5, 4,
                8, 6, 3
        };

        AStar a = new AStar();
        a.setHeuristicFunction(euclideanDistance);
        a.solve(puzzle);

    }
}
