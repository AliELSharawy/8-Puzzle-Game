import SearchAgent.AStar;
import SearchAgent.Agent;
import SearchAgent.BFS;
import SearchAgent.DFS;

import java.awt.*;
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

     /*int[] puzzle = {
                1, 2, 5,
                3, 4, 0,
               6, 7, 8
        };*/

        /*int[] puzzle = {
                1, 4, 2,
                6, 5, 8,
                7, 3, 0
        };*/
      /*  int[] puzzle = {
                1, 2, 3,
                5, 6, 0,
                7, 8, 4
        };*/



        int[] puzzle = {
                1, 2, 3,
                4, 5, 6,
                8,7 , 0
        };



        Agent b = new BFS();
        b.solve(puzzle);



        //AStar a = new AStar();
       // a.setHeuristicFunction(euclideanDistance);
        //a.solve(puzzle);

    }
}
