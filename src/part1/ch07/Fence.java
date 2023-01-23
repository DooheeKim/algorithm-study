package part1.ch07;

import java.util.List;

public class Fence {
    List<Integer> h;

    int solve(int left, int right){
        if(left==right) return h.get(left);

        int mid=(left+right)/2;
        int ret = Integer.max(solve(left, mid), solve(mid, right));

        int lo = mid, hi = mid+1;
        int height = Integer.min(h.get(lo), h.get(hi));
        ret = Integer.max(ret, height*2);

        while(left<lo || hi<right){
            if(hi<right && (lo==left || h.get(hi+1)>h.get(left-1))){
                ++hi;
                height = Integer.min(height, h.get(hi));
            }else{
                --lo;
                height = Integer.min(height, h.get(lo));
            }
            ret = Integer.max(ret, height*(hi-lo+1));
        }
        return ret;
    }
}
