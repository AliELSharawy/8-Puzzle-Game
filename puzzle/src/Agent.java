import java.util.LinkedList;
import java.util.List;

public abstract class Agent {
    protected Node goal = null;
    public abstract List<Node> solve(Node root);

    public List<Node> tracePath(Node n) {
        LinkedList<Node> p = new LinkedList<>();
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
        return p;
    }

}
