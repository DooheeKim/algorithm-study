package part1.ch08;

public class JLISRun {
    public static void main(String[] args){
        int[] A = {10, 20, 30, 1, 2};
        int[] B = {10, 20, 30};
        JLIS jlis = new JLIS(A, B);

        System.out.println(jlis.jlis(-1,-1));
    }
}
