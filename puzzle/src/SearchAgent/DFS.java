package SearchAgent;
import StateNode.Node;
import java.util.*;

public class DFS extends Agent {

    //Stack
    @Override
    public List<Node> solve(int[] startState) {
        Node root = new Node(startState);
        Stack<Node> stack = new Stack<>();
        Set<int[]> visited = new HashSet<>();

        stack.push(root);
        boolean found = false;
        long start = System.currentTimeMillis();

        while (!stack.isEmpty() && !found) {
            Node state = stack.pop();
            visited.add(state.puzzle);

            state.moves();
            this.maxDepth = Math.max(this.maxDepth, state.getDepth());

            for (Node child : state.getChildren()) {
                if (child.goalTest()) {
                    System.out.println("Goal Found");
                    found = true;
                    goal = child;
                }
                if (!stack.contains(child) && !visited.contains(child.puzzle))
                    stack.add(child);
            }
        }

        long executionTime = System.currentTimeMillis() - start;
        System.out.println("Time taken by SearchAgent DFS " + executionTime + " ms");
        return tracePath(goal);
    }

}
