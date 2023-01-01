package part1.ch08;

import java.util.Arrays;

public class Quantization {
    private final int INF = 999999999;
    int[] A; //양자화해야할 수열, 정렬된 상태
    int pSum[]; //부분합. pSum[i] = A[0]+....+A[i]
    int pSqSum[]; // 제곱의 부분합. pSqSum[i] = A[0]^2 + ... + A[i]^2

    int cache[][];

    public Quantization(int[] A){
        this.A = A;
        Arrays.sort(this.A); // 들어온 행렬 정렬
        pSum = new int[A.length];
        pSqSum = new int[A.length];
        cache = new int[101][11];

        //pSum과 pSqSum 채우기
        pSum[0] = A[0];
        pSqSum[0] = A[0]*A[0];
        for(int i=1; i<A.length; i++){
            pSum[i] = pSum[i-1] + A[i];
            pSqSum[i] = pSqSum[i-1]+A[i]*A[i];
        }

        //cache 채우기
        for(int i=0; i< cache.length; i++){
            Arrays.fill(cache[i], -1);
        }
    }

    int minError(int lo, int hi){
        //lo이상 hi  미만.. 1이상 2미만이면 pSum[1]-pSum[0]
        int sum = pSum[hi-1] - (lo==0?0:pSum[lo-1]);
        int sqSum = pSqSum[hi-1] - (lo==0?0:pSqSum[lo-1]);

        int m = (int)(0.5 + ((double)sum)/(hi-lo));
        int ret = sqSum- 2*m*sum + m*m*(hi-lo);
        return ret;
    }

    int quantize(int from, int parts){
        // 기저사례: 모든 숫자를 다 양자화했을때
        if(from==this.A.length) return 0;

        // 기저사례: 숫자는 아직 남았는데 더 묶을 수 없을때
        if(parts==0) return INF;

        int ret = cache[from][parts];
        if(ret != -1) return ret;

        cache[from][parts] = INF;
        for(int partSize=1; from + partSize<= this.A.length; ++partSize){
            cache[from][parts] = Integer.min(cache[from][parts], minError(from, from+partSize) +
                    quantize(from+partSize, parts-1));
        }
        return cache[from][parts];

    }
}
