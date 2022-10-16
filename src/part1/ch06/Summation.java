package part1.ch06;

public class Summation {
    private static int sum(int n){
        int ret = 0;
        for(int i=1; i<=n; i++){
            ret+=i;
        }
        return ret;
    }
    private static int recursiveSum(int n){
        if(n==1){
            return 1;
        }
        return n + recursiveSum(n-1);
    }
    public static void main(String[] args) {
        System.out.println(sum(15));
        System.out.println(recursiveSum(15));
    }
}
