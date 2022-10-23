package com.example._8puzzlegame.SearchAgent;

import com.example._8puzzlegame.StateNode.Node;

import java.io.FileWriter;
import java.io.IOException;
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

        StringBuilder steps= new StringBuilder();
        for (int i = p.size() - 1; i >= 0; i--) {
            steps.append(Stringify(p.get(i)));
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

        FileWriter output;
        try {
            output = new FileWriter("path.txt");
            output.write(steps.toString());
            output.flush();
            output.close();
            System.out.println("Path is Printed to path.txt");
        } catch (IOException e) {
            System.out.println("Failed to save the path file.\n"
                    + "The path is:\n" + steps);
        }
    }
    public String Stringify(Node nod) {//For Printing In the File :)
        String st = Integer.toString(nod.puzzle);
        if(st.length() != 9) {
            st = '0' + st;
        }
        StringBuilder s = new StringBuilder();
        s.append("---------\n");
        for(int i=0;i<3;i++) {
            for(int j=3*i;j<3*i+3;j++) {
                s.append(st.charAt(j)).append(" ");
                if(j%3 != 2)
                    s.append("| ");
                else
                    s.append("\n");
            }
        }
        s.append("---------\n\n\n");
        return s.toString();
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
