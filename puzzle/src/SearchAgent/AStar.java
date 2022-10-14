package SearchAgent;

import StateNode.Node;

import java.awt.*;
import java.util.List;
import java.util.function.BiFunction;

public class AStar extends Agent{

    private BiFunction<Point, Point, Double> heuristicFunction;
    @Override
    public List<Node> solve(int[] startState) {
        return null;
    }

    public void setHeuristicFunction(BiFunction<Point, Point, Double> hFn) {
        heuristicFunction = hFn;
    }

    public Double calculateHeuristic(int[] puzzle) {
        Double h = 0.00;
        for (int i = 0; i < puzzle.length; i++) {
            Point target = getPos(puzzle[i]);
            Point current = getPos(i);
            h += heuristicFunction.apply(target, current);
        }
        return h;
    }

    private Point getPos(int i) {
        return new Point(i / 3, i % 3);
    }
}
