package part1.ch08;

import java.util.Arrays;

public class AsymTiling {
    final int MOD = 1000000007;
    int[] cache;

    public AsymTiling(){
        cache = new int[101];
        Arrays.fill(cache, -1);
    }
    public int tiling(int width){
        if(width<=1) return 1;
        if(cache[width] != -1) return cache[width];

        cache[width] = (tiling(width-2)+tiling(width-1))%MOD;
        return cache[width];
    }
    public int asymTiling(int width){
        if(width%2==1){
            return (tiling(width)-tiling(width/2) + MOD)%MOD;
        }
        cache[width] = tiling(width);
        cache[width] = ((cache[width]-tiling(width/2))+MOD)%MOD;
        cache[width] = ((cache[width]-tiling(width/2-1))+MOD)%MOD;
        return cache[width];
    }
}
