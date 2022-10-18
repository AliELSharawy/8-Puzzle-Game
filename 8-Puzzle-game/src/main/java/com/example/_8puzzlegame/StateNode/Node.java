package com.example._8puzzlegame.StateNode;

import java.util.LinkedList;
import java.util.List;

public class Node {

    public int puzzle;
    private int spaceIndex;
    private int depth;
    private final List<Node> children;
    public Node parent;

    public Node(int p) {
        setPuzzle(p);
        setSpaceIndex(0);
        children = new LinkedList<>();
        parent = null;
        depth = 0;
    }


    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setDepth(int d) {
        depth = d;
    }

    public int getDepth() {
        return depth;
    }

    public void setPuzzle(int puzzle) {
        this.puzzle = puzzle;
    }

    public List<Node> getChildren() {
        return children;
    }


    public int getSpaceIndex() {
        return spaceIndex;
    }

    public void setSpaceIndex(int spaceIndex) {
        this.spaceIndex = spaceIndex;
    }


    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass())
            return false;
        if (o == this)
            return true;

        Node node = (Node) o;
        return node.puzzle == this.puzzle;
    }

    public int offset(char c) {
        return switch (c) {
            case 'u' -> -3;
            case 'd' -> 3;
            case 'l' -> -1;
            case 'r' -> 1;
            default -> 0;
        };
    }

    public void insert(int newState) {
        Node child = new Node(newState);
        child.setDepth(depth + 1);
        this.children.add(child);
        child.setParent(this);
    }

    public int move(int p, int i, char direction) {
        int offset = offset(direction);
        if (isValid(direction)) {
            //System.out.print(" " + direction + " ");
            int pc = swap(p, i, i + offset);
            insert(pc);
            return 1;
        }
        return 0;
    }

    private int swap(int p, int i, int j) {
        // clone the array so not change in the original one
        StringBuilder pc = new StringBuilder(puzzleConvertor(p));
        // swap the 2 numbers
        pc.setCharAt(i,pc.charAt(j));
        pc.setCharAt(j,'0');
        return Integer.parseInt(pc.toString());
    }

    private boolean isValid(char direction) {

        return switch (direction) {
            // if 2 or 5 or 8 can't move to right which % 3 =2
            case 'r' -> getSpaceIndex() % 3 < 2;
            // if 0 or 3 or 6 can't move to left which % 3 =0
            case 'l' -> getSpaceIndex() % 3 != 0;
            // if 0 or 1 or 2 can't move to up
            case 'u' -> getSpaceIndex() > 2;
            // if 6 or 7 or 8 can't move to down
            case 'd' -> getSpaceIndex() < 6;

            default -> false;
        };
    }

    // expand our algorithm

    public int expand() {
        int noStates = 0;
        String puzzleStr = puzzleConvertor(puzzle);
        for (int i = 0; i < puzzleStr.length(); i++) {
            if (puzzleStr.charAt(i) == '0') {
                setSpaceIndex(i);
                break;
            }
        }

        noStates += move(puzzle, getSpaceIndex(), 'l');
        noStates += move(puzzle, getSpaceIndex(), 'r');
        noStates += move(puzzle, getSpaceIndex(), 'u');
        noStates += move(puzzle, getSpaceIndex(), 'd');

        return noStates;
    }


    //goal test check if number in the list is not in ascending order this means it's not int the goal
    //0 1 2 3 4 5 6 7 8
    public boolean goalTest() {
        return puzzle == 12345678;
    }

    public static String puzzleConvertor(int puzzle){
        String puzzleStr = Integer.toString(puzzle);
        if(puzzleStr.length() < 9)
            return '0' + puzzleStr;
        return puzzleStr;
    }


}
