package part1.ch05;

public class BinarySearch {
    //필수 조건: A는 오름차순 정렬
    //결과: A[i-1]<x<=A[i]인 i를 반환
    //이 때 A[-1]= -INF, A[n]=INF 로 가정
    public static int binSearch(int[] A, int x){
        int n = A.length;
        int lo = -1, hi = n;

        //반복문 불변식1: lo<hi
        //반복문 불변식2: A[lo]<x=A[hi]
        // * 불변식은 여기서 성립해야 한다.
        while(lo+1 < hi){
            int mid = (lo + hi)/2;
            if(A[mid]<x){
                lo = mid;
            }else{
                hi = mid;
            }
            //불변식은 여기서도 성립해야 한다.
        }
        return hi;
    }
    public static void main(String[] args){
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(binSearch(arr, 6));
    }
}
