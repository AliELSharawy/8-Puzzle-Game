package com.example._8puzzlegame.SearchAgent;

import com.example._8puzzlegame.StateNode.Node;

import java.util.LinkedList;


import java.util.LinkedList;

public abstract class Agent {
    protected Node goal;
    public LinkedList<Node> res = new LinkedList<>();
    protected int maxDepth;



    public long time;
    protected int nodesExpanded;

    public Agent() {
        goal = null;
        maxDepth = 0;
        nodesExpanded = 0;
        time =0;
    }

    public abstract void solve(int startState);

    public void expand(Node state) {
        nodesExpanded += state.expand();
    }
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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
            String resultPuzzle = Node.puzzleConvertor(p.get(i).puzzle);
            for (int j = 0; j < resultPuzzle.length(); j++) {
                System.out.print(resultPuzzle.charAt(j) + " ");
                if (j % 3 == 2)
                    System.out.println();
            }
            System.out.println();
        }
        System.out.println("cost " + (p.size() - 1));
        System.out.println("maxDepth : " + getMaxDepth());
        System.out.println("nodes " + getNodesExpanded());
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
