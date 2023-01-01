package baekjoon.from31to40;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p35_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numMeetings = Integer.parseInt(br.readLine());
        PriorityQueue<Meet> meetPriorityQueue = new PriorityQueue<>();
        StringTokenizer st;
        for(int i=0; i<numMeetings; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetPriorityQueue.add(new Meet(start, end));
        }

        int numPossibleMeetings=0;
        int lastMeetEndTime=0;

        while(!meetPriorityQueue.isEmpty()){
            Meet meet = meetPriorityQueue.poll();
            if(meet.start>=lastMeetEndTime){
                numPossibleMeetings++;
                lastMeetEndTime = meet.end;
            }
        }
        bw.write(Integer.toString(numPossibleMeetings));
        bw.close();
        br.close();
    }

    private static class Meet implements Comparable<Meet> {
        int start;
        int end;

        private Meet(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meet target){
            if(this.end==target.end){
                return Integer.compare(this.start, target.start);
            }
            return Integer.compare(this.end, target.end);
        }
    }
}
