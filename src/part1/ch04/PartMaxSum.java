package part1.ch04;

public class PartMaxSum {
    static final int MIN = Integer.MIN_VALUE;

    // O(n^3)
    public static int inefficientMaxSum(int[] A){
        int N = A.length;
        int ret = MIN;
        for (int i=0; i<N; i++){
            for (int j=i; j<N; j++){
                int sum = 0;
                for (int k=i; k<=j; k++){
                    sum += A[k];
                }
                ret = Integer.max(sum,ret);
            }
        }
        return ret;
    }

    // O(n^2)
    public static int littleInefficientMaxSum(int[] A){
        int N = A.length;
        int ret = MIN;
        for (int i=0; i<N; i++){
            int sum = 0;
            for (int j=i; j<N; j++){
                sum += A[j];
                ret = Integer.max(sum,ret);
            }
        }
        return ret;
    }

    // O(n log n)
    public static int fastMaxSum(int[] A, int lo, int hi){
        if (lo == hi){
            return A[lo];
        }
        int mid = (lo+hi)/2;

        int left = MIN, right = MIN, sum = 0;
        for (int i = mid; i>=lo; i--){
            sum+=A[i];
            left = Integer.max(sum, left);
        }
        sum = 0;
        for (int i = mid+1; i<=hi; i++){
            sum += A[i];
            right = Integer.max(sum, right);
        }
        int single = Integer.max(fastMaxSum(A, lo, mid), fastMaxSum(A, mid+1, hi));
        return Integer.max(single, left+right);
    }

    public static int fastestMaxSum(int[] A){
        int N = A.length, ret = MIN, psum = 0;
        for (int i = 0; i<N; i++){
            psum = Integer.max(psum, 0) + A[i];
            ret = Integer.max(psum, ret);
        }
        return ret;

    }
    public static void main(String[] args){
        int[] input = new int[] {-7, 4, -3, 6, 3, -8, 3, 4};
        System.out.println("Worst Inefficient Algorithm( O(n^3) ): "+ inefficientMaxSum(input));
        System.out.println("Inefficient Algorithm( O(n^2) ): "+ littleInefficientMaxSum(input));
        System.out.println("Fast Algorithm( O(n log n) ): "+ fastMaxSum(input, 0, input.length-1));
        System.out.println("Fastest Algorithm( O(n) ): "+ fastestMaxSum(input));
    }
}
