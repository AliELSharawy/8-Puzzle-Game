package SearchAgent;

import StateNode.Node;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.BiFunction;

public class AStar extends Agent{

    private BiFunction<Point, Point, Double> heuristicFunction;
    @Override
    public List<Node> solve(int[] startState) {
        Node root = new Node(startState);
        PriorityQueue<Node> pq = new PriorityQueue<>((Node n1, Node n2) ->
                Double.compare(n1.getDepth() + calculateHeuristic(n1.puzzle), n2.getDepth() + calculateHeuristic(n2.puzzle)));
        Set<int[]> visited = new HashSet<>();
        pq.add(root);
        boolean found = false;

        while (!pq.isEmpty() && !found) {
            Node state = pq.poll();
            visited.add(state.puzzle);
            state.expand();

            for (Node child : state.getChildren()) {
                if (child.goalTest()) {
                    goal = child;
                    found = true;
                }
                if (!visited.contains(child.puzzle) && !pq.contains(child))
                    pq.add(child);
            }
        }
        return tracePath(goal);
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
