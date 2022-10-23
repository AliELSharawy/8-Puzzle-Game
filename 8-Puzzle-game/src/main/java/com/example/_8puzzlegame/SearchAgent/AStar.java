package com.example._8puzzlegame.SearchAgent;

import com.example._8puzzlegame.StateNode.Node;
import java.awt.*;
import java.util.*;
import java.util.function.BiFunction;

public class AStar extends Agent {

    private final BiFunction<Point, Point, Double> heuristicFunction;

    public AStar(BiFunction<Point, Point, Double> hFn) {
        heuristicFunction = hFn;
    }

    @Override
    public void solve(int startState) {
        Node root = new Node(startState);
        PriorityQueue<Node> pq = new PriorityQueue<>((Node n1, Node n2) ->
                Double.compare(n1.getDepth() + calculateHeuristic(n1.puzzle), n2.getDepth() + calculateHeuristic(n2.puzzle)));
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Double> stateCostMap = new HashMap<>();
        pq.add(root);
        stateCostMap.put(startState, calculateHeuristic(startState));

        long start = System.nanoTime();
        while (!pq.isEmpty()) {
            Node state = pq.poll();
            visited.add(state.puzzle);
            stateCostMap.remove(state.puzzle);
            this.maxDepth = Math.max(this.maxDepth, state.getDepth());
            if (state.goalTest()) {
                goal = state;
                break;
            }

            expand(state);

            for (Node child : state.getChildren()) {
                int puzzle = child.puzzle;
                double fn = child.getDepth() + calculateHeuristic(puzzle);
                if (!visited.contains(puzzle) && (!stateCostMap.containsKey(puzzle)
                        || stateCostMap.containsKey(puzzle) && fn < stateCostMap.get(puzzle))) {
                    pq.add(child);
                    stateCostMap.put(puzzle, fn);
                }
            }
        }
        long executionTime = (System.nanoTime() - start)/1000;
        setTime(executionTime);
        setVisitedNodes(visited.size());
        //return tracePath(goal);
        if (goal != null) {
            tracePath(goal);
            System.out.println("Time taken by SearchAgent AStar " + executionTime + " µs");
        } else {
            System.out.println(" Not solvable Example  !!!! ");
            System.out.println("Time taken by SearchAgent DFS " + executionTime + " µs");
            System.out.println("nodes expanded " + getNodesExpanded());
            System.out.println("nodes visited " + getVisitedNodes());
            System.out.println("Max Depth " + getMaxDepth());
        }

    }


    public Double calculateHeuristic(int puzzle) {
        Double h = 0.00;
        String puzzleStr = Node.puzzleConvertor(puzzle);
        for (int i = 0; i < puzzleStr.length(); i++) {
            //current state
            Point target = getPos(Integer.parseInt("" + puzzleStr.charAt(i)));
            //goal state
            Point current = getPos(i);
            h += heuristicFunction.apply(target, current);
        }
        return h;
    }

    private Point getPos(int i) {
        return new Point(i / 3, i % 3);
    }
}
