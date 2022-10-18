package SearchAgent;

import StateNode.Node;

import java.util.LinkedList;


public abstract class Agent {
    protected Node goal;
    public LinkedList<Node> res = new LinkedList<>();
    protected int maxDepth;

    protected int nodesExpanded;

    public Agent() {
        goal = null;
        maxDepth = 0;
        nodesExpanded = 0;
    }

    public abstract void solve(int[] startState);

    public void expand(Node state) {
        nodesExpanded += state.expand();
    }

    public void tracePath(Node n) {
        LinkedList<Node> p = new LinkedList<>();
        Node curr = n;
        p.add(curr);

        while (curr.getParent() != null) {
            curr = curr.getParent();
            p.add(curr);
        }

        for (int i = p.size() - 1; i >= 0; i--) {
            for (int j = 0; j < 9; j++) {
                System.out.print(p.get(i).puzzle[j] + " ");
                if (j % 3 == 2)
                    System.out.println();
            }
            System.out.println();
        }
        System.out.println("cost " + (p.size() - 1));
        System.out.println("maxDepth : " + getMaxDepth());
        System.out.println("nodes" + getNodesExpanded());
        res = p;

    }

    public int getDepth() {
        return goal.getDepth();
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
    }

}
