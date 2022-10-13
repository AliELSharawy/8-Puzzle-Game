import java.util.LinkedList;
import java.util.List;

public class Node {

    public int[] puzzle = new int[9];


    private int spaceIndex;
    private List<Node> children;
    Node parent;
    int COL = 3;


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

    /*0 1 2
     3 4 5
     6 7 8 */
    // if 2 or 5 or 8 can't move to right which % 3 =2
    public void moveToRight(int[] p, int i) {
        if (i % COL < 2) {
            System.out.print(" r ");
            // clone the array so not change in the original one
            int[] pc = p.clone();
            // swap the 2 numbers
            int temp = pc[i + 1];
            pc[i + 1] = pc[i];
            pc[i] = temp;
            Node child = new Node(pc);
            this.children.add(child);
            child.parent = this;
        }

    }

    // 0 or 3 or 6  which divisible by 3 can't move to left
    public void moveToLeft(int[] p, int i) {
        if (i % COL != 0) {
            System.out.print(" l ");
            // clone the array so not change in the original one
            int[] pc = p.clone();
            // swap the 2 numbers
            int temp = pc[i - 1];
            pc[i - 1] = pc[i];
            pc[i] = temp;
            Node child = new Node(pc);
            this.children.add(child);
            child.parent = this;
        }

    }

    public void moveUp(int[] p, int i) {
        if (i - COL > 0) {
            System.out.print(" u ");
            // clone the array so not change in the original one
            int[] pc = p.clone();
            // swap the 2 numbers
            int temp = pc[i - 3];
            pc[i - 3] = pc[i];
            pc[i] = temp;
            Node child = new Node(pc);
            this.children.add(child);
            child.parent = this;
        }

    }

    public void moveDown(int[] p, int i) {
        if (i + COL < puzzle.length) {
            System.out.print(" d ");
            // clone the array so not change in the original one
            int[] pc = p.clone();
            // swap the 2 numbers
            int temp = pc[i + 3];
            pc[i + 3] = pc[i];
            pc[i] = temp;
            Node child = new Node(pc);
            this.children.add(child);
            child.parent = this;
        }
    }

    // expand our algorithm
    public void moves() {
        for (int i = 0; i < puzzle.length; i++)
            if (puzzle[i] == 0)
                setSpaceIndex(i);


        moveToRight(puzzle, getSpaceIndex());
        moveToLeft(puzzle, getSpaceIndex());
        moveUp(puzzle, getSpaceIndex());
        moveDown(puzzle, getSpaceIndex());

    }

    /*this function checks that this state wasn't same as prev state
    public boolean is_same_puzzle(int[] p) {
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] != p[i])
                return false;
        }
        return true;
    }*/


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

    /* public void print(){
        for(Node child:getChildren()){
            for(int i=0;i<puzzle.length;i++){
                System.out.print(child.puzzle[i]+" ");
                if(i%3==2)
                    System.out.println();
            }
        }

    }*/


}
