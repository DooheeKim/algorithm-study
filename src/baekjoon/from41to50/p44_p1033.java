package baekjoon.from41to50;

import java.io.*;
import java.util.StringTokenizer;

public class p44_p1033 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numMaterials = Integer.parseInt(br.readLine());
        long[][] ratioInput = new long[numMaterials-1][4];
        StringTokenizer st ;
        for(int i=0; i<numMaterials-1; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long p = Long.parseLong(st.nextToken());
            long q = Long.parseLong(st.nextToken());
            long gcdPQ = getGcd(p, q);
            ratioInput[i] = new long[]{a, b, p/gcdPQ, q/gcdPQ}; /// 약분해서 넣어야 나중에 계산이 쉬움
        }

        long[][] solution = new long[numMaterials][2]; //[a][0]: a의 분자, [a][1]: a의 분모
        solution[0][0] = 1;
        solution[0][1] = 1;
        long solved = 1; // solution에 값이 채워지면 solved+=1, solved==numMaterials가 되면 반복문 종료

        while(solved<numMaterials){
            for(int i=0; i<numMaterials-1; i++){
                long[] problem = ratioInput[i];
                if(solution[(int)problem[0]][0] !=0 && solution[(int)problem[1]][0] != 0){
                    // 이미 풀린 문제
                    continue;
                }else if(solution[(int)problem[0]][0] == 0 && solution[(int)problem[1]][0] == 0){
                    // 못푸는 문제
                    continue;
                }else if(solution[(int)problem[0]][0] == 0 && solution[(int)problem[1]][0] != 0){
                    // 뒤의 재료는 알고 앞의 재료는 모를때
                    long numerator = problem[2] * solution[(int)problem[1]][0];
                    long denominator = problem[3] * solution[(int)problem[1]][1];
                    long gcd = getGcd(numerator, denominator);

                    solution[(int)problem[0]][0] = numerator/gcd;
                    solution[(int)problem[0]][1] = denominator/gcd;
                    solved++;
                }else{
                    // solution[problem[0]][0] != 0 && solution[problem[1]][0] == 0
                    // 반대로 앞의 재료를 알고 뒤의 재료를 모를때
                    long numerator = problem[3] * solution[(int)problem[0]][0];
                    long denominator = problem[2] * solution[(int)problem[0]][1];
                    long gcd = getGcd(numerator, denominator);

                    solution[(int)problem[1]][0] = numerator/gcd;
                    solution[(int)problem[1]][1] = denominator/gcd;
                    solved++;
                }
            }
        }

        long lcm = 1;
        for(int i=0; i<numMaterials; i++){// 루프 다돌면 모든 수들의 최소공배수가 구해지게 됨
            lcm = lcm*solution[i][1]/getGcd(lcm, solution[i][1]) ; //최소공배수는 두수의 곱/최대공약수
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<numMaterials; i++){
            sb.append(solution[i][0]*lcm/solution[i][1]);
            sb.append(" ");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static long getGcd(long a, long b){
        if(a<b){
            long tmp = a;
            a = b;
            b = tmp;
        };
        // 기저조건
        while(a%b!=0){
            long tmp = a;
            a = b;
            b = tmp%b;
        }
        return b;
    }
    private static long getGcd2(long a, long b){
        if(a<b) return getGcd(b,a);
        // 기저조건
        if(a%b==0) return b;
        return getGcd(b, a%b);
    }
}
