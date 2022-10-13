import java.util.*;

public class BFS {

    public void bfs(Node root) {
        List<Node> path = new LinkedList<>();
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
                    tracePath(path, child);
                }
                if (!queue.contains(child) && !visited.contains(child))
                    queue.add(child);
            }
        }

        long executionTime = System.currentTimeMillis() - start;
        System.out.println("Time " + executionTime);

    }

    public void tracePath(List<Node> p, Node n) {
        Node curr = n;
        p.add(curr);

        while (curr.parent != null) {
            curr = curr.parent;
            p.add(curr);
        }
        for (int i = p.size()-1; i >=0 ; i--) {
            for (int j = 0; j < 9; j++) {
                System.out.print(p.get(i).puzzle[j] + " ");
                if (j % 3 == 2)
                    System.out.println();
            }
            System.out.println();
        }
    }

}
