import java.util.LinkedList;
import java.util.List;

public class Node {

    public int[] puzzle = new int[9];
    private int spaceIndex;
    private int depth;
    private final List<Node> children;
    Node parent;

    public Node(int[] p) {
        setPuzzle(p);
        setSpaceIndex(0);
        children = new LinkedList<>();
        parent = null;
        depth = 0;
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

    /*0 1 2
     3 4 5
     6 7 8 */
    // if 2 or 5 or 8 can't move to right which % 3 =2
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
            System.out.print(" " + direction + " ");
            int[] pc = swap(p, i,  i + offset);
            Node child = new Node(pc);
            child.setDepth(depth + 1);
            this.children.add(child);
            child.parent = this;
        }
    }

    private int[] swap(int[] p, int i, int j) {
        // clone the array so not change in the original one
        int[] pc = p.clone();
        // swap the 2 numbers
        int temp = pc[j];
        pc[j] = pc[i];
        pc[i] = temp;
        return pc;
    }

    private boolean isValid(int i) {
        return i >= 0 && i < puzzle.length;
    }

    // expand our algorithm
    public void moves() {
        for (int i = 0; i < puzzle.length; i++)
            if (puzzle[i] == 0)
                setSpaceIndex(i);

        move(puzzle, getSpaceIndex(), 'r');
        move(puzzle, getSpaceIndex(), 'l');
        move(puzzle, getSpaceIndex(), 'u');
        move(puzzle, getSpaceIndex(), 'd');

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
            }
        }

    }*/


}
