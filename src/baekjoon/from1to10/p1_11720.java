package baekjoon.from1to10;

import java.io.*;
import java.util.Scanner;

public class p1_11720 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String numString = sc.next();
        int ret = 0;

        for(int i=0; i<n;++i){
            ret+=(numString.charAt(i)-'0');
        }
        System.out.println(ret);
    }
}
