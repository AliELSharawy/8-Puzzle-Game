import SearchAgent.Agent;
import SearchAgent.BFS;
import SearchAgent.DFS;
import StateNode.Node;

import java.util.List;
public class Main {

    public static void main(String[] args) {
        /*int[] puzzle = {
                1, 2, 3,
                8, 0, 4,
                7, 6, 5
        };*/

        int[] puzzle = {
                1, 2, 5,
                3, 4, 0,
                6, 7, 8
        };
        Node root = new Node(puzzle);

        Agent b = new DFS();
        List<Node> sol = b.solve(root);

    }
}
