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
        // used this set to check if it exists in stack or not as set searches in O(1)
        Set<Integer> fringeElements = new HashSet<>();

        stack.push(root);
        fringeElements.add(root.puzzle);

        long start = System.nanoTime();

        while (!stack.isEmpty()) {
            //get the state from the stack
            Node state = stack.pop();
            fringeElements.remove(state.puzzle);
            visited.add(state.puzzle);
            // updating the max depth
            this.maxDepth = Math.max(this.maxDepth, state.getDepth());
            // found goal when dequeue(removing from stack)
            if (state.goalTest()) {
                System.out.println("Goal Found");
                goal = state;
                break;
            }
            // expand the state to see its child's moves
            expand(state);

            for (Node child : state.getChildren()) {
                //looping through the state's children
                // if it's child not visited before or not added to stack we add it to the stack
                if (!visited.contains(child.puzzle) && !fringeElements.contains(child.puzzle)) {
                    stack.add(child);
                    fringeElements.add(child.puzzle);
                }
            }

        }
        long executionTime = (System.nanoTime() - start) / 1000;
        setTime(executionTime);
        setVisitedNodes(visited.size());

        if (goal != null) {
            tracePath(goal);
            System.out.println("Time taken by SearchAgent DFS " + executionTime + " µs");
        } else {
            System.out.println(" Not solvable Example  !!!! ");
            System.out.println("Time taken by SearchAgent DFS " + executionTime + " µs");
            System.out.println("nodes expanded " + getNodesExpanded());
            System.out.println("nodes visited " + getVisitedNodes());
            System.out.println("Max Depth " + getMaxDepth());

        }
    }
}