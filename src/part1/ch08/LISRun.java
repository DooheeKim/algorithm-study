package part1.ch08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LISRun {
    public static void main(String[] args){
        List<Integer> arr = new ArrayList<>();
        Integer[] tmp = {3,2,1,7,5,4,2,6};
        arr.addAll(Arrays.asList(tmp));
        LIS lis = new LIS();
        System.out.println(lis.lis(Arrays.asList(tmp)));
        LIS lis2 = new LIS(new int[]{3,2,1,7,5,4,2,6});
        System.out.println(lis2.lis2(4));
    }
}
