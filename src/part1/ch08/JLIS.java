package part1.ch08;

import java.util.Arrays;
import java.util.List;

public class JLIS {
    private final long NEGINF = Long.MIN_VALUE;
    int n, m;
    int[][] cache;
    int[] A;
    int[] B;

    public JLIS(int[] A, int[] B) {
        this.A = A;
        this.B = B;
        n = A.length;
        m = B.length;
        cache = new int[n+1][m+1]; //-1부터 시작하게 해서 처음부터 자동으로 재귀호출 하게 하려고
        // cache 초기화
        for(int i=0; i<cache.length;i++){
            Arrays.fill(cache[i], -1);
        }
    }

    public int jlis(int indexA, int indexB){
        int ret = cache[indexA+1][indexB+1];
        if(ret != -1) return ret;

        ret = 0;
        long a = (indexA==-1? NEGINF:A[indexA]);
        long b = (indexB==-1? NEGINF:B[indexB]);
        long maxElement = Long.max(a, b);
        for(int nextA = indexA+1; nextA<n; ++nextA){
            if(maxElement<A[nextA]){
                ret = Integer.max(ret, jlis(nextA, indexB)+1);
                cache[indexA+1][indexB+1] = ret;
            }
        }
        for(int nextB=indexB+1; nextB<m; ++nextB){
            if(maxElement<B[nextB]){
                ret = Integer.max(ret, jlis(indexA, nextB)+1);
                cache[indexA+1][indexB+1] = ret;
            }
        }
        return ret;
    }
}
