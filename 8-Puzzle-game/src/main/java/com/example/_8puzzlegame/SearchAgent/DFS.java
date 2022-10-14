package com.example._8puzzlegame.SearchAgent;
import com.example._8puzzlegame.StateNode.Node;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DFS extends Agent {

    public List<Node> solve(Node root) {
        Stack<Node> stack = new Stack<>();
        Set<int[]> visited = new HashSet<>();

        stack.push(root);
        boolean found = false;
        long start = System.currentTimeMillis();

        while (!stack.isEmpty() && !found) {

            Node state = stack.pop();
            visited.add(state.puzzle);
            state.moves();
            this.searchDepth = Math.max(this.searchDepth, state.getDepth());

            for (Node child : state.getChildren()) {
                if (child.goalTest()) {
                    System.out.println("Goal Found");
                    found = true;
                    goal = child;
                }
                if (!stack.contains(child) && !visited.contains(child.puzzle))
                    stack.push(child);
            }
        }

        long executionTime = System.currentTimeMillis() - start;
        System.out.println("Time taken by SearchAgent DFS " + executionTime + " ms");
        return tracePath(goal);
    }

}
