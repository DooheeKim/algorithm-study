package part2.ch16;

import java.util.Arrays;

public class SieveOfEratosthenes {
    static final int MAX_N = 10000000;
    static char[] sieve = new char[(MAX_N+7)/8+1];

    private static boolean isPrime(int i) {
        return !((sieve[i >> 3] & (1 << (i & 7))) == 0);
    }
    private static void checkComposite(int i){
        sieve[i>>3] &= ~(1<<(i&7));
    }
    public static void main(String[] args){
        Arrays.fill(sieve, (char) 255);
        checkComposite(0);
        checkComposite(1);
        int sqrtN = (int) Math.sqrt(MAX_N);
        for (int i=2; i<sqrtN; i++){
            if(isPrime(i)){
                System.out.println(i);
                for(int j=i*i;j<=MAX_N; j+=i){
                    checkComposite(j);
                }
            }
        }
    }
}
