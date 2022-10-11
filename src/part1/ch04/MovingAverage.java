package part1.ch04;

import java.util.Arrays;

public class MovingAverage {
    public static double[] movingAverage1(double[] A, int M){
        double[] result = new double[A.length-M+1];
        for (int i=M-1; i<A.length; i++){
            double partialSum = 0;
            for (int j= 0; j<M; j++){
                partialSum += A[i-j];
            }
            result[i-M+1] = partialSum/M;
        }
        return result;
    }

    public static double[] movingAverage2(double[] A, int M){
        double[] result = new double[A.length-M+1];
        double partialSum=0;
        for (int i = 0; i<M-1; i++){
            partialSum += A[i];
        }
        for (int i = M-1; i<A.length; i++){
            partialSum += A[i];
            result[i+1-M] = partialSum/M;
            partialSum = partialSum-A[i+1-M];
        }
        return result;
    }
    public static void main(String[] args){
        System.out.println(Arrays.toString(movingAverage1(new double[] {1,2,3,4,5,6,7,8,9}, 4)));
        System.out.println(Arrays.toString(movingAverage2(new double[] {1,2,3,4,5,6,7,8,9}, 4)));
    }
}
