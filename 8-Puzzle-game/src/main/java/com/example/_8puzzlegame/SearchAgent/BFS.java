package com.example._8puzzlegame.SearchAgent;
import com.example._8puzzlegame.StateNode.Node;

import java.util.*;

public class BFS extends Agent {
    @Override
    public List<Node> solve(int[] startState) {
        Node root = new Node(startState);
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(root);
        boolean found = false;
        long start = System.currentTimeMillis();

        // if the initial case is the goal
        if (root.goalTest()) {
            System.out.println("Goal Found It was the initial state ");
            found = true;
            goal = root;
        }

        while (!queue.isEmpty() && !found) {
            Node state = queue.poll();
            visited.add(Arrays.toString(state.puzzle));
            state.expand();

            this.maxDepth = Math.max(this.maxDepth, state.getDepth());

            for (Node child : state.getChildren()) {
                if (child.goalTest()) {
                    System.out.println("Goal Found");
                    found = true;
                    goal = child;
                }
                if (!visited.contains(Arrays.toString(child.puzzle)))
                    queue.add(child);
            }
        }

        long executionTime = System.currentTimeMillis() - start;
        System.out.println("Time taken by SearchAgent.BFS " + executionTime + " ms");
        return tracePath(goal);
    }
}
