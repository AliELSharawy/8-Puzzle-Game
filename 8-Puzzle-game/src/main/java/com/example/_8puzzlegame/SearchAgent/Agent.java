package com.example._8puzzlegame.SearchAgent;
import com.example._8puzzlegame.StateNode.Node;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

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

    public abstract List<Node> solve(int[] startState);

    public void expand(Node state) {
        nodesExpanded += state.expand();
    }
    public List<Node> tracePath(Node n) {
        LinkedList<Node> p = new LinkedList<>();
        Node curr = n;
        p.add(curr);

        while (curr.parent != null) {
            curr = curr.parent;
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
        res=p;
        return p;
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

    public void setHeuristicFunction(BiFunction<Point, Point, Double> euclideanDistance) {
    }
}
