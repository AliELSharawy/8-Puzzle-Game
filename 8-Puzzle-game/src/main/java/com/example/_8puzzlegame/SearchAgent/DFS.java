package com.example._8puzzlegame.SearchAgent;

import com.example._8puzzlegame.StateNode.Node;

import java.util.*;

public class DFS extends Agent {
    //Stack
    @Override
    public void solve(int startState) {
        Node root = new Node(startState);
        Stack<Node> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> fringeElements = new HashSet<>();

        stack.push(root);
        fringeElements.add(root.puzzle);

        long start = System.nanoTime();

        while (!stack.isEmpty()) {
            Node state = stack.pop();
            fringeElements.remove(state.puzzle);
            visited.add(state.puzzle);

            this.maxDepth = Math.max(this.maxDepth, state.getDepth());
            // found goal when dequeue(removing from stack)
            if (state.goalTest()) {
                System.out.println("Goal Found");
                goal = state;
                break;
            }

            expand(state);

            for (Node child : state.getChildren()) {

                if (!visited.contains(child.puzzle) && !fringeElements.contains(child.puzzle)) {
                    stack.add(child);
                    fringeElements.add(child.puzzle);
                }
            }

        }
        long executionTime = (System.nanoTime() - start)/1000;
        setTime(executionTime);
        // return tracePath(goal);
        if (goal != null) {
            tracePath(goal);
            System.out.println("Time taken by SearchAgent DFS " + executionTime + " ms");
        } else
            System.out.println(" Not solvable Example  !!!! ");
    }
}