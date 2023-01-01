package baekjoon.from1to10;

import java.io.*;
import java.util.StringTokenizer;

public class p2_1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numSubjects = Integer.parseInt(br.readLine());
        StringTokenizer scoreTokenizer = new StringTokenizer(br.readLine());

        int maxScore = 0;
        int scoreSum = 0;
        while (scoreTokenizer.countTokens() > 0) {
            int score = Integer.parseInt(scoreTokenizer.nextToken());
//            System.out.println(score);
            scoreSum += score;
            maxScore = Integer.max(maxScore, score);
        }
        bw.write(Double.toString(((double)scoreSum)/maxScore*100/numSubjects));

        br.close();
        bw.close();
    }
}
