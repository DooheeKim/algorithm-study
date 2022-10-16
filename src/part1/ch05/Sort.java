package part1.ch05;

import java.util.Arrays;

public class Sort {
    static void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
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
            // 불변식 a: A[0, .., i-1]는 이미 정렬돼있다.
            // A[0,..., i-1] 사이에 A[i]를 끼워 넣는다.
            int  j=i;
            while(j>0 && A[j-1]>A[j]){
                //불변식 b: A[j+1,...,i]의 모든 원소는 A[j]보다 크다.
                //불변식 c: A[0, ..., i]구간은 A[j]를 제외하면 정렬되어 있다.
                swap(A, j, j-1);
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
