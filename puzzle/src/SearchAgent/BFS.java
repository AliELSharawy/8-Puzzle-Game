package SearchAgent;

import StateNode.Node;

import java.util.*;

public class BFS extends Agent {
    @Override
    public List<Node> solve(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Set<int[]> visited = new HashSet<>();

        queue.add(root);
        boolean found = false;
        long start = System.currentTimeMillis();

        while (!queue.isEmpty() && !found) {
            Node state = queue.poll();
            visited.add(state.puzzle);

            state.moves();
            // this.searchDepth = Math.max(this.searchDepth, state.getDepth());

            for (Node child : state.getChildren()) {
                if (child.goalTest()) {
                    System.out.println("Goal Found");
                    found = true;
                    goal = child;
                }
                if (!queue.contains(child) && !visited.contains(child.puzzle))
                    queue.add(child);
            }
        }

        long executionTime = System.currentTimeMillis() - start;
        System.out.println("Time taken by SearchAgent.BFS " + executionTime + " ms");
        return tracePath(goal);
    }
}
