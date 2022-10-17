package com.example._8puzzlegame.SearchAgent;

import com.example._8puzzlegame.StateNode.Node;
import java.awt.*;
import java.util.*;
import java.util.function.BiFunction;

public class AStar extends Agent {

    private BiFunction<Point, Point, Double> heuristicFunction;

    public AStar(BiFunction<Point, Point, Double> hFn) {
        heuristicFunction = hFn;
    }

    @Override
    public void solve(int startState) {
        Node root = new Node(startState);
        PriorityQueue<Node> pq = new PriorityQueue<>((Node n1, Node n2) ->
                Double.compare(n1.getDepth() + calculateHeuristic(n1.puzzle), n2.getDepth() + calculateHeuristic(n2.puzzle)));
        Set<Integer> visited = new HashSet<>();
        pq.add(root);
        long start = System.currentTimeMillis();
        while (!pq.isEmpty()) {
            Node state = pq.poll();
            this.maxDepth = Math.max(this.maxDepth, state.getDepth());
            if (state.goalTest()) {
                goal = state;
                break;
            }
            if (!visited.contains(state.puzzle)) {
                visited.add(state.puzzle);
                expand(state);
                pq.addAll(state.getChildren());
            }
        }
        long executionTime = System.currentTimeMillis() - start;
        //return tracePath(goal);
        if (goal != null) {
            tracePath(goal);
            System.out.println("Time taken by SearchAgent AStar " + executionTime + " ms");
        } else
            System.out.println(" Not solvable Example  !!!! ");

    }

    public void setHeuristicFunction(BiFunction<Point, Point, Double> hFn) {
        heuristicFunction = hFn;
    }

    public Double calculateHeuristic(int puzzle) {
        Double h = 0.00;
        String puzzleStr = Node.puzzleConvertor(puzzle);
        for (int i = 0; i < puzzleStr.length(); i++) {
            Point target = getPos(Integer.parseInt("" + puzzleStr.charAt(i)));
            Point current = getPos(i);
            h += heuristicFunction.apply(target, current);
        }
        return h;
    }

    private Point getPos(int i) {
        return new Point(i / 3, i % 3);
    }
}
