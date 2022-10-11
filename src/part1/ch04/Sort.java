package part1.ch04;

import java.util.Arrays;

public class Sort {
    static void selectionSort(int[] A){
        for(int i=0; i<A.length; i++){
            int minIndex=i;
            for(int j=i+1; j<A.length; j++){
                if(A[minIndex]>A[j]){
                    minIndex = j;
                }
            }
            //swap
            int tmp = A[i];
            A[i] = A[minIndex];
            A[minIndex] = tmp;
        }
    }

    static void insertionSort(int[] A){
        for(int i=0; i<A.length; i++){
            int j = i;
            while(j>0 && A[j-1]>A[j]){
                int tmp = A[j-1];
                A[j-1] = A[j];
                A[j] = tmp;
                j--;
            }
        }
    }
    public static void main(String[] args){
        int [] A = new int[] {5,4,3,2,1};
        selectionSort(A);
        System.out.println(Arrays.toString(A));

        A = new int[] {5,4,3,2,1};
        insertionSort(A);
        System.out.println(Arrays.toString(A));
    }
}
