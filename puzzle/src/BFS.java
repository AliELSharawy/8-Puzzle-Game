import java.util.*;

public class BFS extends Agent{
    @Override
    public List<Node> solve(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.add(root);
        boolean found = false;
        long start = System.currentTimeMillis();

        while (!queue.isEmpty() && !found) {
            Node front = queue.poll();
            visited.add(front);

            front.moves();
            System.out.println("here");

            for (Node child : front.getChildren()) {
                if (child.goalTest()) {
                    System.out.println("Goal Found");
                    found = true;
                    System.out.println();
                    goal = child;
                }
                if (!queue.contains(child) && !visited.contains(child))
                    queue.add(child);
            }
        }

        long executionTime = System.currentTimeMillis() - start;
        System.out.println("Time " + executionTime);
        return tracePath(goal);
    }
}
