package part1.ch06;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Combination {
    static void pick(int n, Stack<Integer> picked, int toPick){
        if (toPick == 0){
            System.out.println(picked.toString());
            return;
        }

        int smallest = picked.size()==0?0:picked.peek()+1;
        for (int next = smallest; next<n; next++){
            picked.add(next);
            pick(n, picked, toPick-1);
            picked.pop();
        }
    }
    public static void main(String[] args){
        pick(5, new Stack<>(), 3);
    }
}
