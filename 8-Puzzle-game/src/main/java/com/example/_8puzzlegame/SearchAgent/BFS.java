package com.example._8puzzlegame.SearchAgent;

import com.example._8puzzlegame.StateNode.Node;

import java.util.*;

public class BFS extends Agent {
    @Override
    public void solve(int[] startState) {
        Node root = new Node(startState);
        Queue<Node> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Set<String> fringeSet = new HashSet<>();

        queue.add(root);
        fringeSet.add(Arrays.toString(startState));

        long start = System.currentTimeMillis();

        while (!queue.isEmpty() ) {
            Node state = queue.poll();
            visited.add(Arrays.toString(state.puzzle));
            fringeSet.remove(Arrays.toString(state.puzzle));

            // found goal when dequeue
            this.maxDepth = Math.max(this.maxDepth, state.getDepth());

            expand(state);

            for (Node child : state.getChildren()) {

                if (state.goalTest()) {
                    System.out.println("Goal Found");
                    goal = state;
                    break;
                }

                if (!visited.contains(Arrays.toString(child.puzzle)) && !fringeSet.contains(Arrays.toString(child.puzzle))) {
                    queue.add(child); //if not  visited add to closed set
                    fringeSet.add(Arrays.toString(child.puzzle));
                }

            }

            if (goal != null)
                break;

        }

        long executionTime = System.currentTimeMillis() - start;

        // return tracePath(goal);
        if (goal != null) {
            tracePath(goal);
            System.out.println("Time taken by SearchAgent BFS " + executionTime + " ms");
        } else
            System.out.println(" Not solvable Example  !!!! ");

    }
}
