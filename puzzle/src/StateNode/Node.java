package StateNode;

import java.util.LinkedList;
import java.util.List;

public class Node {

    public int[] puzzle = new int[9];
    private int spaceIndex;
    private final List<Node> children;
    public Node parent;
    private int depth;

    public int getDepth() {
        return depth;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
        this.depth = parent.depth + 1;
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

    public Node(int[] p) {
        setPuzzle(p);
        setSpaceIndex(0);
        children = new LinkedList<>();
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

    public void move(int[] p, int i, char direction) {
        int offset = offset(direction);
        if (isValid(i + offset)) {
            //System.out.print(" " + direction + " ");
            int[] pc = swap(p, i, i + offset);
            Node child = new Node(pc);
            this.children.add(child);
            child.setParent(this);
        }
    }

    private int[] swap(int[] p, int i, int j) {
        // clone the array so not change in the original one
        int[] pc = p.clone();
        // swap the 2 numbers
        pc[i] = pc[j];
        pc[j] = 0;
        return pc;
    }

    private boolean isValid(int i) {
        return i >= 0 && i < puzzle.length;
    }

    // expand our algorithm


    public void moves() {
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == 0) {
                setSpaceIndex(i);
                break;
            }
        }

        // if 2 or 5 or 8 can't move to right which % 3 =2
        if (getSpaceIndex() % 3 < 2)
            move(puzzle, getSpaceIndex(), 'r');

        // if 0 or 3 or 6 can't move to left which % 3 =0
        if (getSpaceIndex() % 3 != 0)
            move(puzzle, getSpaceIndex(), 'l');

        // if 0 or 1 or 2 can't move to up
        if(getSpaceIndex() > 2)
            move(puzzle, getSpaceIndex(), 'u');

        // if 6 or 7 or 8 can't move to down
        if(getSpaceIndex() < 6)
            move(puzzle, getSpaceIndex(), 'd');

    }


    //goal test check if number in the list is not in ascending order this means it's not int the goal

    //0 1 2 3 4 5 6 7 8
    public boolean goalTest() {
        int prev = puzzle[0];
        for (int i = 1; i < puzzle.length; i++) {
            if (prev > puzzle[i])
                return false;
            prev = puzzle[i];
        }


        return true;
    }


}
