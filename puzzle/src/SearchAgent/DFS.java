package SearchAgent;
import StateNode.Node;
import java.util.*;

public class DFS extends Agent {

    //Stack
    @Override
    public void solve(int[] startState) {
        Node root = new Node(startState);
        Stack<Node> stack = new Stack<>();
        Set<String> visited = new HashSet<>();


        stack.push(root);
        boolean found = false;
        long start = System.currentTimeMillis();
        if (root.goalTest()) {
            System.out.println("Goal Found as initial state");
            found = true;
            goal = root;
        }
        while (!stack.isEmpty() && !found) {
            Node state = stack.pop();
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
                    stack.add(child);
            }

        }
        long executionTime = System.currentTimeMillis() - start;


        //return tracePath(goal);
        if(goal !=null){
            tracePath(goal);
            System.out.println("Time taken by SearchAgent DFS " + executionTime + " ms");
        }
        else
            System.out.println(" Not solvable Example  !!!! ");
    }

}
