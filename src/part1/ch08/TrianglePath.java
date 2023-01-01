package part1.ch08;

import java.util.Arrays;

public class TrianglePath {
    int n;
    int[][] triangle;
    int cache[][];

    public TrianglePath(){
        this.triangle = new int[100][100];
        this.cache = new int[100][100];
        for(int i=0; i<this.cache.length;i++){
            Arrays.fill(this.cache[i],-1);
        }
    }

    int path(int y, int x){
        //기저사례
        if(y==n-1) return this.triangle[y][x];

        //메모이제이션
        int ret = cache[y][x];

        if(ret!=-1) return ret;
        cache[y][x] = Integer.max(path(y+1,x), path(y+1, x+1))+triangle[y][x];
        return cache[y][x];
    }
}
