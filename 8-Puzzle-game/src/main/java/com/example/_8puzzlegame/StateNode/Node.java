package com.example._8puzzlegame.StateNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Node {

    public int[] puzzle = new int[9];
    private int spaceIndex;
    private int depth;
    private final List<Node> children;
    public Node parent;

    public Node(int[] p) {
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

    public void setPuzzle(int[] puzzle) {
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
        return Arrays.equals(node.puzzle, this.puzzle);
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

    public void insert(int[] newState) {
        Node child = new Node(newState);
        child.setDepth(depth + 1);
        this.children.add(child);
        child.setParent(this);
    }

    public int move(int[] p, int i, char direction) {
        int offset = offset(direction);
        if (isValid(i, direction)) {
            //System.out.print(" " + direction + " ");
            int[] pc = swap(p, i, i + offset);
            insert(pc);
            return 1;
        }
        return 0;
    }

    private int[] swap(int[] p, int i, int j) {
        // clone the array so not change in the original one
        int[] pc = p.clone();
        // swap the 2 numbers
        pc[i] = pc[j];
        pc[j] = 0;
        return pc;
    }

    private boolean isValid(int i, char direction) {

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
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == 0) {
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
    public boolean goalTest(){
        int prev = puzzle[0];
        for(int i = 1; i < puzzle.length; i++){
            if(prev > puzzle[i])
                return false;
            prev = puzzle[i];
        }
        return true;
    }

    /*public void print(){
        for(Node child:getChildren()){
            for(int i=0;i<puzzle.length;i++){
                System.out.print(child.puzzle[i]+" ");
                if(i%3==2)
                    System.out.println();
      }*/

}
