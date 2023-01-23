package part1.ch07;

public class QuadTreeRun {
    public static void main(String[] args){
       String compressedString = "xxwwwbxwxwbbbwwxxxwwbbbwwwwbb";
       QuadTree quadTree = new QuadTree(compressedString);
        System.out.println(quadTree.reverse());

    }
}
