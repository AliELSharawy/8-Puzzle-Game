
public class Main {

    public static void main(String[] args) {
        int[] puzzle = {
                1, 2, 5,
                3, 4, 0,
                6, 7, 8
        };
        Node root = new Node(puzzle);

        BFS b = new BFS();
        b.bfs(root);


    }
}
