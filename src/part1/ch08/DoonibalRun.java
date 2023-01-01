package part1.ch08;

import java.util.ArrayList;
import java.util.List;

public class DoonibalRun {
    public static void main(String[] args){
        int[][] array={
                {0, 1, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 1, 1},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 0, 0}
        };
        Doonibal doonibal = new Doonibal(2,3, 6, array);
        List<Integer> path = new ArrayList<>();
        path.add(3);
        System.out.println(doonibal.search(path));
        System.out.println(doonibal.search2(3,0));
    }
}
