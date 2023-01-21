package baekjoon.from61to70;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p66_1414 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int roomCount = Integer.parseInt(br.readLine());
        int[] roomParent = new int[roomCount];
        int[][] lanArr = new int[roomCount][roomCount];
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        int totalLength = 0;
        for(int i=0; i<roomCount; i++){
            char[] readLine = br.readLine().toCharArray();
            roomParent[i] = i; // Union-find 결과 저장을 위한 어레이
            for(int j=0; j<roomCount; j++){
                lanArr[i][j] = convertAlphabetToInteger(readLine[j]);
                priorityQueue.add(new Edge(i, j, lanArr[i][j]));
                totalLength+=lanArr[i][j];
            }
        }

        //// priorityQueue 에서 하나씩 꺼내면서
        int requiredLength = 0;
        while(!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if(findParent(edge.from, roomParent)==findParent(edge.to, roomParent)){
                // 이미 연결된 애들은 필요없음
                continue;
            }
            if((edge.from == edge.to) || (edge.weight==0)){
                // 자기 자신과 연결된 애들도 필요없음
                continue;
            }
            // 둘다 해당 안되는 애들은 unite
            unite(edge.from, edge.to, roomParent);
            requiredLength+=edge.weight;
        }

        //전부 다 같은 그룹인지 조사 후 남은거를 기부하면 됨
        int representativeParent = findParent(0, roomParent);
        for(int i=1; i<roomCount;i++){
            if(findParent(i,roomParent)!=representativeParent){
                bw.write("-1");
                bw.close();
                br.close();
                return;
            }
        }
        bw.write(Integer.toString(totalLength-requiredLength));
        bw.close();
        br.close();
    }
    private static int convertAlphabetToInteger(char character){
        if(character=='0'){
            return 0;
        }
        if(character<='Z'){
            // 'A'~'Z'까지의 수 일때: 27~52
            return character-'A'+27;
        }else{
            // 'a'~'z'까지의 수 일때: 1~26
            return character-'a'+1;
        }
    }

    private static int findParent(int node, int[] parent){
        if(parent[node]==node) return node;
        return parent[node]=findParent(parent[node], parent);
    }
    private static void unite(int node1, int node2, int[] parent){
        node1 = findParent(node1, parent);
        node2 = findParent(node2, parent);
        if(node1<node2){
            parent[node2] = node1;
        }else{
            parent[node1] = node2;
        }
        return;
    }

    private static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int weight;
        private Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
